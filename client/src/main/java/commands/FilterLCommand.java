package commands;


import interaction.Request;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * выводит элементы, значение поля price которых меньше заданного
 */

public class FilterLCommand extends AbstractCommand {
    RequestWriterModule writer;
    ResponseReaderModule reader;

    public FilterLCommand(RequestWriterModule writer, ResponseReaderModule reader) {
        super("filter_less_than_price", "price", "output the elements whose value of the price " +
                "field is less than the given value");
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
