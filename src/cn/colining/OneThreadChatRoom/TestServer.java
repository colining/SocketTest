package cn.colining.OneThreadChatRoom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.Buffer;

/**
 * Created by asus on 2017/5/1.
 */
public class TestServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String info;
            String info1;
            while (true)
            {
                info = dataInputStream.readUTF();
                System.out.println("客户端说:"+ info);
                if (info.equals("bye"))
                    break;
                info1 = bufferedReader.readLine();
                dataOutputStream.writeUTF(info1);
                if (info1.equals("bye"))
                    break;
            }
            dataInputStream.close();
            dataOutputStream.close();
            bufferedReader.close();
            socket.close();
            serverSocket.close();
        } catch (SocketException e)
        {
            System.out.println("网络异常");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
