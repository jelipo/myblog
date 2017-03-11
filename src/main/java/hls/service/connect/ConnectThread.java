package hls.service.connect;

import util.SocketTool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ConnectThread implements Runnable {

    private ServerSocket serverSocket;
    private SocketPojo socketPojo;

    public ConnectThread(int port, SocketPojo socketPojo) throws IOException {
        System.out.println("创建Socket服务");
        this.serverSocket = new ServerSocket(port);
        this.socketPojo = socketPojo;
    }

    /**
     * 当有新连接接入时，把原有的socket替换为新的socket，并新开新开子线程接收消息。保证当前系统中只有一个与hls客户端的socket连接
     */
    @Override
    public void run() {
        try {
            while (true) {
                Socket newSocket=serverSocket.accept();
                socketPojo.setSocket(newSocket);
                ConnectChildThread connectChildThread = new ConnectChildThread(socketPojo);
                Thread thread = new Thread(connectChildThread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理socket接受的消息
     */
    public class ConnectChildThread implements Runnable {

        private SocketPojo socketPojo;

        ConnectChildThread(SocketPojo socketPojo) {
            this.socketPojo= socketPojo;
        }

        /**
         * 读取输入流，根据发来的内容返回对应的内容，没有找到相应该返回的消息时不返回消息
         */
        @Override
        public void run() {
            try {
                BufferedInputStream bis=new BufferedInputStream(socketPojo.getSocket().getInputStream());
                //读取客户端发送来的消息
                String mess;
                while (true) {
                    mess=SocketTool.getMessage(bis);
                    System.out.println(mess);
                    String resultMessage = select(mess);
                    if (resultMessage != null) {
                        SocketTool.send(socketPojo.getSocket(),resultMessage);
                    }
                    if (socketPojo.getSocket().isClosed()||socketPojo.getSocket()==null){
                        socketPojo.getSocket().close();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                System.out.println("与客户端连接断开");
                try {
                    socketPojo.getSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 根据client发来的消息返回相应的消息，没有相应的消息返回null
         *
         * @param message 消息内容
         * @return 根据发来的消息内容返回相应的消息，没有对应的返回null
         */
        private String select(String message) {
            switch (message) {
                case "I'm client":
                    return "I'm Server";
                case "hello":
                    return "hello";
                default:
                    return null;
            }
        }
    }
}
