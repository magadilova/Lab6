package commands;

import interaction.Request;
import interaction.Response;
import manager.LinkedHashSetManager;
import module.CommandWorkerModule;
import utils.FileWorker;
import utils.XmlWorker;

import java.io.IOException;

public class ExitWithSaveCommand extends AbstractCommand {
    LinkedHashSetManager collectionManager;
    FileWorker fileWorker;
    XmlWorker xmlWorker;
    String fileName;

    public ExitWithSaveCommand(LinkedHashSetManager collectionManager, FileWorker fileWorker, XmlWorker xmlWorker, String fileName) {
        super("exit_with_save", "", "closes the server and saves progress");
        this.collectionManager = collectionManager;
        this.fileWorker = fileWorker;
        this.xmlWorker = xmlWorker;
        this.fileName = fileName;
    }

    @Override
    public Response execute(Request req) {
        try {
            String xml = xmlWorker.toXml(collectionManager.getSet(), new Class[]{
                    model.Person.class,
                    model.Product.class,
                    model.Coordinates.class,
                    model.EyeColor.class,
                    model.HairColor.class,
                    model.Country.class,
                    model.UnitOfMeasure.class,
                    model.Location.class,
                    collectionManager.getSet().getClass()
            });
            fileWorker.saveFile(fileName, xml);
            System.out.println("Server is closing");
            try {
                CommandWorkerModule.close();
            } catch (NullPointerException e) {

            }
            System.exit(0);
            return null;
        } catch (SecurityException exception) {
            System.out.println("Permission denied");
            return null;
        } catch (IOException exception) {
            System.out.println("Oops, failure");
            return null;
        }
    }
}
