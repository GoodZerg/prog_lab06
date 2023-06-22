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
     * @param data the data
     */
    CommandClear(DeqCollection<?> data) {
        super(data);
    }

    @Override
    public void execute() {
        data.getStorage().clear();
    }
}
