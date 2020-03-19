package cf.fcff;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by H on 2018/8/16.
 */
public class ByteCharTest {
    public static void main(String[] args) {

        char[] chars = new char[65535];
        for (int i = 0; i <= Character.MAX_VALUE; i++) {
            sysOut(i + ""+(char) i);
        }

/*
        StringBuffer stringGBK = new StringBuffer("GBK");
        stringGBK.append("A");
        StringBuffer stringUTF = new StringBuffer("UTF-8");
        stringUTF.append("A");
        sysOut(stringGBK.toString()+" "+stringUTF.toString());
*/

//        sysOut(Long.parseLong("00991078B2",16));

/*
        byte[] bytes = {
                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38,//0-9
                0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46,//A-G
                0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d,//H-N
                0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53,//O-T
                0x54, 0x55, 0x56, 0x57, 0x58, 0x59,//U-Z
                0x5a, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66,//a-g
                0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d,//h-n
                0x6f, 0x70, 0x71, 0x72, 0x73, 0x74,//o-t
                0x75, 0x76, 0x77, 0x78, 0x79, 0x7a};//u-z
        sysOut(bytes2HexString(bytes));
        for (byte byte1 : HexString2Bytes(Bytes2HexString(bytes))) {
            System.out.println("byte:"+byte1+" char:"+(char) byte1);
        }
        sysOut("");
*/
/*
        byte[] bytes1 = {
                48,49,50,51,52,53,54,55,56,57,
                65,66,67,68,69,70,71,72,73,74,75,76,77,78,
                79,80,81,82,83,84,85,86,87,88,89,90,
                97,98,99,100,101,102,103,104,105,106,107,108,109,110,
                111,112,113,114,115,116,117,118,119,120,121,122};
        for (byte byte1 : bytes1) {
            System.out.println("byte:"+byte1+" char:"+(char) byte1);
        }
        sysOut("");
*/

/*
        int int2char = 70;
        System.out.println((char) int2char);//F

        char char2int = 'F';
        System.out.println((int) char2int);
*/


        //48-57 char:0-9
        //65-90 char:A-Z
        //97-122 char:a-z
/*
        for (int i=0;i<123;i++) {
            char char1 = (char) i;
            System.out.println("int:"+i+" char:"+char1);
        }
*/
/*
        String string = "A";
        char char1 = 'A';
        System.out.println(char1++ + " char1++" + char1);
*/

/*
        sysOut(uppercase2lowercase("UPPERCASE"));
        sysOut(uppercase2lowercase("upperCase"));
        sysOut(lowercase2uppercase("lowercase"));
        sysOut(lowercase2uppercase("lowerCase"));
*/

//AA551B01F87B0101C7EB303137BAC5B5BD31BAC5B4B0BFDA0000A4A82FF4
/*
        String hexString = "C7EB303033BAC5B5BD31BAC5B4B0BFDA";//
        byte[] bytes = HexString2Bytes(hexString);
        bytes = "黄金辉哥1".getBytes(Charset.forName("GBK"));
        String str = new String(bytes, Charset.forName("GBK"));
        sysOut(str);
        str = "请003号到1号窗口";
        sysOut(bytes2HexString(str.getBytes(Charset.forName("GBK"))));
*/
/*
        byte[] b = {0x68, 0x6a, 0x68};
        for (int i = 0; i < b.length; i++) {
            sysOut((char) b[i]);
        }
*/

    }

    public static byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    public static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public static char byteToChar(byte[] b) {
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }

    public static void sysOut(Object object) {
        System.out.println(object);
    }

    public static String uppercase2lowercase(String string) {
        if (string == null) {
            throw new NullPointerException("不能为空对象");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char str = string.charAt(i);
            if (str >= 65 && str <= 90) {
                str = (char) (str + 32);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static String lowercase2uppercase(String string) {
        if (string == null) {
            throw new NullPointerException("不能为空对象");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char str = string.charAt(i);
            if (str >= 97 && str <= 122) {
                str = (char) (str - 32);
            }
            sb.append(str);
        }
        return sb.toString();
    }


    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String bytes2HexString(byte[] bytes) {
        return bytes2HexString(bytes, bytes.length);
    }

    public static String bytes2HexString(byte[] bytes, int length) {
        if (bytes == null) {
            return null;
        }
        int len = length;
        if (len <= 0) {
            return null;
        }
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    public static String Bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }


    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0}));
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1}));
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
     * 0xD9}
     *
     * @param src String
     * @return byte[]
     */
    public static byte[] HexString2Bytes(String src) {
        byte[] tmp = src.getBytes();
        byte[] ret = new byte[tmp.length / 2];
        for (int i = 0; i < tmp.length / 2; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
//			方法2
//			String bStr =src.substring(2*i,2*(i+1));
//			ret[i] =(byte)Integer.parseInt(bStr,16);
        }
        return ret;
    }
}
