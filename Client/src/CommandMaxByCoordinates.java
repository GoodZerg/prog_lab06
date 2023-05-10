import java.util.Optional;

/**
 * The type Command max by coordinates.
 */
public class CommandMaxByCoordinates extends Command{
    /**
     * Instantiates a new Command max by coordinates.
     *
     */
    CommandMaxByCoordinates() {

    }
    private <T extends Collectible & Comparable<T>> void fooHelper(DeqCollection<T> data){
        Optional<T> max = data.findMaxByCord();
        max.ifPresent(t -> System.out.println(t.toString()));
    }
    @Override
    public void execute() {
        fooHelper(data);
    }
}
