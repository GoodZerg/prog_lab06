import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server extends Thread {
    private ServerSocketChannel serverSocketChannel;

    public Server(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
    }

    public void run() {
        try {
            while(true){
                SocketChannel socketChannel =
                        serverSocketChannel.accept();
                if(socketChannel != null){
                    System.out.println(socketChannel.getLocalAddress());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*while(true) {
            try {
                System.out.println("Ожидание клиента на порт " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Просто подключается к " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Спасибо за подключение к " + server.getLocalSocketAddress()
                        + "\nПока!");
                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Время сокета истекло!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }*/
    }
}
