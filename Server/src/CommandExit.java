import java.io.Serial;

/**
 * The type Command exit.
 */
public class CommandExit extends Command{
    @Serial
    private static final long serialVersionUID = 6;
    /**
     * Instantiates a new Command exit.
     *
     * @param data the data
     */
    CommandExit(DeqCollection<?> data) {
        super(data);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
