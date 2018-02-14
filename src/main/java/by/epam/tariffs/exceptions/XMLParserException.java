package by.epam.tariffs.exceptions;

public class XMLParserException extends Exception {

    public XMLParserException() {
    }

    public XMLParserException(String message) {
        super(message);
    }

    public XMLParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLParserException(Throwable cause) {
        super(cause);
    }
}
