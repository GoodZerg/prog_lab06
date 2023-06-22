import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serial;

/**
 * The type Command update.
 */
public class CommandUpdate extends Command{
    @Serial
    private static final long serialVersionUID = 16;
    private Long id;
    /**
     * The Reader.
     */
    BufferedReader reader;
    /**
     * The Is standard input.
     */
    boolean isStandardInput;

    /**
     * Instantiates a new Command update.
     *
     * @param id              the id
     * @param reader          the reader
     * @param isStandardInput the is standard input
     */
    CommandUpdate(Long id, BufferedReader reader, boolean isStandardInput) {
        this.id = id;
        this.reader = reader;
        this.isStandardInput = isStandardInput;
    }

    private <T extends Collectible & Comparable<T>> void fooHelper(DeqCollection<T> data){
        T[] arr = data.getStorage().toArray(data.createContentsArray(data.getStorage().size()));
        for (T i : arr) {
            if(i.getId() == id){
                i.loadFromStandardInput(reader, isStandardInput);
                return;
            }
        }
        System.out.println("Такого id нет");
    }

    @Override
    public void execute() {
        fooHelper(data);
    }
}
