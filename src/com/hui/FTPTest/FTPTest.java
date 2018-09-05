package com.hui.FTPTest;

/**
 * Created by H on 2018/9/6.
 */
public class FTPTest {
    public static void main(String[] args) {
        FtpContinueUtilsNoStatic ftpContinueUtilsNoStatic = new FtpContinueUtilsNoStatic();
        try {
            long startTime = System.currentTimeMillis();
            ftpContinueUtilsNoStatic.uploadFile("192.168.2.3", 21, "MultiMediaScreen", "huihui", "/", "play.txt");
            System.out.println("执行耗时："+(System.currentTimeMillis()-startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
