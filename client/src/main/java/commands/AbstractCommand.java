package commands;

import interaction.Response;
import modules.ResponseReaderModule;

import java.io.IOException;

/**
 * Абстрактный класс комманд
 */

public abstract class AbstractCommand {
    private String name;
    private String description;
    private String params;

    public AbstractCommand(String name, String params, String description) {
        this.name = name;
        this.params = params;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getParameters() {
        return params;
    }

    public boolean result(ResponseReaderModule reader) {
        try {
            Response res = reader.readResponse();
            if (!res.isCompleted()) {
                System.err.println(res.getMessage());
                return false;
            }
            System.out.println((String) res.getBody());
            System.out.println(res.getMessage());
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ooops! Something went wrong :3");
            return false;
        }
    }

    abstract public boolean execute(String arguments);
}
