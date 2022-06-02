package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Выводит элементы, значение поля partNumber которых начинается с заданной подстроки
 */

public class FilterPNCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;

    public FilterPNCommand(RequestWriterModule writer, ResponseReaderModule reader) {
        super("filter_starts_with_part_number", "partNumber", "output the elements whose " +
                "partNumber field value starts with the given substring");
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
