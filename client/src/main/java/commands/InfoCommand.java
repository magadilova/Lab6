package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Выводит в стандартный поток вывода информацию о коллекции.
 */

public class InfoCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;

    public InfoCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("info","" ,"output collection information" +
                " (type, initialisation date, number of items)" + " to the standard output stream");
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public boolean execute(String arguments) {
        try {
            writer.sendRequest(new Request<>(getName(),arguments));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
    }
}
