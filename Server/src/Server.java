import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server extends Thread {
    private final ServerSocketChannel serverSocketChannel;

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
                    ///TODO Handle command
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
