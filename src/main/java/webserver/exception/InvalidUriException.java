package webserver.exception;

public class InvalidUriException extends RuntimeException{
    public InvalidUriException(String message) {
        super(message);
    }
}
