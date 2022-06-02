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
 * Удалены вес элементы, меньшие, чем заданный
 */
public class RemoveLCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetManager;
    CommandManager commandManager;

    public RemoveLCommand(LinkedHashSetManager linkedHashSetManager, CommandManager commandManager) {
        super("remove_lower", "{element}", "remove all items from the collection " +
                "that are smaller than the specified");
        this.linkedHashSetManager = linkedHashSetManager;
        this.commandManager = commandManager;
    }


    @Override
    public Response execute(Request req) {
        try {
            if (req.getParams() == null) {
                if (linkedHashSetManager.getSet().size() == 0) {
                    throw new IllegalArgumentException("Collection is empty");
                }
                Product productToCompare = commandManager.createProductForRemoving((ProductDto) req.getBody());

                ArrayList<Product> listToRemove = new ArrayList<>();

                linkedHashSetManager.getSet().stream()
                        .filter((Product person) -> person.getPrice() < productToCompare.getPrice())
                        .forEach(listToRemove::add);

                listToRemove.forEach(linkedHashSetManager::deleteObject);
                commandManager.addToHistory(getName());
                return new Response<>(true, "Removed from collection all elements less than the specified!");
            } else {
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
            }
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}
