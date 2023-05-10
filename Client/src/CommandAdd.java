import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The type Command add.
 */
public class CommandAdd extends Command{
    /**
     * The Reader.
     */
    BufferedReader reader;
    /**
     * The Is standard input.
     */
    boolean isStandardInput;

    /**
     * Instantiates a new Command add.
     *
     * @param reader          the reader
     * @param isStandardInput the is standard input
     */
    CommandAdd(BufferedReader reader, boolean isStandardInput) {
        this.reader = reader;
        this.isStandardInput = isStandardInput;
    }

    private <T extends Collectible & Comparable<T>> void fooHelper(DeqCollection<T> data){
        T tmp = (T)data.createContents();
        tmp.loadFromStandardInput(reader, isStandardInput);
        data.getStorage().add(tmp);
    }


    @Override
    public void execute() {
        fooHelper(data);
    }
}
