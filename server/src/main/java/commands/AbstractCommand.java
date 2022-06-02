package commands;

import interaction.Request;
import interaction.Response;

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

    abstract public Response execute(Request req);
}
