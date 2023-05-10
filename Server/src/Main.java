import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int port = 2222;
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}