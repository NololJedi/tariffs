package by.epam.tariffs;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import by.epam.tariffs.util.parsers.dom.DOMParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static by.epam.tariffs.DataForTests.INCORRECT_FILE_PATH;
import static by.epam.tariffs.DataForTests.INCORRECT_TARIFF_TYPE;
import static by.epam.tariffs.DataForTests.VALID_DATA_FILE_PATH;

public class DOMParserTest {

    private static Tariffs validTariffs;
    private static DOMParser domParser;

    @BeforeClass
    public static void setTestingObjects() {
        validTariffs = DataForTests.getTariffs();
        domParser = new DOMParser();
    }

    @Test
    public void shouldParsingBeSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs parsedTariffs = domParser.parseTariffsFromFile(VALID_DATA_FILE_PATH);

        Assert.assertEquals(validTariffs, parsedTariffs);
    }

    @Test
    public void shouldParsingFailed() throws IncorrectFileException, XMLParserException {
        Tariffs incorrectTariffs = domParser.parseTariffsFromFile(INCORRECT_TARIFF_TYPE);

        Assert.assertNotEquals(validTariffs,incorrectTariffs);
    }

    @Test(expected = IncorrectFileException.class)
    public void shouldParsingCauseIncorrectFileException() throws IncorrectFileException, XMLParserException {
        domParser.parseTariffsFromFile(INCORRECT_FILE_PATH);
    }
}
