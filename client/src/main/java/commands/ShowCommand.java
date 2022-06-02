package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class ShowCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;

    public ShowCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("show","" ,"output to the standard output stream all the elements" +
                " of the collection in a string representation");
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
