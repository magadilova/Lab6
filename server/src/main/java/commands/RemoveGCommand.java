package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;
import model.Product;
import model.ProductDto;

import java.util.ArrayList;

/**
 * Удаляет  из коллекции все элементы, превышающие заданный
 */

public class RemoveGCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetManager;
    CommandManager commandManager;


    public RemoveGCommand(LinkedHashSetManager linkedHashSetManager, CommandManager commandManager) {
        super("remove_greater", "{element}", "remove all items from the collection that exceed the specified");
        this.linkedHashSetManager = linkedHashSetManager;
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request req) {
        try {
            if (req.getParams() == null) {
                if (linkedHashSetManager.getSet().size() == 0) {
                    throw new IllegalArgumentException("Oops! Collection is empty");
                }
                Product productToCompare = commandManager.createProductForRemoving((ProductDto) req.getBody());

                ArrayList<Product> listToRemove = new ArrayList<>();

                linkedHashSetManager.getSet().stream()
                        .filter((Product person) -> person.getPrice() > productToCompare.getPrice())
                        .forEach(listToRemove::add);

                listToRemove.forEach(linkedHashSetManager::deleteObject);

                commandManager.addToHistory(getName());
                return new Response<>(true, "Removed from collection all elements set given!");
            } else {
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
            }
        } catch (EmptyFieldCommandException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}
