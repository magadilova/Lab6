package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.exception.WrongFieldCommandException;

/**
 * Выводит справку по доступным командам.
 */

public class HelpCommand extends AbstractCommand {
    CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "", "display help for available commands");
        this.commandManager = commandManager;
    }

    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() == null) {
                StringBuilder sb = new StringBuilder();
                commandManager.getCommands().forEach((key, value) -> sb.append(key).append(": ")
                        .append(value.getDescription()).append(" ").append("Params: ").append(value.getParameters()).append("\n"));
//                CommandManager.println("Help has been successfully issued");
                commandManager.addToHistory(getName());
                return new Response<>(true, "Help has been successfully issued", String.valueOf(sb));
            } else
                throw new WrongFieldCommandException("Exception: This command must not have any characters");
        } catch (WrongFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}
