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

        String serverName = "localhost";
        int port = 2222;

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(serverName,port));
        ///TODO check cannot connect to server
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //data.load(new FileReader(file_name));  //TODO Load on server (write file_name to server with)

        try {
            while (!socketChannel.finishConnect()) {
                /// TODO check server answer
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

    }

}
