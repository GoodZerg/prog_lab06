import java.io.Serial;

/**
 * The type Command show.
 */
public class CommandShow extends Command{
    @Serial
    private static final long serialVersionUID = 15;
    /**
     * Instantiates a new Command show.
     *
     */
    CommandShow() {

    }

    private <T extends Collectible & Comparable<T>> void fooHelper(DeqCollection<T> data){
        for (Collectible i : data.getStorage()){
            System.out.println(i.toString());
        }
    }

    @Override
    public void execute() {
        System.out.println("Collection: \n");
        fooHelper(data);
    }
}
