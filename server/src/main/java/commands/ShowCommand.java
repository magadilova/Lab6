package commands;


import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;
import model.Product;

import java.util.LinkedHashSet;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class ShowCommand extends AbstractCommand {
    CommandManager commandManager;
    LinkedHashSetManager linkedHashSetManager;

    public ShowCommand(CommandManager commandManager, LinkedHashSetManager linkedHashSetManager) {
        super("show", "", "output to the standard output stream all the elements" +
                " of the collection in a string representation");
        this.commandManager = commandManager;
        this.linkedHashSetManager = linkedHashSetManager;
    }

    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() == null ) {
                StringBuilder stringBuilder = new StringBuilder();
                LinkedHashSet<Product> product = linkedHashSetManager.getSet();
                if (product.isEmpty()) {
                    return new Response(false, "Oops, collection is empty!");
                } else {
                    for (Product p : product) {
                        stringBuilder.append(p.toString() + "\n");
                    }
                    commandManager.addToHistory(getName());
                }
                return new Response<>(true, "Collection successfully released!", stringBuilder.toString());
            } else
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}
