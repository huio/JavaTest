package cf.fcff.socket;

import sun.nio.cs.ext.GBK;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by H on 2018/8/20.
 */
public class SocketClientTest {
    public static void main(String[] args) {
        String hostname = "192.168.5.8";
        int port = 60000;
        int connectTimeout = 60 * 1000;
        int receiveTimeout = 90 * 1000;

        Socket socket = new Socket();

        OutputStream outputStream = null;
        InputStream inputStream = null;

        OutputStreamWriter outputStreamWriter = null;
        InputStreamReader inputStreamReader = null;

        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        SocketAddress socketAddress = new InetSocketAddress(hostname, port);
        try {
            socket.connect(socketAddress, connectTimeout);
            socket.setSoTimeout(receiveTimeout);

            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            String outString = "yytyktG0012102214客户端发送数据";

            //直接读写
            outputStream.write(outString.getBytes("GB2312"));
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes,"GB2312"));

            //通过 OutputStreamWriter InputStreamReader 读写
            outputStreamWriter = new OutputStreamWriter(outputStream,"GB2312");
            inputStreamReader = new InputStreamReader(inputStream, "GB2312");

/*
            outputStreamWriter.write(outString, 0, outString.length());
            char[] chars = new char[20];
            inputStreamReader.read(chars, 0, 20);
            System.out.println(new String(chars));
*/


            //通过 BufferedWriter BufferedReader 读写
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedReader = new BufferedReader(inputStreamReader);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
