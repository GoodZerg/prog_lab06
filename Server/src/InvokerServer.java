import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Vector;


public class InvokerServer {
    private final DeqCollection<?> data;
    private static final Vector<Command> doneCommands = new Vector<Command>();

    /**
     * The constant command_num.
     */
    public static final int command_num = 16;

    /**
     * Get done commands vector.
     *
     * @return the vector
     */
    public static Vector<Command> getDoneCommands(){
        return doneCommands;
    }

    /**
     * Execute.
     *
     * @param command the command
     */
    public void execute(Command command){
        doneCommands.add(command);
        command.setData(data);
        command.execute();
    }

    /**
     * Instantiates a new Invoker.
     *
     * @param data the data
     */
    public InvokerServer(DeqCollection<?> data) {
        this.data = data;
    }

}
