import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
        /*
        if(args.length != 1){
            System.out.println("Need param");
            return;
        }
        String file_name = args[0];
//            = "src\\start.txt"; //TODO REMOVE DEBUG
        try {
            InputHandler inputHandler = new InputHandler(new DeqCollection<Route>(Route::new, Route[]::new, new OutputHandler(file_name)));
            inputHandler.start(file_name);
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        */
            String file_name// = args[0];
                = "src\\start.txt"; //TODO REMOVE DEBUG
            try {
                // new DeqCollection<Route>(Route::new, Route[]::new, new OutputHandler(file_name))
                InputHandler inputHandler = new InputHandler();
                inputHandler.start(file_name);
            }catch (RuntimeException ex){
                System.out.println(ex.getMessage());
            }

/*
            String serverName = "localhost";
            int port = 2222;

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

 */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}