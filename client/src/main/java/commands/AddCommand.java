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
 * Добавляет в коллекцию новый элемент.
 */

public class AddCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;
    ClientCommandManager commandManager;


    public AddCommand(RequestWriterModule writer, ResponseReaderModule reader, ClientCommandManager commandManager) {
        super("add", "{element}", "add a new element to the collection.");
        this.writer = writer;
        this.reader = reader;
        this.commandManager = commandManager;
    }


    @Override
    public boolean execute(String arguments) {
        try {
            if (arguments == null) {
                Asker asker;
                if (ClientCommandManager.fileMode)
                    asker = new Asker(commandManager.getScanners().getLast());
                else
                    asker = new Asker(ClientCommandManager.console);
                ProductDto dto = asker.makeDto();
                try {
                    writer.sendRequest(new Request<>(getName(), arguments, dto));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result(reader);
            }else
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
