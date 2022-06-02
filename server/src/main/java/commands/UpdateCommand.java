package commands;


import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;
import manager.exception.WrongFieldCommandException;
import model.Product;
import model.ProductDto;

public class UpdateCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetManager;
    CommandManager commandManager;

    public UpdateCommand(LinkedHashSetManager linkedHashSetManager, CommandManager commandManager) {
        super("update_id", "{element}", "update the value of the collection item " +
                "whose id is the same as the given one");
        this.linkedHashSetManager = linkedHashSetManager;
        this.commandManager = commandManager;
    }


    @Override
    public Response<?> execute(Request request) {
        try {
            if (request.getParams() != null) {
                Product personToUpdate = linkedHashSetManager.getElementById(Long.parseLong(request.getParams()));
                linkedHashSetManager.update(personToUpdate,(ProductDto) request.getBody());
                commandManager.addToHistory(getName());
                return new Response<>(true,"The element with the given ID was successfully updated", personToUpdate.toString());
            } else throw new EmptyFieldCommandException("Exception: This command must not have any characters");
        } catch (EmptyFieldCommandException | WrongFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false,e.getMessage());
        }

    }
}
