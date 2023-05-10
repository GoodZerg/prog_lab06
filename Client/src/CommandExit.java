/**
 * The type Command exit.
 */
public class CommandExit extends Command{
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
