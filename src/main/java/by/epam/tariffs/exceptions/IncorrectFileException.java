package by.epam.tariffs.exceptions;

public class IncorrectFileException extends Exception {

    public IncorrectFileException() {
    }

    public IncorrectFileException(String message) {
        super(message);
    }

    public IncorrectFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFileException(Throwable cause) {
        super(cause);
    }
}
