package cf.fcff.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketServer implements Runnable {
    public static boolean flag = true;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(60000);
            System.out.println("等待接收");
            Socket socket;
            while (flag) {
                try {
                    socket = serverSocket.accept();
                    InetAddress inetAddress = socket.getInetAddress();
                    System.out.println("收到连接" + inetAddress.getHostAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket.close();
            if (serverSocket.isClosed()) {
                System.out.println("serverSocket.isConnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
