package by.epam.tariffs.util.parsers;

import by.epam.tariffs.DataForTests;
import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import by.epam.tariffs.parsers.StAXParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static by.epam.tariffs.DataForTests.*;

public class StAXParserTest {

    private static Tariffs validTariffs;
    private static StAXParser stAXParser;

    @BeforeClass
    public static void setTestingObjects() {
        validTariffs = DataForTests.getTariffs();
        stAXParser = new StAXParser();
    }

    @Test
    public void shouldParsingBeSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs parsedTariffs = stAXParser.parseTariffsFromFile(VALID_DATA_FILE_PATH);

        Assert.assertEquals(validTariffs, parsedTariffs);
    }

    @Test
    public void shouldParsingBeNotSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs incorrectTariffs = stAXParser.parseTariffsFromFile(INCORRECT_TARIFF_TYPE);

        Assert.assertNotEquals(validTariffs, incorrectTariffs);
    }

    @Test(expected = IncorrectFileException.class)
    public void shouldParsingCauseIncorrectFileException() throws IncorrectFileException, XMLParserException {
        stAXParser.parseTariffsFromFile(INCORRECT_FILE_PATH);
    }

}
