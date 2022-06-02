package commands;


import managers.ClientCommandManager;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteCommand extends AbstractCommand {
    ClientCommandManager commandManager;

    public ExecuteCommand(ClientCommandManager commandManager) {
        super("execute_script", "file_name","read and execute script from file setup.");
        this.commandManager = commandManager;
    }


    @Override
    public boolean execute(String arguments) {
        ClientCommandManager.setFileMode(true);
        try {
            File file = new File(arguments);
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (commandManager.getFiles().contains(file.getAbsolutePath()))
                throw new ScriptException("Scripts can't be recursive!!!");
            else {
                if (!file.exists()) {
                    throw new FileNotFoundException("File not found. x_X ");
                }

                commandManager.getScanners().add(new Scanner(file));
                commandManager.getFiles().add(file.getAbsolutePath());
            }
        } catch (IllegalArgumentException | FileNotFoundException  e) {
            System.err.println(e.getMessage());
            return false;
        } catch (ScriptException e) {
            System.err.println(e.getMessage());
            commandManager.startInteractiveMode();
            return false;
        }
        while (commandManager.getScanners().getLast().hasNextLine()) {
            String command = commandManager.getScanners().getLast().nextLine();
                commandManager.executeCommand(command);


        }
        commandManager.getScanners().removeLast();
        commandManager.getFiles().removeLast();
        ClientCommandManager.setFileMode(false);
        return true;
















////        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        ArrayList<String> nameFile = new ArrayList();
//        nameFile.add(arguments);
//        File file = new File(arguments);
//        try {
//            if (!file.exists()) {
//                throw new FileNotFoundException("File not found.");
//            }
//            Scanner scanner = new Scanner(file);
//            if (!arguments.equals("")) {
//                while (scanner.hasNext()) {
//                    String element = scanner.nextLine();
//                    String[] strCommand = element.trim().split(" ", 2);
//                    if (strCommand[0].equals("execute_script") && strCommand[1].equals(arguments)) {
//                        throw new EmptyFieldCommandException("You have already run the script");
//                    }
//                    if (strCommand[0].equals("execute_script")) {
//                        for (String str : nameFile) {
//                            str.equals(strCommand[1]);
//                            throw new ScriptException("Scripts can't be recursive!!!");
//                        }
//                    }
//                    if (strCommand[0].equals("add")) {
//
//                        Product product1 = new Product(scanner.nextLine(),
//                                new Coordinates(Integer.parseInt(scanner.nextLine()), Long.parseLong(scanner.nextLine())),
//                                Double.parseDouble(scanner.nextLine()), scanner.nextLine(), Long.parseLong(scanner.nextLine()),
//                                UnitOfMeasure.valueOf(scanner.nextLine()),
//                                new Person(scanner.nextLine(), EyeColor.valueOf(scanner.nextLine()), HairColor.valueOf(scanner.nextLine()),
//                                        Country.valueOf(scanner.nextLine()), new Location(Float.parseFloat(scanner.nextLine()), Double.parseDouble(scanner.nextLine()), Double.parseDouble(scanner.nextLine()))));
//                        linkedHashSetCollectionManager.getSet().add(product1);
//                        linkedHashSetCollectionManager.sortSet();
//                        System.out.println("The element was successfully added.");
//                    } else consoleClient.executeCommand(element);
//                    consoleClient.addToHistory(getName());
//                }
//            }
//        } catch (ScriptException | FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;

    }
}