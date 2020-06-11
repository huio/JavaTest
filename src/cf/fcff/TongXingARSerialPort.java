package cf.fcff;

import java.math.BigDecimal;
import java.util.Arrays;

public class TongXingARSerialPort {

    public static void main(String[] args) {
        readSerialPort();
        sendPayResult();
        sendPayResultI();
    }


    private static int l = 0;
    private static byte[] amtBytes = ByteCharTest.HexString2Bytes("02000E0000000000310009000000640351");
    private static byte[] payBytes = ByteCharTest.HexString2Bytes("02003C00000000003100010000006400989A69323030303130303031323030363038313435303439303030313138363230323030363038313435303439030e");

    private static byte[] read(int len) {
        byte[] bytes1 = Arrays.copyOfRange(amtBytes, l, l + len);
        l += len;
        return bytes1;
    }

    private static void readSerialPort() {
        //START_CODE 报⽂起始符(0x02) 1 byte
        byte[] b_start_code = read(1);
        if (b_start_code.length != 1 || b_start_code[0] != 0x02) return;


        //LEN 报⽂⻓度指示 2 bytes
        byte[] b_length = read(2);
        if (b_length.length != 2) return;
        int len = b_length[0] * 256 + b_length[1];

        //TRANSCATION_ID 交易ID 6 byte
        //CONTENT 交易指令报⽂
        //END_CODE 报⽂终⽌符(0x03) 1 byte
        //CHECK_BYTE 校验字节位 1 byte LEN 到 END_CODE 的 报 ⽂ , 即 {LEN,TRANSCATION_ID,CONTENT,ENCODE} 的所有字节进⾏按位异或计算
        byte[] b_body = read(len);
        if (b_body.length != len || b_body.length < 6 || b_body[len - 2] != (byte) 0x03) return;
        //计算校验位
        int check;
        check = b_length[0] ^ b_length[1];
        for (int i = 0; i < b_body.length - 1; i++) {
            check = check ^ b_body[i];
        }
        if (b_body[b_body.length - 1] != (byte) check) return;

        //TRANSCATION_ID
        //若交易 ID 为 1，则 TRANSCATION_ID 字段为{0x00, 0x00,0x00, 0x00, 0x00,’1’}，即 {0x00, 0x00, 0x00, 0x00, 0x00,0x31} ;
        //从⼀个交易请求发起开始到交易结束，应该只有⼀个交易 ID,⽤以标识包的合法性，防⽌串包。
        byte[] TRANSCATION_ID = Arrays.copyOfRange(b_body, 0, 6);

        //CONTENT
        byte[] CONTENT = Arrays.copyOfRange(b_body, 6, b_body.length);

        //CONTENT:指令代码
        byte[] cmdBytes = Arrays.copyOfRange(CONTENT, 0, 2);

        if (cmdBytes[0] == 0 && cmdBytes[1] == 9) {//0009
            sysOut("amt");
            if (CONTENT.length < 38) return;
        } else if (cmdBytes[0] == 0 && cmdBytes[1] == 1) {//0001
            sysOut("confirm amt");
            //消费⾦额 单位为分 高位在前
            byte[] amtBytes = Arrays.copyOfRange(CONTENT, 2, 6);
            //智眼终端编号
            byte[] oriDeviceIdBytes = Arrays.copyOfRange(CONTENT, 6, 10);
            //智眼订单号
            byte[] oriOrderIdBytes = Arrays.copyOfRange(CONTENT, 10, 38);

            BigDecimal amt = getMoney(amtBytes);
            sysOut(String.format("AMT:%s \t DevId:%s \t OrderId:%s", amt.doubleValue(),
                    ByteCharTest.Bytes2HexString(oriDeviceIdBytes),
                    ByteCharTest.Bytes2HexString(oriOrderIdBytes)));
        } else if (cmdBytes[0] == 0 && cmdBytes[1] == 3) {//0003
            sysOut("cancel pay");
            byte[] oriOrderIdBytes = Arrays.copyOfRange(CONTENT, 2, 30);
            sysOut(String.format("OrderId:%s", ByteCharTest.Bytes2HexString(oriOrderIdBytes)));
        } else {
            sysOut(String.format("未知报文%s%s", cmdBytes[0], cmdBytes[1]));
        }
    }

    private static int payLen = 1 + 2 + 6 + 2 + 6 + 28 + 1 + 128 + 4 + 14 + 1 + 1;

    private static void sendPayResult() {
        byte[] bytes = new byte[payLen];
        //START_CODE 1
        bytes[0] = 0x02;

        //LEN 2
        bytes[1] = 0x00;
        bytes[2] = (byte) 0xbf;

        //TRANSCATION_ID 6  -0
        String TRANSCATION_ID = "1";
        byte[] tranBytes = TRANSCATION_ID.getBytes();
        System.arraycopy(tranBytes, 0, bytes, 3 + 6 - tranBytes.length, tranBytes.length);

        //cmd 2
        bytes[9] = 0x00;
        bytes[10] = 0x02;

        //devId 6
        byte[] oriDeviceIdBytes = new byte[6];
        oriDeviceIdBytes[5] = 1;
        System.arraycopy(oriDeviceIdBytes, 0, bytes, 11, 6);

        //oriOrderId 28
        byte[] oriOrderIdBytes = new byte[28];
        oriOrderIdBytes[27] = 2;
        System.arraycopy(oriOrderIdBytes, 0, bytes, 17, oriOrderIdBytes.length);

        //type 1:card 2:QR
        bytes[45] = 0x01;

        //data 128 0-
        byte[] dataBytes = new byte[128];
        dataBytes[127] = 3;
        System.arraycopy(dataBytes, 0, bytes, 46, dataBytes.length);

        //amt 4 -0
        byte[] amtBytes = new byte[4];
        amtBytes[3] = 4;
        System.arraycopy(amtBytes, 0, bytes, 174, amtBytes.length);

        //date 14 `20200610144159`
        byte[] dateBytes = new byte[14];
        dateBytes[13] = 5;
        System.arraycopy(dateBytes, 0, bytes, 178, dateBytes.length);

        //END_CODE 1
        bytes[192] = 0x03;

        //CHECK_BYTE 1
        //第一个为长度的第一个字符
        byte CHECK_BYTE = 0x00;
        //从2(长度的第二个index)开始  不包含最后的check code位
        for (int i = 2; i < bytes.length - 1; i++) {
            CHECK_BYTE = (byte) (bytes[i] ^ CHECK_BYTE);
        }
        bytes[193] = CHECK_BYTE;
        sysOut(ByteCharTest.Bytes2HexString(bytes));
    }

    private static void sendPayResultI() {
        byte[] bytes = new byte[payLen];
        int index = 0;
        //START_CODE 1
        bytes[index++] = 0x02;

        //LEN 2
        bytes[index++] = 0x00;
        bytes[index++] = (byte) 0xbf;

        //TRANSCATION_ID 6  -0
        String TRANSCATION_ID = "1";
        int transLen = 6;
        index += transLen;
        byte[] tranBytes = TRANSCATION_ID.getBytes();
        System.arraycopy(tranBytes, 0, bytes, index - tranBytes.length, tranBytes.length);

        //cmd 2
        bytes[index++] = 0x00;
        bytes[index++] = 0x02;

        //devId 6
        int devIdLen = 6;
        byte[] oriDeviceIdBytes = new byte[devIdLen];
        oriDeviceIdBytes[5] = 1;
        System.arraycopy(oriDeviceIdBytes, 0, bytes, index, devIdLen);
        index += devIdLen;

        //oriOrderId 28
        int oriOrderIdLen = 28;
        byte[] oriOrderIdBytes = new byte[oriOrderIdLen];
        oriOrderIdBytes[oriOrderIdLen-1] = 2;
        System.arraycopy(oriOrderIdBytes, 0, bytes, index, oriOrderIdBytes.length);
        index += oriOrderIdLen;

        //type 1:card 2:QR
        bytes[index++] = 0x01;

        //data 128 0-
        int dataLen = 128;
        byte[] dataBytes = new byte[dataLen];
        dataBytes[dataLen-1] = 3;
        System.arraycopy(dataBytes, 0, bytes, index, dataBytes.length);
        index += dataLen;

        //amt 4 -0
        int amtLen = 4;
        byte[] amtBytes = new byte[amtLen];
        amtBytes[amtLen-1] = 4;
        System.arraycopy(amtBytes, 0, bytes, index, amtBytes.length);
        index += amtLen;

        //date 14 `20200610144159`
        int dateLen = 14;
        byte[] dateBytes = new byte[dateLen];
        dateBytes[dateLen-1] = 5;
        System.arraycopy(dateBytes, 0, bytes, index, dateBytes.length);
        index += dateLen;

        //END_CODE 1
        bytes[index++] = 0x03;

        //CHECK_BYTE 1
        //第一个为长度的第一个字符
        byte CHECK_BYTE = 0x00;
        //从2(长度的第二个index)开始  不包含最后的check code位
        for (int i = 2; i < bytes.length - 1; i++) {
            CHECK_BYTE = (byte) (bytes[i] ^ CHECK_BYTE);
        }
        bytes[index] = CHECK_BYTE;
        sysOut(ByteCharTest.Bytes2HexString(bytes));
    }

    public static BigDecimal getMoney(byte[] bytes) {
        BigDecimal decimal = BigDecimal.ZERO;
        for (int i = 0; i < bytes.length; i++) {
            decimal = decimal.add(BigDecimal.valueOf((bytes[i] & 0xff) * (1 << 8 * (bytes.length - i - 1))));
        }
        return decimal.multiply(BigDecimal.valueOf(0.01));
    }

    private static void sysOut(String string) {
        System.out.println(string);
    }
}
