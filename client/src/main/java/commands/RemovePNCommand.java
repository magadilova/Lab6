package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Удаляет элементы, значение поля partNumber которого эквивалентно заданному
 */

public class RemovePNCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;


    public RemovePNCommand(RequestWriterModule writer,ResponseReaderModule reader) {
        super("remove_any_by_part_number","partNumber" ,"remove one element from the collection whose " +
                "partNumber field value is equivalent to the given");
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
