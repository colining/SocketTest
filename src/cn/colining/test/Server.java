package cn.colining.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by asus on 2017/5/1.
 */
public class Server {
    private Socket socket;
    private ServerSocket ss;

    public Server() throws IOException {
        ss = new ServerSocket(7777);
        socket = ss.accept();
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        while (true) {


            //BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String a = dataInputStream.readUTF();
            if (a.equals("bye"))
                break;
            System.out.println(a);
            //System.out.println("客户端发来的信息是：" + br.readLine());

        }
        //br.close();
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
