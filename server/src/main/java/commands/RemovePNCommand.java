package commands;


import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;
import manager.exception.WrongFieldCommandException;

/**
 * Удаляет элементы, значение поля partNumber которого эквивалентно заданному
 */

public class RemovePNCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetManager;
    CommandManager commandManager;

    public RemovePNCommand(LinkedHashSetManager linkedHashSetManager, CommandManager commandManager) {
        super("remove_any_by_part_number", "partNumber", "remove one element from the collection whose " +
                "partNumber field value is equivalent to the given");
        this.linkedHashSetManager = linkedHashSetManager;
        this.commandManager = commandManager;
    }


    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() != null) {
                linkedHashSetManager.deleteByPN(req.getParams().trim());
                commandManager.addToHistory(getName());
                return new Response<>(true,"One element removed, partNumber field value that is equivalent to the given");
            } else throw new EmptyFieldCommandException("Exception: This command requires the value of the \" Part Number\" field");
        } catch (EmptyFieldCommandException | WrongFieldCommandException e){
            System.out.println(e.getMessage());
            return new Response<>(false,e.getMessage());
        }
    }
}
