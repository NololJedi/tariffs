package by.epam.tariffs.parsers;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JAXBParser implements Parser {

    private static final Logger LOGGER = Logger.getLogger(JAXBParser.class);

    public Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        LOGGER.info("Start JAXB parsing.");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tariffs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            FileReader fileReader = new FileReader(xmlFilePath);
            Tariffs tariffs = (Tariffs) unmarshaller.unmarshal(fileReader);

            LOGGER.info("JAXB parsing was made successfully.");

            return tariffs;

        } catch (JAXBException exception) {
            throw new XMLParserException("JAXB parsing failed.", exception);
        } catch (FileNotFoundException exception) {
            throw new IncorrectFileException("Something wrong with file.",exception);
        }

    }

}
