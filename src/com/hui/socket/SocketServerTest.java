package com.hui.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by H on 2018/8/22.
 */
public class SocketServerTest {
    public static boolean flag = true;

    public static void setFlag(boolean flag) {
        SocketServerTest.flag = flag;
    }

    public static void main(String[] args) {
        try {
//            final boolean[] flag = {true};
            ServerSocket serverSocket = new ServerSocket(60000);
//            serverSocket.setSoTimeout(10 * 1000);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(30 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    flag[0] = false;
                    setFlag(false);//三十秒之后关闭socket
                }
            });
            thread.start();
//            while (flag[0]) {
            System.out.println("等待接收");
            Socket socket = null;
            while (flag) {
                socket = serverSocket.accept();
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("收到连接" + inetAddress.getHostAddress());
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                byte bytes[] = new byte[10];
                int len;
                while ((len = inputStream.read(bytes)) != 0) {
                    System.out.println("length:" + len + " byte[1024]:" + new String(bytes));
                }

/*              //若客户端未结束，则接收不到数据
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String string;
                while ((string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(string);
                }
                System.out.println(stringBuilder.toString());
*/

                String sendString = "send something";
                outputStream.write(sendString.getBytes());
                socket.close();
            }
            serverSocket.close();

//                while (inputStream.read(bytes) != -1) {
//                    stringBuilder.append(new String(bytes));
//                }
//                System.out.println(stringBuilder.toString());
//                inputStream.close();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
