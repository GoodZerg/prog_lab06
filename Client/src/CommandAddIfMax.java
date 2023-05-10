import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * The type Command add if max.
 */
public class CommandAddIfMax extends Command{
    /**
     * The Reader.
     */
    BufferedReader reader;
    /**
     * The Is standard input.
     */
    boolean isStandardInput;

    /**
     * Instantiates a new Command add if max.
     *
     * @param reader          the reader
     * @param isStandardInput the is standard input
     */
    CommandAddIfMax(BufferedReader reader, boolean isStandardInput) {
        this.reader = reader;
        this.isStandardInput = isStandardInput;
    }

    private <T extends Collectible & Comparable<T>> void fooHelper(DeqCollection<T> data){
        T tmp = (T)data.createContents();
        tmp.loadFromStandardInput(reader, isStandardInput);
        Optional<T> max = data.findMax();
        T _max;
        if(max.isPresent()){
            _max = max.get();
        } else {
            data.getStorage().add(tmp);
            return;
        }
        if(tmp.compareTo(_max) > 0 ) data.getStorage().add(tmp);
    }

    @Override
    public void execute() {
        fooHelper(data);
    }
}
