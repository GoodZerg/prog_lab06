import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String file_name// = args[0];
                = "src\\start.txt"; //TODO REMOVE DEBUG
            try {
                // new DeqCollection<Route>(Route::new, Route[]::new, new OutputHandler(file_name))
                InputHandler inputHandler = new InputHandler();
                inputHandler.start(file_name);
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}