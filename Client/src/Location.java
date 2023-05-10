/**
 * The type Location.
 */
public class Location {
    private Long x; //Поле не может быть null
    private int y;
    private String name; //Строка не может быть пустой, Поле не может быть null

    /**
     * Gets x.
     *
     * @return the x
     */
    public Long getX() {
        return x;
    }

    /**
     * Instantiates a new Location.
     *
     * @param x    the x
     * @param y    the y
     * @param name the name
     */
    Location(Long x, int y, String name){
        setX(x);
        setY(y);
        setName(name);
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(Long x) {
        if(x == null)
            throw new RuntimeException("Error Location");
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        if(name == null || name.equals(""))
            throw new RuntimeException("Error Location:Name");
        this.name = name;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("     X: ");    sb.append(getX());    sb.append("\n");
        sb.append("     Y: ");    sb.append(getY());    sb.append("\n");
        sb.append("     Name:\" "); sb.append(getName());
        return sb.toString();
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCsv() {
        return getX() + "," + getY() + "," + getName();
    }
}
