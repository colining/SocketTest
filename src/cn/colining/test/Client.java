package cn.colining.test;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by asus on 2017/5/1.
 */
public class Client {
    Socket client;

    public Client() {
        try {
            client = new Socket("localhost", 7777);
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String a;
            while (true) {
                a = bufferedReader.readLine();
                if (a.equals("bye"))
                    break;
                dataOutputStream.writeUTF(a);
            }
            dataOutputStream.close();
            bufferedReader.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

            new Client();

    }
}