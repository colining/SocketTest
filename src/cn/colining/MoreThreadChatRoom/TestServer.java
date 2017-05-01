package cn.colining.MoreThreadChatRoom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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

            new MyClientReader(dataInputStream).start();
            new MyServerWriter(dataOutputStream).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class MyServerReader extends  Thread{
    private DataInputStream dataInputStream;


    public MyServerReader(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    @Override
    public void run() {
        String info;
        try {
            while (true)
            {
                info = dataInputStream.readUTF();
                System.out.println("客户端说"+info);
                if (info.equals("bye")){
                    System.out.println("对方不想理你了");
                    System.exit(0);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class MyServerWriter extends Thread {
    private  DataOutputStream dataOutputStream;


    public MyServerWriter(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    @Override
    public void run() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String info;
        try {
            while (true)
            {
                info = bufferedReader.readLine();
                dataOutputStream.writeUTF(info);
                if (info.equals("bye"))
                {
                    System.out.println("感谢使用");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
