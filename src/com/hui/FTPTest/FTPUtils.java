package com.hui.FTPTest;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by H on 2018/9/6.
 */
public class FTPUtils {
    private String hostname;

    private int port = 21;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean upload(File file) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostname, port);
            ftpClient.login("MultiMediaScreen", "huihui");
            FileInputStream fis = new FileInputStream(file);
            ftpClient.storeFile("/", fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean upload(String filePath) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostname, port);
            ftpClient.login("MultiMediaScreen", "huihui");
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ftpClient.storeFile("/", fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        FTPUtils ftpUtils = new FTPUtils();
        ftpUtils.setHostname("192.168.2.3");
        ftpUtils.upload("WriteText.txt");
    }
}
