package cn.colining.OneThreadChatRoom;

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
                info = bufferedReader.readLine();
                dataOutputStream.writeUTF(info);

                if (info == "bye")
                    break;
                info1 = dataInputStream.readUTF();

                System.out.println("服务器"+info1);
                if (info1 == "bye")
                    break;
            }
            bufferedReader.close();
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
