package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;
import model.Product;
import model.ProductDto;

/**
 * Добавляет в коллекцию новый элемент.
 */

public class AddCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetCollectionManager;
    CommandManager commandManager;

    public AddCommand(LinkedHashSetManager linkedHashSetCollectionManager, CommandManager commandManager) {
        super("add", "{element}", "add a new element to the collection");
        this.linkedHashSetCollectionManager = linkedHashSetCollectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            if (request.getParams() == null) {
                ProductDto dto = (ProductDto) request.getBody();
                Product product = new Product(
                        dto.getName(),
                        dto.getCoordinates(),
                        dto.getPrice(),
                        dto.getPartNumber(),
                        dto.getManufactureCost(),
                        dto.getUnitOfMeasure(),
                        dto.getOwner()
                );

                linkedHashSetCollectionManager.add(product);
                commandManager.addToHistory(getName());
                return new Response<>(true, "The element was successfully added!", product.toString());
            } else
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}

