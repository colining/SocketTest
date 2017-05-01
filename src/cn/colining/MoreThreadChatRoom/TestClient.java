package cn.colining.MoreThreadChatRoom;

import com.sun.corba.se.impl.activation.ServerMain;
import com.sun.corba.se.impl.activation.ServerManagerImpl;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by asus on 2017/5/1.
 */
public class TestClient {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost",8888);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            new MyClientReader(dataInputStream).start();
            new MyClientWriter(dataOutputStream).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyClientReader extends  Thread{
    private DataInputStream dataInputStream;


    public MyClientReader(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    @Override
    public void run() {
        String info;
        try {
            while (true)
            {
                info = dataInputStream.readUTF();
                System.out.println("服务器说:"+info);
                if (info.equals("bye")){
                    System.out.println("对方不想理你了");
                    System.exit(0);
                }

            }
        } catch (IOException e) {
            System.out.println("服务器死翘翘了");
            e.printStackTrace();
        }

    }
}

class MyClientWriter extends Thread {
    private  DataOutputStream dataOutputStream;


    public MyClientWriter(DataOutputStream dataOutputStream) {
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