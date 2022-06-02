package commands;


import interaction.Request;
import interaction.Response;

/**
 *  Завершает работу программы.
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "", "exit program without saving collection into file");
    }

//    public boolean execute(String arguments) {
//        System.out.println("Exit of the application");
//        System.exit(0);
//        return true;
//    }

    @Override
    public Response execute(Request req) {
        return null;
    }
}