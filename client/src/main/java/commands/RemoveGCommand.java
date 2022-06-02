package commands;

import commands.exception.EmptyFieldCommandException;
import interaction.Request;
import managers.ClientCommandManager;
import model.ProductDto;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;
import workers.Asker;

import java.io.IOException;

/**
 * Удаляет  из коллекции все элементы, превышающие заданный
 */

public class RemoveGCommand extends AbstractCommand{
    RequestWriterModule writer;
    ResponseReaderModule reader;
    ClientCommandManager commandManager;

    public RemoveGCommand(RequestWriterModule writer,ResponseReaderModule reader, ClientCommandManager commandManager) {
        super("remove_greater","{element}" ,"remove all items from the collection that exceed the specified");
        this.writer = writer;
        this.reader = reader;
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute(String arguments) {
        try {
            if (arguments == null) {
        Asker asker;
        if (ClientCommandManager.fileMode) {
            asker = new Asker(commandManager.getScanners().getLast());
        } else {
            asker = new Asker(ClientCommandManager.console);
        }
        ProductDto dto = asker.makeDto();
        try {
            writer.sendRequest(new Request<>(getName(), "", dto));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
            } else
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
