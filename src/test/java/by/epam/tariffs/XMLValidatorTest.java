package by.epam.tariffs;

import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.util.XMLValidator;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static by.epam.tariffs.DataForTests.VALID_DATA_FILE_PATH;

@RunWith(DataProviderRunner.class)
public class XMLValidatorTest {

    private static XMLValidator xmlValidator;

    private final static String XSD_SCHEMA_FILE_PATH = "./src/test/resources/schema.xsd";

    @BeforeClass
    public static void setXmlValidator() {
        xmlValidator = new XMLValidator();
    }

    @DataProvider
    public static Object[][] incorrectInputParameters() {
        String nullXMLFilePath = null;
        String emptyXMLFileParh = "";
        String nullXSDFilePath = null;
        String emptyXSDFilePath = "";

        return new Object[][]{
                {nullXMLFilePath, XSD_SCHEMA_FILE_PATH},
                {emptyXMLFileParh, XSD_SCHEMA_FILE_PATH},
                {VALID_DATA_FILE_PATH, nullXSDFilePath},
                {VALID_DATA_FILE_PATH, emptyXSDFilePath}
        };

    }

    @DataProvider
    public static Object[][] notValidXMLFiles() {
        String incorrectTariffType = "./src/test/resources/incorrect_tariff_type.xml";
        String incorrectAttributeOperator = "./src/test/resources/incorrect_attribute_operator.xml";
        String incorrectAttributeName = "./src/test/resources/incorrect_attribute_name.xml";
        String missedAttribute = "./src/test/resources/missed_attribute.xml";

        return new Object[][]{
                {incorrectAttributeName},
                {missedAttribute},
                {incorrectAttributeOperator},
                {incorrectTariffType}
        };
    }

    @Test
    public void shouldValidationBeSuccessful() throws IncorrectFileException {
        boolean validationResult = xmlValidator.validateXMLFIle(VALID_DATA_FILE_PATH, XSD_SCHEMA_FILE_PATH);

        Assert.assertTrue(validationResult);
    }

    @Test
    @UseDataProvider("notValidXMLFiles")
    public void shouldValidationBeNotSuccessful(String xmlFilePath) throws IncorrectFileException {
        boolean validationResult = xmlValidator.validateXMLFIle(xmlFilePath, XSD_SCHEMA_FILE_PATH);

        Assert.assertFalse(validationResult);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("incorrectInputParameters")
    public void shouldValidationCauseIllegalArgumentException(String xmlFilePath, String xsdFilePath)
            throws IncorrectFileException {
        xmlValidator.validateXMLFIle(xmlFilePath, xsdFilePath);
    }

    @Test(expected = IncorrectFileException.class)
    public void shouldValidationCauseIncorrectFileException() throws IncorrectFileException {
        String incorrectFilePath = ".src/test/resources/incorrect_attribute_smsPrice.xml";

        xmlValidator.validateXMLFIle(incorrectFilePath, XSD_SCHEMA_FILE_PATH);
    }

}
