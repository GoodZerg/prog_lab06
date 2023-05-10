import java.io.Serializable;

/**
 * The type Command.
 */
public abstract class Command implements Serializable {
    /**
     * The Data.
     */
    protected DeqCollection<?> data;

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(DeqCollection<?> data) {
        this.data = data;
    }

    /**
     * Execute.
     */
    public abstract void execute();
}
