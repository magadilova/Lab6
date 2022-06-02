package managers;

import commands.AbstractCommand;
import commands.exception.WrongFieldCommandException;
import workers.ConsoleWorker;

import java.io.Console;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ClientCommandManager {
    LinkedHashMap<String, AbstractCommand> commands = new LinkedHashMap<>();
    static public Console console = System.console();
    static public boolean fileMode = false;
    private Deque<String> files = new ArrayDeque<>();
    private static Deque<Scanner> scanners = new ArrayDeque<>();

    public Deque<Scanner> getScanners() {
        return scanners;
    }


    public void addCommands(AbstractCommand[] commands) {
        for (AbstractCommand command : commands) {
            this.commands.put(command.getName(), command);
        }
    }

    public void startInteractiveMode() {
        while (true) {
            try {
                ConsoleWorker.println("Enter Command: ");
                ConsoleWorker.printSymbol(true);

//                String command = new Scanner(System.in).next().trim();

                String command = console.readLine().trim();
                executeCommand(command);
            } catch (NullPointerException e) {
//                startInteractiveMode();
                e.printStackTrace();
            }
        }
    }

    public static void setFileMode(boolean fileMode) {
        ClientCommandManager.fileMode = fileMode;
    }

    public boolean executeCommand(String command) {
        try {
            String[] userCommand = command.split(" ", 2);
            if (!commands.containsKey(userCommand[0])) {
                throw new WrongFieldCommandException("No such command: " + userCommand[0]);
            }
            return commands.get(userCommand[0]).execute(userCommand.length > 1 ? userCommand[1] : null );
        } catch (WrongFieldCommandException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public LinkedHashMap<String, AbstractCommand> getCommands() {
        return commands;
    }

    public static Console getConsole() {
        return console;
    }

    public static boolean isFileMode() {
        return fileMode;
    }

    public Deque<String> getFiles() {
        return files;
    }
}