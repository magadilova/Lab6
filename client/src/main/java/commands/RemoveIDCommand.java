package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Удаляет элемент из коллекции по его id.
 */

public class RemoveIDCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;

    public RemoveIDCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("remove_by_id","id" ,"remove element from collection by its id.");
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public boolean execute(String arguments) {
        try {
            writer.sendRequest(new Request<>(getName(), arguments));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
    }
}
