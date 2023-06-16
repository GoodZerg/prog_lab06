import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The type Input handler.
 */
public class InputHandler {
    private final Invoker parser;
    private static final int BUFFER_CAPACITY = 8192;

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
        try {
            if(socketChannel.finishConnect()) {
                System.out.println("Connected!");
            }
        } catch (ConnectException ex) {
            System.out.println("Connection error: " + ex.getMessage());
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //data.load(new FileReader(file_name));  //TODO Load on server (write file_name to server with)
/*
        try {
            while (!socketChannel.finishConnect()) {
                Thread.onSpinWait();
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
        String request;

        var scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter request: ");
 //           request = scanner.nextLine();
            parser.setServerInfo(socketChannel);

            try {
                parser.parseCommand(reader.readLine(), reader, true);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                continue;
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }


/*
            ByteBuffer buffer1 = ByteBuffer.wrap(request.getBytes());
            socketChannel.write(buffer1);
*/

            ///TODO check hav answer
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesCount = 0;
            int iter = 0;
            while (bytesCount <= 0) {
                bytesCount = socketChannel.read(buffer);
                //if(++iter == 30) break;;
            }
            System.out.println("Bytes received: " + bytesCount);
            buffer.flip();
            var response = new String(Arrays.copyOf(buffer.array(), bytesCount));
            System.out.println("Server responded: " + response);
        }

//        socketChannel.close();
    }

}
