package hls.service.connect;

import org.springframework.beans.factory.InitializingBean;
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
public class SwitchService implements InitializingBean {

    @Value("#{config['switch.serverPort']}")
    private int port;

    private SocketPojo socketPojo;

    /**
     * 初始化服务，另起线程创建新的ServerSocket，并处理与client的连接
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        socketPojo = new SocketPojo();
        try {
            ConnectThread connectServer = new ConnectThread(this.port, socketPojo);
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
        return socketPojo.getSocket();
    }

    public void sendMessage(String message) throws IOException {
        Socket socket1 = socketPojo.getSocket();
        System.out.println("q");
        if (socket1 == null || socket1.isClosed()) {
            System.out.println("尚未有连接接入或者连接已关闭");
        }
        System.out.println("e");
        SocketTool.send(socket1, message);

    }
}


