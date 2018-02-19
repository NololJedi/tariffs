package by.epam.tariffs.parsers;

import by.epam.tariffs.parsers.sax.SAXParser;
import by.epam.tariffs.parsers.dom.DOMParser;

public class ParserFactory {

    public Parser getParser(ParserType parserType) {

        switch (parserType) {
            case DOM: {
                return new DOMParser();
            }
            case SAX: {
                return new SAXParser();
            }
            case JAXB: {
                return new JAXBParser();
            }
            case StAX: {
                return new StAXParser();
            }
            default: {
                throw new IllegalArgumentException("Incorrect parser type.");
            }
        }

    }

}
