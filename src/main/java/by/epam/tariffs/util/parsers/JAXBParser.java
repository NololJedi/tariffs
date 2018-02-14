package by.epam.tariffs.util.parsers;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JAXBParser {

    public Tariffs parseTariffsFromFile(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tariffs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            FileReader fileReader = new FileReader(xmlFilePath);
            Tariffs tariffs = (Tariffs) unmarshaller.unmarshal(fileReader);

            return tariffs;
        } catch (JAXBException exception) {
            throw new XMLParserException("Parsing failed.", exception);
        } catch (FileNotFoundException exception) {
            throw new IncorrectFileException(exception);
        }

    }

}
