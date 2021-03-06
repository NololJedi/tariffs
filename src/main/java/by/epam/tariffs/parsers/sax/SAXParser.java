package by.epam.tariffs.parsers.sax;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import by.epam.tariffs.parsers.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXParser implements Parser {

    public Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        try {
            TariffHandler tariffHandler = new TariffHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(tariffHandler);
            reader.parse(xmlFilePath);

            Tariffs tariffs = new Tariffs();
            List<AbstractTariff> listOfTariffs = tariffHandler.getListOfTariffs();
            tariffs.setListOfTariffs(listOfTariffs);

            return tariffs;

        } catch (SAXException exception) {
            throw new XMLParserException("SAX parsing failed.", exception);
        } catch (IOException exception) {
            throw new IncorrectFileException("Something wrong with file.",exception);
        }
    }

}
