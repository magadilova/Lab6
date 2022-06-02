package interaction;

import java.io.Serializable;

public class Response<T> implements Serializable {
    boolean isCompleted;
    String message;
    T body;

    public Response(boolean isCompleted, T body) {
        this.isCompleted = isCompleted;
        this.body = body;
    }

    public Response(boolean isCompleted, String message) {
        this.isCompleted = isCompleted;
        this.message = message;
    }

    public Response(boolean isCompleted, String message, T body) {
        this.isCompleted = isCompleted;
        this.message = message;
        this.body = body;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "isCompleted=" + isCompleted +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
