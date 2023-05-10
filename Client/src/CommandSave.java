/**
 * The type Command save.
 */
public class CommandSave extends Command{
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
