package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;
import manager.exception.WrongFieldCommandException;

/**
 * Удаляет элемент из коллекции по его id.
 */

public class RemoveIDCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetManager;
    CommandManager commandManager;

    public RemoveIDCommand(LinkedHashSetManager linkedHashSetCollectionManager, CommandManager commandManager) {
        super("remove_by_id", "id", "remove element from collection by its id.");
        this.linkedHashSetManager = linkedHashSetCollectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request req) {
        try {
            if (req.getParams() != null) {
                linkedHashSetManager.deleteByID(Long.parseLong(req.getParams().trim()));
                commandManager.addToHistory(getName());
                return new Response<>(true, "Element was successfully deleted");
            } else {
                throw new EmptyFieldCommandException("Exception: This command needs the value \" id \"");
            }
        } catch (WrongFieldCommandException | EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}