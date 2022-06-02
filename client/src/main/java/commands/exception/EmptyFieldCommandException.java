package commands.exception;

public class EmptyFieldCommandException extends RuntimeException {
    public EmptyFieldCommandException(String message) {
        super(message);
    }
}