package interaction;


import java.io.Serializable;

public class Request<T> implements Serializable {
    String command;
    String params;
    T body;

    public Request(String command) {
        this.command = command;
    }

    public Request(String command, String params) {
        this.command = command;
        this.params = params;
    }

    public Request(String command, String params, T body) {
        this.command = command;
        this.params = params;
        this.body = body;
    }

    public Request(String command, T body) {
        this.command = command;
        this.body = body;
    }

    public String getCommand() {
        return command;
    }

    public String getParams() {
        return params;
    }

    public T getBody() {
        return body;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", params='" + params + '\'' +
                ", body=" + body +
                '}';
    }
}
