/**
 * The type Command clear.
 */
public class CommandClear extends Command{
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
