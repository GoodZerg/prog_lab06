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
     */
    CommandSave() {

    }

    @Override
    public void execute() {
        data.save();
    }
}
