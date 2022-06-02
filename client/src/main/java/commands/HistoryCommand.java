package commands;

import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Выводит последние 12 команд
 */

public class HistoryCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;

    public HistoryCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("history","" ,"print the last 12 commands");
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
