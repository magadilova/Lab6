package commands;


import client.Client;
import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 *  Завершает работу программы.
 */
public class ExitCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;


    public ExitCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("exit","" ,"exit program without saving collection into file" );
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
        Client.close();
        System.exit(0);
        return true;
    }
}
