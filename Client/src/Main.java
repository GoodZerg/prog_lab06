import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 2222;
        try {
            System.out.println("Подключение к " + serverName + " на порт " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Просто подключается к " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Привет из " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Сервер ответил " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}