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
     */
    CommandExit() {
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
