package cf.fcff.socket;

public interface SocketServerListener {
    /**
     * 收到报文的回调
     * @param body 收到的信息
     * @return 需要返回的信息
     */
    String onReceive(String body);

    void onResponese(String body);
}
