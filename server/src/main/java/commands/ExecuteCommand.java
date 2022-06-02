package commands;


import interaction.Request;
import interaction.Response;

public class ExecuteCommand extends AbstractCommand {

    public ExecuteCommand() {
        super("execute_script", "file_name", "read and execute a script from a specified file. " +
                "The script contains commands in the same form as they are entered by the user in interactive mode.");
    }

    @Override
    public Response execute(Request req) {
        return null;
    }
}
