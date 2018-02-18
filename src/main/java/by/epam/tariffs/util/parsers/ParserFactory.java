package by.epam.tariffs.util.parsers;

import by.epam.tariffs.util.parsers.dom.DOMParser;
import by.epam.tariffs.util.parsers.sax.SAXParser;

public class ParserFactory {

    public TariffParser getTariffParser(ParserType parserType) {

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
