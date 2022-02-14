package command;

public class CommandManager {

    private static final String[] commandNames;

    static {
        CommandType[] commands = CommandType.values();
        commandNames = new String[commands.length];
        for (int i = 0; i < commands.length; i++) {
            commandNames[i] = commands[i].name();
        }
    }

    public static Command getCommand(String commandName) {
        CommandType commandType = CommandType.NO_COMMAND;
        if (isCommandExist(commandName)) {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        }
        return commandType.getCommand();
    }

    private static boolean isCommandExist(String commandName) {
        for (String name : commandNames) {
            if (name.equalsIgnoreCase(commandName)) {
                return true;
            }
        }
        return false;
    }
}
