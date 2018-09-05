package com.hui;

import java.math.BigDecimal;

/**
 * Created by H on 2018/8/22.
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        sysOut(add("6", "2").toString());// +
        sysOut(subtract("6", "2").toString());// -
        sysOut(multiply("6", "2").toString());// *
        sysOut(divide("6", "2").toString());// /
        sysOut(remainder("6", "4").toString());// %
    }
    public static void sysOut(String string) {
        System.out.println(string);
    }

    public static BigDecimal add(String str1,String str2){
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.add(decimal2);
    }
    public static BigDecimal subtract(String str1,String str2){
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.subtract(decimal2);
    }
    public static BigDecimal multiply(String str1,String str2){
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.multiply(decimal2);
    }
    public static BigDecimal divide(String str1,String str2){
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.divide(decimal2);
    }
    public static BigDecimal remainder(String str1,String str2){
        BigDecimal decimal1 = new BigDecimal(str1);
        BigDecimal decimal2 = new BigDecimal(str2);
        return decimal1.remainder(decimal2);
    }
}
