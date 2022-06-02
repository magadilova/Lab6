package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Очищает коллекцию.
 */

public class ClearCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;

    public ClearCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("clear","" ,"clears collection" );
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public boolean execute(String argument) {
        try {
            writer.sendRequest(new Request<>(getName(),argument));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
    }
}