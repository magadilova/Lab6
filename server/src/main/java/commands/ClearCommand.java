package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;

/**
 * Очищает коллекцию.
 */

public class ClearCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetCollectionManager;
    CommandManager commandManager;


    public ClearCommand(manager.LinkedHashSetManager collectionManager, CommandManager commandManager) {
        super("clear", "", "clear the collection");
        this.linkedHashSetCollectionManager = collectionManager;
        this.commandManager = commandManager;

    }

    @Override
    public Response<?> execute(Request req) {

        try {
            if (req.getParams() == null) {
                if (linkedHashSetCollectionManager.getSet().isEmpty()) {
                    return new Response<>(false, "Collection is empty!");
                } else {
                    linkedHashSetCollectionManager.clear();
                    commandManager.addToHistory(getName());
                    return new Response<>(true, "Collection has cleared");
                }
            } else {
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
            }
        } catch (EmptyFieldCommandException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}
