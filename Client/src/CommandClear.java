import java.io.Serial;

/**
 * The type Command clear.
 */
public class CommandClear extends Command{
    @Serial
    private static final long serialVersionUID = 3;
    /**
     * Instantiates a new Command clear.
     *
     */
    CommandClear() {

    }

    @Override
    public void execute() {
        data.getStorage().clear();
    }
}
