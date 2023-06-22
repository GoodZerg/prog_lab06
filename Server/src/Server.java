import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class Server extends Thread {
    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    private static final int BUFFER_CAPACITY = 8192;

    private final InvokerServer invoker;

    public Server(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        String file_name// = args[0];
                = "src\\start.txt"; //TODO REMOVE DEBUG
        invoker = new InvokerServer(new DeqCollection<Route>(Route::new, Route[]::new, new OutputHandler(file_name)));
    }

    private void process(Set<SelectionKey> readyKeys) throws IOException, SocketException {
        System.out.println("Processing...");
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            System.out.println("Processing key with code " + key.readyOps());
            if (key.isValid() && key.isAcceptable()) {
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = channel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(key.selector(), SelectionKey.OP_READ);
                System.out.println("Accepted new connection: " + socketChannel.getLocalAddress());
            }
            if (key.isValid() && key.isReadable()) {
                System.out.println("Reading from: " + key.channel() );
                //String msg = processRead(key);
                Command command = readCommand(key);

                if (command != null) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.wrap(("Client text '" + command.getClass() + "'").getBytes());
                    socketChannel.write(buffer);

                    invoker.execute(command);
                }

                //System.out.println("Received data: " + msg);


                ///TODO execute command DESERIALIZE
/*
                if (msg != null) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.wrap(("Client text '" + msg + "'").getBytes());
                    socketChannel.write(buffer);
                }

 */
            }
        }
    }

    public String processRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesCount = socketChannel.read(buffer);
            System.out.println("Bytes received: " + bytesCount);
            if (bytesCount > 0) {
                buffer.flip();
                return new String(Arrays.copyOf(buffer.array(), bytesCount));
            }
            socketChannel.close();
        } catch (IOException ex) {
            socketChannel.close();
        }
        return null;
    }

    public Command readCommand(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesCount = socketChannel.read(buffer);
            byte[] bytes = new byte[bytesCount];
            bytes = buffer.array();
            System.out.println("Bytes received: " + bytesCount);
            if (bytesCount > 0) {
                buffer.flip();
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new ByteArrayInputStream(bytes));
                return (Command) objectInputStream.readObject();
            }
            socketChannel.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            socketChannel.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void run() {
        while(selector.isOpen()){
            try {
                Thread.onSpinWait();
                if (selector.select() <= 0) {
                    continue;
                }
                process(selector.selectedKeys());
            } catch (SocketException ex) {
                System.out.println("Socket exception: " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("IO exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
