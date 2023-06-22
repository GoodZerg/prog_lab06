import java.io.Serial;

/**
 * The type Command save.
 */
public class CommandSave extends Command{
    @Serial
    private static final long serialVersionUID = 14;
    /**
     * Instantiates a new Command save.
     *
     * @param data the data
     */
    CommandSave(DeqCollection<?> data) {
        super(data);
    }

    @Override
    public void execute() {
        data.save();
    }
}
