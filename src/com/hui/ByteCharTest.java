package com.hui;

/**
 * Created by H on 2018/8/16.
 */
public class ByteCharTest {
    public static void main(String[] args) {
/*
        byte[] bytes = {
                0x30,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,
                0x40,0x41,0x42,0x43,0x44,0x45,0x46,0x47,0x48,0x49,
                0x50,0x51,0x52,0x53,0x54,0x55,0x56,0x57,0x58,0x59};
        byte[] bytes1 = {
                48,49,50,51,52,53,54,55,56,57,
                58,59,60,61,62,63,64,65,66,67,
                68,69,70,71,72,73,74,75,76,77};
        for (byte byte1 : bytes) {
            System.out.println("byte:"+byte1+" char:"+(char) byte1);
        }
        for (byte byte1 : bytes1) {
            System.out.println("byte:"+byte1+" char:"+(char) byte1);
        }

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
        System.out.println(char1++ + " " + char1);
*/

        sysOut(uppercase2lowercase("UPPERCASE"));
        sysOut(uppercase2lowercase("upperCase"));
        sysOut(lowercase2uppercase("lowercase"));
        sysOut(lowercase2uppercase("lowerCase"));
//        StringBuilder sb = new StringBuilder();
//        sysOut(sb.append('A').append('B').toString());

    }

    public static void sysOut(Object object) {
        System.out.println(object);
    }

    public static String uppercase2lowercase(String string){
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
}
