import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * The type Input handler.
 */
public class InputHandler {
    private final Invoker parser;

    /**
     * Instantiates a new Input handler.
     *
     */
    InputHandler(){
        this.parser = new Invoker();
    }

    /**
     * Start.
     *
     * @param file_name the file name
     */
    public void start(String file_name) throws IOException {
        //data.load(new FileReader(file_name));  //TODO ?
        String serverName = "localhost";
        int port = 2222;

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(serverName,port));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }

/*
        try {
            while (true) {
                try {
                    parser.parseCommand(reader.readLine(), reader, true);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (java.io.IOException ex){
            System.out.println("Error in reading command");
        }
        */
    }

}
