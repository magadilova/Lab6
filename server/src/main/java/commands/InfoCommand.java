package commands;


import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.LinkedHashSetManager;
import manager.exception.EmptyFieldCommandException;

/**
 * Выводит в стандартный поток вывода информацию о коллекции.
 */

public class InfoCommand extends AbstractCommand {
    LinkedHashSetManager linkedHashSetCollectionManager;
    CommandManager commandManager;

    public InfoCommand( LinkedHashSetManager linkedHashSetCollectionManager,CommandManager commandManager) {
        super("info", "", "output collection information" +
                " (type, initialisation date, number of items)" + " to the standard output stream");
        this.linkedHashSetCollectionManager = linkedHashSetCollectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() == null) {
                String info = " Collection type: " + linkedHashSetCollectionManager.getSet().getClass() +
                        "\n Date of initialisation: " + linkedHashSetCollectionManager.getDateInitialization() +
                        "\n Number of elements: " + linkedHashSetCollectionManager.getSet().size() +
                        "\n" + "Information of the collection is displayed";
                commandManager.addToHistory(getName());
                return new Response<>(true,  "",info);
            } else {
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
            }
        } catch (EmptyFieldCommandException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}