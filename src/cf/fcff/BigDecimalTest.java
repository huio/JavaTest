package cf.fcff;

import java.math.BigDecimal;

/**
 * Created by H on 2018/8/22.
 */
public class BigDecimalTest {

    public static void main(String[] args) {
//        sysOut(add("6", "2").toString());// +
//        sysOut(subtract("6", "2").toString());// -
//        sysOut(multiply("6", "2").toString());// *
//        sysOut(divide("6", "2").toString());// /
//        sysOut(remainder("6", "4").toString());// %

        /**
         *Math.sqrt()//计算平方根
         *Math.cbrt()//计算立方根
         *Math.pow(a, b)//计算a的b次方
         *Math.max( , );//计算最大值
         *Math.min( , );//计算最小值
         * abs求绝对值
         *
         * ceil天花板的意思，就是返回大的值
         * floor地板的意思，就是返回小的值
         *
         * round 四舍五入，float时返回int值，double时返回long值
         */
        Math.abs(-1);//|-1|
        Math.pow(2, 3);//2^3
        Math.round(2.82f);//四舍五入
        Math.floor(2.22f);
        Math.ceil(2.22f);
        double a = 5;
        int b = 2;
        double c = a / b;
        sysOut(Math.floor(5 / 2) + " " + Math.ceil(5 / 2) + " "
                + Math.floor(5f / 2f) + " " + Math.ceil(5f / 2f) + " "
                + Math.floor(a / b) + " " + Math.ceil(a / b) + " "
                + Math.floor(c) + " " + Math.ceil(c));
/*
        int i = 4;
        int col = 2;
        int rowIs = i / col % 2;
        int colIs = i % col % 2;

        sysOut( "row:"+String.valueOf(rowIs) +" col:"+ String.valueOf(colIs) + " " +(rowIs==colIs));
*/
    }

    public static void sysOut(String string) {
        System.out.println(string);
    }

    public static BigDecimal add(String str1, String str2) {
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.add(decimal2);
    }

    public static BigDecimal subtract(String str1, String str2) {
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.subtract(decimal2);
    }

    public static BigDecimal multiply(String str1, String str2) {
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.multiply(decimal2);
    }

    public static BigDecimal divide(String str1, String str2) {
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.divide(decimal2);
    }

    public static BigDecimal remainder(String str1, String str2) {
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.remainder(decimal2);
    }
}
