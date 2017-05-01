package cn.colining.test2;

/**
 * Created by asus on 2017/5/1.
 */
import java.io.*;
import java.net.Socket;

/**
 *
 */
public class TestClient {
    public static void main(String args[]) {
        try {
            // 创建socket对象，指定服务器的ip地址，和服务器监听的端口号
            // 客户端在new的时候，就发出了连接请求，服务器端就会进行处理，如果服务器端没有开启服务，那么
            // 这时候就会找不到服务器，并同时抛出异常==》java.net.ConnectException: Connection
            // refused: connect
            Socket s1 = new Socket("127.0.0.1", 8888);
            // 打开输入流
            InputStream is = s1.getInputStream();
            // 封装输入流
            DataInputStream dis = new DataInputStream(is);
            // 打印服务器端发送过来的信息
            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());
            // 关闭输入流
            dis.close();
            // 关闭打开的socket对象
            s1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
