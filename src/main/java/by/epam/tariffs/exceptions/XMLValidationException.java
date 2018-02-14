package by.epam.tariffs.exceptions;

public class XMLValidationException extends Exception {

    public XMLValidationException() {
    }

    public XMLValidationException(String message) {
        super(message);
    }

    public XMLValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLValidationException(Throwable cause) {
        super(cause);
    }
}
