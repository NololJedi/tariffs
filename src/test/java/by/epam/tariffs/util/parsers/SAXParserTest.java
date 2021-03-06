package by.epam.tariffs.util.parsers;

import by.epam.tariffs.DataForTests;
import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import by.epam.tariffs.parsers.sax.SAXParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static by.epam.tariffs.DataForTests.*;

public class SAXParserTest {

    private static Tariffs validTariffs;
    private static SAXParser saxParser;

    @BeforeClass
    public static void setTestingObjects() {
        validTariffs = DataForTests.getTariffs();
        saxParser = new SAXParser();
    }

    @Test
    public void shouldParsingBeSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs actualTariffs = saxParser.parse(VALID_DATA_FILE_PATH);

        Assert.assertEquals(validTariffs, actualTariffs);
    }

    @Test
    public void shouldParsingBeNotSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs actualTariffs = saxParser.parse(INCORRECT_TARIFF_TYPE);

        Assert.assertNotEquals(validTariffs, actualTariffs);
    }

    @Test(expected = IncorrectFileException.class)
    public void shouldParsingCauseIncorrectFileException() throws IncorrectFileException, XMLParserException {
        saxParser.parse(INCORRECT_FILE_PATH);
    }

}
