import java.io.Serial;

/**
 * The type Command info.
 */
public class CommandInfo extends Command{
    /**
     * Instantiates a new Command info.
     *
     */

    @Serial
    private static final long serialVersionUID = 9;
    CommandInfo() {
    }

    @Override
    public void execute() {
        System.out.println("type : " + data.getStorage().getClass());
        System.out.println("creationDate : " + data.getCreationDate());
        System.out.println("number of elements : " + data.getStorage().size());
    }
}
