package commands;


import commands.exception.EmptyFieldCommandException;
import interaction.Request;
import managers.ClientCommandManager;
import model.ProductDto;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;
import workers.Asker;

import java.io.IOException;

public class UpdateCommand extends  AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;
    ClientCommandManager commandManager;

    public UpdateCommand(RequestWriterModule writer, ResponseReaderModule reader, ClientCommandManager commandManager) {
        super("update_id", "id {element}", "update the value of the collection item " +
                "whose id is the same as the given one");
        this.writer = writer;
        this.reader = reader;
        this.commandManager = commandManager;
    }


    @Override
    public boolean execute(String arguments) {
        try {
            if (arguments != null) {
                Asker asker;
                if (ClientCommandManager.fileMode) {
                    asker = new Asker(commandManager.getScanners().getLast());
                } else {
                    asker = new Asker(ClientCommandManager.console);
                }
                ProductDto dto = asker.makeDto();
                try {
                    writer.sendRequest(new Request<>(getName(), arguments, dto));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result(reader);
            } else
                throw new EmptyFieldCommandException("Exception: This command needs the value \" id \"");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
