package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;


/**
 * Выводит элементы, значение поля partNumber которых начинается с заданной подстроки
 */

public class FilterPNCommand extends AbstractCommand {

    LinkedHashSetManager linkedHashSetManager;
    CommandManager commandManager;

    public FilterPNCommand(LinkedHashSetManager linkedHashSetManager, CommandManager commandManager) {
        super("filter_starts_with_part_number", "partNumber", "output the elements whose " +
                "partNumber field value starts with the given substring");
        this.linkedHashSetManager = linkedHashSetManager;
        this.commandManager = commandManager;

    }

    @Override
    public Response<?> execute(Request req) {
        StringBuilder sb = new StringBuilder();
        try {
            if (req.getParams() != null) {
                int count = 0;
                for (model.Product item : linkedHashSetManager.getSet()) {
                    if (item.getPartNumber().startsWith(req.getParams().trim())) {
                        sb.append(item);
                        count++;
                    }
                }
                commandManager.addToHistory(getName());
                return new Response<>(true, "Inferred elements, the value of the partNumber field starts with the given substring.", sb +
                        "\nThe number of elements that satisfy the condition: " + count);
            } else {
                throw new EmptyFieldCommandException("Exception: This command needs the value \" Part number \"");
            }
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}
