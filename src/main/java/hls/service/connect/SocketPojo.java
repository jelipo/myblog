package hls.service.connect;

import java.net.Socket;


public class SocketPojo {

    private volatile Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
