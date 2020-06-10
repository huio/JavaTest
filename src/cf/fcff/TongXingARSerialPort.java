package cf.fcff;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class TongXingARSerialPort {

    public static void main(String[] args) {
        readSerialPort();
        sendPayResult();
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
        byte[] b_body = read(len);
        if (b_body.length != len || b_body.length < 6 || b_body[len - 1] != (byte) 0x03) return;


        //计算校验位
        int check;
        check = b_length[0] ^ b_length[1];
        for (byte b : b_body) {
            check = check ^ b;
        }


        //CHECK_BYTE 校验字节位 1 byte LEN 到 END_CODE 的 报 ⽂ , 即 {LEN,TRANSCATION_ID,CONTENT,ENCODE} 的所有字节进⾏按位异或计算
        byte[] b_check = read(1);
        if (b_check.length != 1 || b_check[0] != (byte) check) return;


        //TRANSCATION_ID
        //若交易 ID 为 1，则 TRANSCATION_ID 字段为{0x00, 0x00,0x00, 0x00, 0x00,’1’}，即 {0x00, 0x00, 0x00, 0x00, 0x00,0x31} ;
        //从⼀个交易请求发起开始到交易结束，应该只有⼀个交易 ID,⽤以标识包的合法性，防⽌串包。
        byte[] TRANSCATION_ID = Arrays.copyOfRange(b_body, 0, 7);

        //CONTENT
        byte[] CONTENT = Arrays.copyOfRange(b_body, 7, b_body.length);

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
        System.arraycopy(tranBytes, 0, bytes, 6 - tranBytes.length, tranBytes.length);

        //cmd 2
        bytes[9] = 0x00;
        bytes[10] = 0x02;

        //devId 6
        byte[] oriDeviceIdBytes = new byte[6];
        System.arraycopy(oriDeviceIdBytes, 0, bytes, 11, 6);

        //oriOrderId 28
        byte[] oriOrderIdBytes = new byte[28];
        System.arraycopy(oriOrderIdBytes, 0, bytes, 17, oriOrderIdBytes.length);

        //type 1:card 2:QR
        bytes[45] = 0x01;

        //data 128 0-
        byte[] dataBytes = new byte[128];
        System.arraycopy(dataBytes, 0, bytes, 46, dataBytes.length);

        //amt 6 -0
        byte[] amtBytes = new byte[6];
        System.arraycopy(amtBytes, 0, bytes, 174, amtBytes.length);

        //date 14 `20200610144159`
        byte[] dateBytes = new byte[14];
        System.arraycopy(dateBytes, 0, bytes, 180, dateBytes.length);

        //END_CODE 1
        bytes[194] = 0x03;

        //CHECK_BYTE 1
        //第一个为长度的第一个字符
        byte CHECK_BYTE = 0x00;
        //从2开始  不包含最后的check code位
        for (int i = 2; i < bytes.length - 1; i++) {
            CHECK_BYTE = (byte) (bytes[i] ^ CHECK_BYTE);
        }
        bytes[bytes.length - 1] = CHECK_BYTE;
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


    private static int l = 0;
    private static byte[] bytes = ByteCharTest.HexString2Bytes("02003B00000000003100010000006400989A69323030303130303031323030363038313435303439303030313138363230323030363038313435303439030e");

    private static byte[] read(int len) {
        byte[] bytes1 = Arrays.copyOfRange(bytes, l, l + len);
        l += len;
        return bytes1;
    }
}
