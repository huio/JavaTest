package cf.fcff.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javatest.socket.SocketServerTest.setFlag;

public class SocketServerSocketRunnable implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
//    private

    public SocketServerSocketRunnable(Socket socket) {
        this.socket = socket;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        if (socket == null || inputStream == null || outputStream == null) return;
        try {
            //表示接收数据时的等待超时数据, 此方法必须在接收数据之前执行才有效.
            // 此外, 当输入流的 read()方法抛出 SocketTimeoutException后, Socket仍然是连接的, 可以尝试再次读数据,
            // 单位为毫秒, 它的默认值为 0(表示会无限等待, 永远不会超时)
            socket.setSoTimeout(20 * 1000);
            //表示对于长时间处于空闲状态的Socket, 是否要自动把它关闭.
            if (!socket.getKeepAlive()) {
                socket.setKeepAlive(true);//true，若长时间没有连接则断开
            }
            new Thread(new SocketServerSocketReceiveRunnable(inputStream,outputStream)).start();
            if (socket != null) {
                socket.close();
                System.out.println("关闭连接");
                if (socket.isConnected()) {
                    System.out.println("socket.isConnected");
                }
                if (socket.isClosed()) {
                    System.out.println("socket.isClosed");
                }
            }
        } catch (IOException e) {

        }
    }

    private void send(String msg) {
        new Thread(new SocketServerSocketSendRunnable(inputStream, outputStream, msg)).start();
    }
}

class SocketServerSocketReceiveRunnable implements Runnable {
    private InputStream inputStream;
    private OutputStream outputStream;

    public SocketServerSocketReceiveRunnable(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] headBytes = new byte[1024];
                int headBytesLen = inputStream.read(headBytes);
                if (headBytesLen == -1) {
                    break;
                }
                String inStr = new String(headBytes, "GB18030");

                String outStr = "========服务端时间:" + SimpleDateFormat.getDateTimeInstance().format(new Date()) + " 收到的数据：" + inStr;
                outputStream.write(outStr.getBytes("GB18030"));
                if ("close".equals(inStr)) {
                    setFlag(false);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class SocketServerSocketSendRunnable implements Runnable {
    private InputStream inputStream;
    private OutputStream outputStream;
    private String msg;

    public SocketServerSocketSendRunnable(InputStream inputStream, OutputStream outputStream, String msg) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            if (msg == null || "".equals(msg)) return;
            outputStream.write(msg.getBytes(Charset.forName("GB18030")));
            byte[] headBytes = new byte[1024];
            int headBytesLen = inputStream.read(headBytes);
            if (headBytesLen == -1) {
                return;
            }
            String inStr = new String(headBytes, "GB18030");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
