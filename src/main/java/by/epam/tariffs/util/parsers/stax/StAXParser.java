package by.epam.tariffs.util.parsers.stax;

import by.epam.tariffs.entities.*;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.tariffs.util.parsers.XmlElementNameConstants.*;

public class StAXParser {

    public Tariffs parseTariffsFromFile(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        List<AbstractTariff> listOfTariffs = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(xmlFilePath)) {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

            RoamingTariff roamingTariff = null;
            InternetForMobileTariff internetForMobileTariff = null;

            String currentElementName;

            while (xmlStreamReader.hasNext()) {
                int elementType = xmlStreamReader.next();

                if (elementType == XMLStreamConstants.START_ELEMENT) {
                    currentElementName = xmlStreamReader.getLocalName();

                    switch (currentElementName) {
                        case ROAMING_TARIFF_ELEMENT_NAME: {
                            roamingTariff = readRoamingTariffFromXml(xmlStreamReader);
                            listOfTariffs.add(roamingTariff);
                            break;
                        }
                        case INTERNET_FOR_MOBILE_ELEMENT_NAME: {
                            internetForMobileTariff = readInternetForMobileTariffFromXml(xmlStreamReader);
                            listOfTariffs.add(internetForMobileTariff);
                            break;
                        }
                    }
                }
            }

            Tariffs tariffs = new Tariffs();
            tariffs.setListOfTariffs(listOfTariffs);

            return tariffs;

        } catch (XMLStreamException exception) {
            throw new XMLParserException("StAX parsing failed.", exception);
        } catch (IOException exception) {
            throw new IncorrectFileException(exception);
        }
    }

    private InternetForMobileTariff readInternetForMobileTariffFromXml(XMLStreamReader reader) throws XMLStreamException {
        AbstractTariff abstractTariff = readAbstractTariffFromXml(reader);
        InternetForMobileTariff internetForMobileTariff = new InternetForMobileTariff(abstractTariff);

        String currentElementName;

        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case MEGABYTES_COUNT_ELEMENT_NAME: {
                        String megaBytesCountValue = getValueFromXml(reader);
                        Integer megaBytesCount = Integer.parseInt(megaBytesCountValue);
                        internetForMobileTariff.setMegaBytesCount(megaBytesCount);
                        break;
                    }
                    case MEGABYTE_PRICE_ELEMENT_NAME: {
                        String megaBytePriceValue = getValueFromXml(reader);
                        Double megaBytePrice = Double.parseDouble(megaBytePriceValue);
                        internetForMobileTariff.setMegaBytePrice(megaBytePrice);
                        break;
                    }
                }
            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                currentElementName = reader.getLocalName();

                if (INTERNET_FOR_MOBILE_ELEMENT_NAME.equals(currentElementName)) {
                    return internetForMobileTariff;
                }
            }
        }

        throw new XMLStreamException("Unknown element in tag InternetForMobileTariff.");
    }

    private RoamingTariff readRoamingTariffFromXml(XMLStreamReader reader) throws XMLStreamException {
        AbstractTariff abstractTariff = readAbstractTariffFromXml(reader);
        RoamingTariff roamingTariff = new RoamingTariff(abstractTariff);

        String currentElementName;
        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case INTERNET_AVAILABLE_ELEMENT_NAME: {
                        String isInternetAvailableValue = getValueFromXml(reader);
                        Boolean isInternetAvailable = Boolean.parseBoolean(isInternetAvailableValue);
                        roamingTariff.setIsInternetAvailable(isInternetAvailable);
                        break;
                    }
                    case INTERNATIONAL_CALL_ELEMENT_NAME: {
                        String internationalCallPerMinutePriceValue = getValueFromXml(reader);
                        Double internationalCallPerMinutePrice = Double.parseDouble(internationalCallPerMinutePriceValue);
                        roamingTariff.setInternationalCallPerMinutePrice(internationalCallPerMinutePrice);
                        break;
                    }
                }
            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                currentElementName = reader.getLocalName();

                if (ROAMING_TARIFF_ELEMENT_NAME.equals(currentElementName)) {
                    return roamingTariff;
                }
            }
        }

        throw new XMLStreamException("Unknown element in tag RoamingTariff.");
    }

    private AbstractTariff readAbstractTariffFromXml(XMLStreamReader reader) throws XMLStreamException {
        AbstractTariff abstractTariff = new AbstractTariff() {
        };

        String tariffName = reader.getAttributeValue(null, TARIFF_NAME_ELEMENT_NAME);
        abstractTariff.setTariffName(tariffName);

        String currentElementName;
        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case SMS_PRICE_ELEMENT_NAME: {
                        String smsPriceValue = getValueFromXml(reader);
                        Double smsPrice = Double.parseDouble(smsPriceValue);
                        abstractTariff.setSmsPrice(smsPrice);
                        break;
                    }
                    case PAYROLL_ELEMENT_NAME: {
                        String payrollValue = getValueFromXml(reader);
                        Double payroll = Double.parseDouble(payrollValue);
                        abstractTariff.setPayroll(payroll);
                        break;
                    }
                    case OPERATOR_ELEMENT_NAME: {
                        String operatorValue = getValueFromXml(reader);
                        Operator operator = Operator.valueOf(operatorValue);
                        abstractTariff.setOperator(operator);
                        break;
                    }
                    case CALL_PRICES_ELEMENT_NAME: {
                        CallPrices callPrices = readCallPricesFromXml(reader);
                        abstractTariff.setCallPrices(callPrices);
                        break;
                    }
                    case PARAMETERS_ELEMENT_NAME: {
                        Parameters parameters = readParametersFromXml(reader);
                        abstractTariff.setParameters(parameters);
                        return abstractTariff;
                    }
                }

            }
        }

        throw new XMLStreamException("Unknown element in tag Tariff.");
    }

    private CallPrices readCallPricesFromXml(XMLStreamReader reader) {
        CallPrices callPrices = new CallPrices();

        String inComingCallPerMinutePriceValue = reader.getAttributeValue(null, IN_COMING_CALL_ELEMENT_NAME);
        String outComingCallPerMinutePriceValue = reader.getAttributeValue(null, OUT_COMING_CALL_ELEMENT_NAME);
        String cityLineCallPerMinutePriceValue = reader.getAttributeValue(null, CITY_LINES_CALL_ELEMENT_NAME);

        Double inComingCallPerMinutePrice = Double.parseDouble(inComingCallPerMinutePriceValue);
        Double outComingCallPerMinutePrice = Double.parseDouble(outComingCallPerMinutePriceValue);
        Double cityLineCallPerMinutePrice = Double.parseDouble(cityLineCallPerMinutePriceValue);

        callPrices.setInComingCallPerMinutePrice(inComingCallPerMinutePrice);
        callPrices.setOutComingCallPerMinutePrice(outComingCallPerMinutePrice);
        callPrices.setCityLineCallPerMinutePrice(cityLineCallPerMinutePrice);

        return callPrices;
    }

    private Parameters readParametersFromXml(XMLStreamReader reader) throws XMLStreamException {
        Parameters parameters = new Parameters();

        String isFavoriteNumberAvailableValue = reader.getAttributeValue(null, FAVORITE_NUMBER_AVAILABLE_ELEMENT_NAME);
        Boolean isFavoriteNumberAvailable = Boolean.parseBoolean(isFavoriteNumberAvailableValue);

        parameters.setIsFavoriteNumberAvailable(isFavoriteNumberAvailable);

        String currentElementName;
        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case TARIFFICATION_ELEMENT_NAME: {
                        String tarifficationValue = getValueFromXml(reader);
                        Tariffication tariffication = Tariffication.valueOf(tarifficationValue);
                        parameters.setTariffication(tariffication);
                        break;
                    }
                    case CONNECTION_PRICE_ELEMENT_NAME: {
                        String connectionPriceValue = getValueFromXml(reader);
                        Double connectionPrice = Double.parseDouble(connectionPriceValue);
                        parameters.setConnectionPrice(connectionPrice);
                        break;
                    }
                }

            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                currentElementName = reader.getLocalName();
                if (PARAMETERS_ELEMENT_NAME.equals(currentElementName)) {
                    return parameters;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag Parameters.");
    }

    private String getValueFromXml(XMLStreamReader reader) throws XMLStreamException {
        String value = null;
        if (reader.hasNext()) {
            reader.next();
            value = reader.getText();
        }

        return value;
    }

}
