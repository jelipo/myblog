package hls.service.connect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.PackingResult;
import util.SocketTool;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

@Service("hls/service/connect/SwitchService")
public class SwitchService {

    @Value("#{config['switch.serverPort']}")
    private int port;

    private PackegSocket packegSocket;

    /**
     * 初始化服务，另起线程创建新的ServerSocket，并处理与client的连接
     */
    @PostConstruct
    public void init() {
        Socket socket = null;
        packegSocket=new PackegSocket(socket);
        try {
            ConnectThread connectServer = new ConnectThread(this.port, packegSocket);
            Thread connect = new Thread(connectServer);
            connect.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取与hls client连接的socket，当启动后没有client连接的时候，socket为空，socket对象由子线程创建
     *
     * @return
     */
    public Socket getHlsClientSocket() {
        return packegSocket.socket;
    }

    public void sendMessage(String message) throws IOException {
        Socket socket1=packegSocket.socket;
        if (socket1==null){
            throw new IOException();
        }
        SocketTool.send(socket1,message);

    }

    protected class PackegSocket{
        protected Socket socket;
        public PackegSocket(Socket socket){
            this.socket=socket;
        }
    }

}
