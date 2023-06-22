import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.Vector;

/**
 * The type Invoker.
 */
public class Invoker {
    private static final Vector<Command> doneCommands = new Vector<Command>();

    /**
     * The constant command_num.
     */
    public static final int command_num = 16;

    /**
     * Get done commands vector.
     *
     * @return the vector
     */
    public static Vector<Command> getDoneCommands(){
        return doneCommands;
    }

    /**
     * The type Command information.
     */
    public static class _CommandInformation{
        /**
         * The Name.
         */
        public String name;
        /**
         * The Command class.
         */
        public Class<?> command_class;
        /**
         * The Argument count.
         */
        public int argument_count;

        /**
         * Instantiates a new Command information.
         *
         * @param name           the name
         * @param command_class  the command class
         * @param argument_count the argument count
         */
        _CommandInformation(String name, Class<?> command_class, int argument_count){
            this.name = name;
            this.command_class = command_class;
            this.argument_count = argument_count;
        }

    }

    /**
     * The Commands info.
     */
    public static Vector<_CommandInformation> commandsInfo;

    static{
        commandsInfo = new Vector<_CommandInformation>(command_num);
        commandsInfo.add(new _CommandInformation(
                "help",
                CommandHelp.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "info",
                CommandInfo.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "show",
                CommandShow.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "add",
                CommandAdd.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "update",
                CommandUpdate.class,
                1));
        commandsInfo.add(new _CommandInformation(
                "remove_by_id",
                CommandRemoveById.class,
                1));
        commandsInfo.add(new _CommandInformation(
                "clear",
                CommandClear.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "execute_script",
                CommandExecuteScript.class,
                1));
        commandsInfo.add(new _CommandInformation(
                "exit",
                CommandExit.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "add_if_max",
                CommandAddIfMax.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "remove_lower",
                CommandRemoveLower.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "history",
                CommandHistory.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "max_by_coordinates",
                CommandMaxByCoordinates.class,
                0));
        commandsInfo.add(new _CommandInformation(
                "count_by_distance",
                CommandCountByDistance.class,
                1));
        commandsInfo.add(new _CommandInformation(
                "print_field_descending_distance",
                CommandPrintFieldDescendingDistance.class,
                0));
    }

    /**
     * Find command by class command information.
     *
     * @param command_class the command class
     * @return the command information
     */
    public  static _CommandInformation findCommandByClass(Class<?> command_class){
        for (_CommandInformation i :
                commandsInfo){
            if(command_class == i.command_class) return i;
        }
        return new _CommandInformation("Error", Command.class, 0);
    }

    private SocketChannel socketChannel;

    public void setServerInfo(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }

    private void findCommandByName(String[] words, BufferedReader reader, boolean isStandardInput)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        String first_word = words[0];
        for (_CommandInformation i :
                commandsInfo) {
            if(Objects.equals(first_word, i.name)){
                if(words.length == i.argument_count + 1){
                    if(Objects.equals("add", i.name) ||
                            Objects.equals("add_if_max", i.name) ||
                            Objects.equals("remove_lower", i.name)){
                        execute((Command) i.command_class.getDeclaredConstructor(
                                BufferedReader.class, boolean.class
                                ).newInstance(reader, isStandardInput));
                        return;
                    } else if(words.length == 1){
                        execute((Command) i.command_class.getDeclaredConstructor()
                                .newInstance());
                        return;
                    } else if (Objects.equals("update", i.name)){
                        execute((Command) i.command_class.getDeclaredConstructor(
                                Long.class, BufferedReader.class, boolean.class)
                                .newInstance(Long.parseLong(words[1]), reader, isStandardInput));
                        return;
                    } else if (Objects.equals("remove_by_id", i.name)){
                        execute((Command) i.command_class.getDeclaredConstructor(Long.class)
                                .newInstance(Long.parseLong(words[1])));
                        return;
                    }else if (Objects.equals("count_by_distance", i.name)) {
                        execute((Command) i.command_class.getDeclaredConstructor(Integer.class)
                                .newInstance(Integer.parseInt(words[1])));
                        return;
                    } else if(Objects.equals("execute_script", i.name)){
                        execute((Command) i.command_class.getDeclaredConstructor(String.class)
                                .newInstance(words[1]));
                        return;
                    }
                } else {
                    throw new IllegalArgumentException("wrong command args");
                }
            }
        }
        throw new IllegalArgumentException("wrong command");
    }

    /**
     * Execute.
     *
     * @param command the command
     */
    public void execute(Command command) {
        doneCommands.add(command);
        if(command.getClass() == CommandExit.class){
            System.exit(0);
        }
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(command);
            os.flush();

            ByteBuffer buffer = ByteBuffer.wrap(out.toByteArray());
            System.out.println(out);

            socketChannel.write(buffer);
        } catch (IOException e){
            System.out.println("Smt wrong");
        }
        ///TODO Arg obj? or reading in server part


        //command.execute(); Client execute lab_05
    }

    /**
     * Instantiates a new Invoker.
     *
     */
    public Invoker() {

    }

    /**
     * Parse command.
     *
     * @param str             the str
     * @param reader          the reader
     * @param isStandardInput the is standard input
     * @throws InvocationTargetException the invocation target exception
     * @throws NoSuchMethodException     the no such method exception
     * @throws InstantiationException    the instantiation exception
     * @throws IllegalAccessException    the illegal access exception
     */
    public void parseCommand(String str, BufferedReader reader, boolean isStandardInput)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(str == null)
            throw new RuntimeException("null command");
        String[] words = str.split(" ");
        findCommandByName(words, reader, isStandardInput);
    }
}

