package by.epam.tariffs.parsers;

import by.epam.tariffs.entities.*;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.tariffs.util.ValueInjector.*;


public class StAXParser implements Parser {

    private static final Logger LOGGER = Logger.getLogger(StAXParser.class);

    public Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        List<AbstractTariff> listOfTariffs = new ArrayList<>();

        LOGGER.info("Start StAX parsing.");

        try (FileInputStream fileInputStream = new FileInputStream(xmlFilePath)) {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

            while (xmlStreamReader.hasNext()) {
                int elementType = xmlStreamReader.next();

                if (elementType == XMLStreamConstants.START_ELEMENT) {
                    String currentElementName = xmlStreamReader.getLocalName();

                    switch (currentElementName) {
                        case ROAMING_TARIFF_ELEMENT_NAME: {
                            RoamingTariff roamingTariff = readRoamingTariffFromXml(xmlStreamReader);
                            listOfTariffs.add(roamingTariff);

                            break;
                        }
                        case INTERNET_FOR_MOBILE_ELEMENT_NAME: {
                            InternetForMobileTariff internetForMobileTariff = readInternetForMobileTariffFromXml(xmlStreamReader);
                            listOfTariffs.add(internetForMobileTariff);

                            break;
                        }
                    }
                }
            }

            Tariffs tariffs = new Tariffs();
            tariffs.setListOfTariffs(listOfTariffs);

            LOGGER.info("StAX parsing was made successfully.");

            return tariffs;

        } catch (XMLStreamException exception) {
            throw new XMLParserException("StAX parsing failed.", exception);
        } catch (IOException exception) {
            throw new IncorrectFileException("Something wrong with file.",exception);
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
                        Integer megaBytesCount = injectIntegerValueFromElement(reader);
                        internetForMobileTariff.setMegaBytesCount(megaBytesCount);

                        break;
                    }
                    case MEGABYTE_PRICE_ELEMENT_NAME: {
                        Double megaBytePrice = injectDoubleValueFromElement(reader);
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
                        Boolean isInternetAvailable = injectBooleanValueFromElement(reader);
                        roamingTariff.setIsInternetAvailable(isInternetAvailable);

                        break;
                    }
                    case INTERNATIONAL_CALL_ELEMENT_NAME: {
                        Double internationalCallPerMinutePrice = injectDoubleValueFromElement(reader);
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
                        Double smsPrice = injectDoubleValueFromElement(reader);
                        abstractTariff.setSmsPrice(smsPrice);

                        break;
                    }
                    case PAYROLL_ELEMENT_NAME: {
                        Double payroll = injectDoubleValueFromElement(reader);
                        abstractTariff.setPayroll(payroll);

                        break;
                    }
                    case OPERATOR_ELEMENT_NAME: {
                        Operator operator = (Operator) injectEnumValueFromElement(reader, Operator.class);
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

        Double inComingCallPerMinutePrice = injectDoubleValueFromElement(reader, IN_COMING_CALL_ELEMENT_NAME);
        Double outComingCallPerMinutePrice = injectDoubleValueFromElement(reader, OUT_COMING_CALL_ELEMENT_NAME);
        Double cityLineCallPerMinutePrice = injectDoubleValueFromElement(reader, CITY_LINES_CALL_ELEMENT_NAME);

        callPrices.setInComingCallPerMinutePrice(inComingCallPerMinutePrice);
        callPrices.setOutComingCallPerMinutePrice(outComingCallPerMinutePrice);
        callPrices.setCityLineCallPerMinutePrice(cityLineCallPerMinutePrice);

        return callPrices;
    }

    private Parameters readParametersFromXml(XMLStreamReader reader) throws XMLStreamException {
        Parameters parameters = new Parameters();

        Boolean isFavoriteNumberAvailable = injectBooleanValueFromElement(reader, FAVORITE_NUMBER_AVAILABLE_ELEMENT_NAME);
        parameters.setIsFavoriteNumberAvailable(isFavoriteNumberAvailable);

        String currentElementName;
        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case TARIFFICATION_ELEMENT_NAME: {
                        Tariffication tariffication = (Tariffication) injectEnumValueFromElement(reader, Tariffication.class);
                        parameters.setTariffication(tariffication);

                        break;
                    }
                    case CONNECTION_PRICE_ELEMENT_NAME: {
                        Double connectionPrice = injectDoubleValueFromElement(reader);
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

}
