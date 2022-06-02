package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Выводит справку по доступным командам.
 */

public class HelpCommand extends AbstractCommand{
    RequestWriterModule writer;
    ResponseReaderModule reader;


    public HelpCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("help","" ,"display help for available commands");
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
