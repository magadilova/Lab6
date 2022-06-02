import commands.*;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import model.*;
import utils.FileWorker;
import utils.XmlWorker;

import java.io.Console;
import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        try {


            FileWorker fileWorker;
            fileWorker = new FileWorker();

            LinkedHashSetManager collectionManager = new LinkedHashSetManager();
            XmlWorker xmlWorker = new XmlWorker(collectionManager.getSet().getClass(), Product.class);
            collectionManager.load((xmlWorker.fromXML(FileWorker.readFile(args[1]),
                    new Class[]{
                            Person.class,
                            Product.class,
                            Coordinates.class,
                            EyeColor.class,
                            HairColor.class,
                            Country.class,
                            UnitOfMeasure.class,
                            Location.class,
                            collectionManager.getSet().getClass()})));

            CommandManager commandManager = new CommandManager();

            commandManager.addCommands(new commands.AbstractCommand[]{
                    new commands.HelpCommand(commandManager),
                    new commands.InfoCommand(collectionManager, commandManager),
                    new commands.ShowCommand(commandManager, collectionManager),
                    new commands.AddCommand(collectionManager, commandManager),
                    new commands.UpdateCommand(collectionManager, commandManager),
                    new commands.RemoveIDCommand(collectionManager, commandManager),
                    new commands.ExecuteCommand(),
                    new ClearCommand(collectionManager, commandManager),
                    new commands.RemoveGCommand(collectionManager, commandManager),
                    new commands.RemoveLCommand(collectionManager, commandManager),
                    new commands.RemovePNCommand(collectionManager, commandManager),
                    new commands.FilterLCommand(collectionManager.getSet(), commandManager),
                    new commands.FilterPNCommand(collectionManager, commandManager),
                    new commands.HistoryCommand(commandManager),
                    new commands.ExitCommand(),
            });

            FileWorker finalFileWorker = fileWorker;


            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        finalFileWorker.saveFile(args[1], xmlWorker.toXml(collectionManager.getSet(), new Class[]{
                                model.Person.class,
                                model.Product.class,
                                model.Coordinates.class,
                                model.EyeColor.class,
                                model.HairColor.class,
                                model.Country.class,
                                model.UnitOfMeasure.class,
                                model.Location.class,
                                collectionManager.getSet().getClass()
                        }));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Console console = System.console();
                        String input = console.readLine().trim();
                            if (input.equalsIgnoreCase("save")) {
                                new SaveCommand(finalFileWorker, xmlWorker, collectionManager, commandManager, args[1]).execute(null);
                            }
                            else if (input.equalsIgnoreCase("exit")) {
                                new ExitWithSaveCommand(collectionManager, finalFileWorker, xmlWorker, args[1]).execute(null);
                            }
                            else {
                                System.out.println("No such command");
                            }

                    }
                }
            }).start();

            Server server = new Server(Integer.parseInt(args[0]), commandManager);
            server.start();
            server.connect();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File not found X_X");
        }
    }
}
