package by.epam.tariffs;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import by.epam.tariffs.util.parsers.JAXBParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static by.epam.tariffs.DataForTests.INCORRECT_FILE_PATH;
import static by.epam.tariffs.DataForTests.INCORRECT_TARIFF_TYPE;
import static by.epam.tariffs.DataForTests.VALID_DATA_FILE_PATH;

public class JAXBParserTest {

    private static Tariffs validTariffs;
    private static JAXBParser jaxbParser;

    @BeforeClass
    public static void setTestingObjects() {

        validTariffs = DataForTests.getTariffs();
        jaxbParser = new JAXBParser();

    }

    @Test
    public void shouldJAXBParsingBeSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs parsedTariffs = jaxbParser.parseTariffsFromFile(VALID_DATA_FILE_PATH);

        Assert.assertEquals(validTariffs, parsedTariffs);
    }

    @Test
    public void shouldJAXBParsingBeNotSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs incorrectTariffs = jaxbParser.parseTariffsFromFile(INCORRECT_TARIFF_TYPE);

        Assert.assertNotEquals(validTariffs, incorrectTariffs);
    }

    @Test(expected = IncorrectFileException.class)
    public void shouldParsingCauseIncorrectFileException() throws IncorrectFileException, XMLParserException {
        jaxbParser.parseTariffsFromFile(INCORRECT_FILE_PATH);
    }

}
