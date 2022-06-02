package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;
import utils.FileWorker;
import utils.XmlWorker;

import java.io.IOException;


public class SaveCommand extends AbstractCommand {
    utils.FileWorker fileWorker;
    utils.XmlWorker xmlWorker;
    LinkedHashSetManager linkedHashSetManager;
    CommandManager commandManager;
    String fileName;


    public SaveCommand(FileWorker fileWorker,
                       XmlWorker xmlWorker,
                       LinkedHashSetManager linkedHashSetManager,
                       CommandManager commandManager,
                       String fileName) {
        super("save", "", "save the collection to a file");
        this.fileWorker = fileWorker;
        this.xmlWorker = xmlWorker;
        this.linkedHashSetManager = linkedHashSetManager;
        this.commandManager = commandManager;
        this.fileName = fileName;
    }


    @Override
    public Response execute(Request req) {
        try {

            linkedHashSetManager.sortSet();
            String xml = xmlWorker.toXml(linkedHashSetManager.getSet(), new Class[]{
                    model.Person.class,
                    model.Product.class,
                    model.Coordinates.class,
                    model.EyeColor.class,
                    model.HairColor.class,
                    model.Country.class,
                    model.UnitOfMeasure.class,
                    model.Location.class,
                    linkedHashSetManager.getSet().getClass()
            });

            fileWorker.saveFile(fileName, xml);
            commandManager.addToHistory(getName());
            System.out.println("Collection saved");
            return null;
        } catch (EmptyFieldCommandException | IOException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}