package commands;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.exception.EmptyFieldCommandException;

import java.util.ArrayList;

/**
 * Выводит последние 12 команд
 */

public class HistoryCommand extends AbstractCommand {
    CommandManager commandManager;

    public HistoryCommand(CommandManager commandManager) {
        super("history", "", "print the last 12 commands");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request req) {
        StringBuilder sb = new StringBuilder();
        try {
            if (req.getParams() == null) {
                ArrayList<String> history = commandManager.getHistory();
                int number = 1;
                for (String command : history) {
                    sb.append(number).append(" ").append(command).append("\n");
                    number++;
                }
                return new Response<>(true, "A list of commands is displayed!", sb.toString());
            } else
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response<>(false, e.getMessage());
        }
    }
}

