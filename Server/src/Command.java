import java.io.Serial;
import java.io.Serializable;

/**
 * The type Command.
 */
public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1337;
    /**
     * The Data.
     */
    protected transient DeqCollection<?> data;

    void setData(DeqCollection<?> data){
      this.data = data;
    }

    /**
     * Instantiates a new Command.
     *
     * @param data the data
     */
    Command(DeqCollection<?> data){
        this.data = data;
    }

    /**
     * Execute.
     */
    public abstract void execute();
}
