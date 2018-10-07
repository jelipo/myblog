package util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by cao on 2017/3/7.
 */
public class SocketTool {

    /**
     * 发送socket字节流，长度不允许超过6位，即最大长度为 999999
     *
     * @param socket
     * @param message
     */
    public static void send(Socket socket, String message) {

        int bodyLength = message.getBytes().length;
        int blLength = sizeOfInt(bodyLength);
        StringBuilder str = new StringBuilder(headFlag).append(getZeroStr(blLength)).append(bodyLength).append(message);
        DataOutputStream out = null;
        synchronized (socket) {
            try {
                out = new DataOutputStream(socket.getOutputStream());
                out.write(str.toString().getBytes("UTF-8"));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    final static String headFlag = "!F!";
    final static String zero1 = "0";
    final static String zero2 = "00";
    final static String zero3 = "000";
    final static String zero4 = "0000";
    final static String zero5 = "00000";

    private static String getZeroStr(int blLength) {
        switch (blLength) {
            case 1:
                return zero5;
            case 2:
                return zero4;
            case 3:
                return zero3;
            case 4:
                return zero2;
            case 5:
                return zero1;
            default:
                return "";
        }
    }

    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    /**
     * 获取int值的位数
     *
     * @param x
     * @return
     */
    static int sizeOfInt(int x) {
        for (int i = 0; ; i++) {
            if (x <= sizeTable[i]) {
                return i + 1;
            }
        }
    }


    final private static byte[] headFlagByte = "!F!".getBytes();

    /**
     * 方法因为使用了BufferedReader的read方法，所以本方法为阻塞
     * 定义body“长度”的byte为6个byte
     *
     * @param br
     * @return
     */
    public static String getMessage(BufferedInputStream br) throws IOException {
        int single = 0;
        synchronized (br) {
            while (true) {
                if ((single = br.read()) == -1) throw new IOException();
                if (single != headFlagByte[0]) continue;
                if (br.read() != headFlagByte[1]) continue;
                if (br.read() != headFlagByte[2]) continue;
                int length = 0;
                for (int i = 6; i > 0; i--) {
                    length += getNumberByAscii(br.read()) * numTable[i - 1];
                }
                byte[] resultChar = new byte[length];
                if (br.read(resultChar, 0, length) == -1) throw new IOException();
                return new String(resultChar, "UTF-8");
            }
        }
    }

    private static int[] numTable = {1, 10, 100, 1000, 1000, 100000};

    private static int getNumberByAscii(int by) {
        switch (by) {
            case 48:
                return 0;
            case 49:
                return 1;
            case 50:
                return 2;
            case 51:
                return 3;
            case 52:
                return 4;
            case 53:
                return 5;
            case 54:
                return 6;
            case 55:
                return 7;
            case 56:
                return 8;
            case 57:
                return 9;
            default:
                return -1;

        }
    }


}
