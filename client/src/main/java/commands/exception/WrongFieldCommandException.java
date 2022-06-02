package commands.exception;

public class WrongFieldCommandException extends RuntimeException {
    public WrongFieldCommandException(String message) {
        super(message);
    }
}
