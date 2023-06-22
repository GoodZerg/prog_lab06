import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serial;
import java.util.Optional;

/**
 * The type Command remove lower.
 */
public class CommandRemoveLower extends Command{
    @Serial
    private static final long serialVersionUID = 13;
    /**
     * The Reader.
     */
    BufferedReader reader;
    /**
     * The Is standard input.
     */
    boolean isStandardInput;

    /**
     * Instantiates a new Command remove lower.
     *
     * @param reader          the reader
     * @param isStandardInput the is standard input
     */
    CommandRemoveLower(BufferedReader reader, boolean isStandardInput) {
        this.reader = reader;
        this.isStandardInput = isStandardInput;
    }
    private <T extends Collectible & Comparable<T>> void fooHelper(DeqCollection<T> data){
        T[] arr = data.getStorage().toArray(data.createContentsArray(data.getStorage().size()));

        T tmp = (T)data.createContents();
        tmp.loadFromStandardInput(reader, isStandardInput);

        for (T i : arr) {
            if(tmp.compareTo(i) > 0){
                data.getStorage().remove(i);
            }
        }
    }
    @Override
    public void execute() {
        fooHelper(data);
    }
}
