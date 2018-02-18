package by.epam.tariffs.util.parsers.sax;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;
import by.epam.tariffs.entities.Tariffication;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static by.epam.tariffs.util.parsers.XmlElementNameConstants.*;

public class TariffHandler extends DefaultHandler {

    private static final Logger LOGGER = Logger.getLogger(TariffHandler.class);

    private static final int FIRST_ATTRIBUTE_INDEX = 0;
    private static final int SECOND_ATTRIBUTE_INDEX = 1;
    private static final int THIRD_ATTRIBUTE_INDEX = 2;

    private List<AbstractTariff> listOfTariffs;
    private AbstractTariff currentTariff;
    private String currentElementName;

    public TariffHandler() {
        this.listOfTariffs = new ArrayList<>();
    }

    @Override
    public void startDocument() {
        LOGGER.info("Start SAX parsing.");
    }

    @Override
    public void endDocument() {
        LOGGER.info("SAX parsing was made successfully.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        switch (localName) {
            case ROAMING_TARIFF_ELEMENT_NAME: {
                AbstractTariff abstractTariff = buildAbstractTariff();
                currentTariff = new RoamingTariff(abstractTariff);

                String tariffName = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                currentTariff.setTariffName(tariffName);
                break;
            }
            case INTERNET_FOR_MOBILE_ELEMENT_NAME: {
                AbstractTariff abstractTariff = buildAbstractTariff();
                currentTariff = new InternetForMobileTariff(abstractTariff);

                String tariffName = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                currentTariff.setTariffName(tariffName);

                break;
            }
            case CALL_PRICES_ELEMENT_NAME: {
                String inComingCallPerMinutePriceValue = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                Double inComingCallPerMinutePrice = Double.parseDouble(inComingCallPerMinutePriceValue);
                String outComingCallPerMinutePriceValue = attributes.getValue(SECOND_ATTRIBUTE_INDEX);
                Double outComingCallPerMinutePrice = Double.parseDouble(outComingCallPerMinutePriceValue);
                String cityLineCallPerMinutePriceValue = attributes.getValue(THIRD_ATTRIBUTE_INDEX);
                Double cityLineCallPerMinutePrice = Double.parseDouble(cityLineCallPerMinutePriceValue);

                CallPrices callPrices = currentTariff.getCallPrices();
                callPrices.setInComingCallPerMinutePrice(inComingCallPerMinutePrice);
                callPrices.setOutComingCallPerMinutePrice(outComingCallPerMinutePrice);
                callPrices.setCityLineCallPerMinutePrice(cityLineCallPerMinutePrice);

                break;
            }
            case PARAMETERS_ELEMENT_NAME: {
                String isFavoriteNumberAvailableValue = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                Boolean isFavoriteNumberAvailable = Boolean.parseBoolean(isFavoriteNumberAvailableValue);

                Parameters parameters = currentTariff.getParameters();
                parameters.setIsFavoriteNumberAvailable(isFavoriteNumberAvailable);

                break;
            }
            default: {
                currentElementName = localName;

                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ROAMING_TARIFF_ELEMENT_NAME.equals(localName) || INTERNET_FOR_MOBILE_ELEMENT_NAME.equals(localName)) {
            listOfTariffs.add(currentTariff);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentElementName != null && !value.isEmpty()) {

            switch (currentElementName) {
                case TARIFFICATION_ELEMENT_NAME: {
                    Tariffication tariffication = Tariffication.valueOf(value);
                    Parameters parameters = currentTariff.getParameters();
                    parameters.setTariffication(tariffication);

                    break;
                }
                case CONNECTION_PRICE_ELEMENT_NAME: {
                    Double connectionPrice = Double.parseDouble(value);
                    Parameters parameters = currentTariff.getParameters();
                    parameters.setConnectionPrice(connectionPrice);

                    break;
                }
                case PAYROLL_ELEMENT_NAME: {
                    Double payroll = Double.parseDouble(value);
                    currentTariff.setPayroll(payroll);
                    break;
                }
                case SMS_PRICE_ELEMENT_NAME: {
                    Double smsPrice = Double.parseDouble(value);
                    currentTariff.setSmsPrice(smsPrice);

                    break;
                }
                case OPERATOR_ELEMENT_NAME: {
                    Operator operator = Operator.valueOf(value);
                    currentTariff.setOperator(operator);

                    break;
                }
                case MEGABYTES_COUNT_ELEMENT_NAME: {
                    Integer megaBytesCount = Integer.parseInt(value);
                    InternetForMobileTariff internetForMobileTariff = (InternetForMobileTariff) currentTariff;
                    internetForMobileTariff.setMegaBytesCount(megaBytesCount);

                    break;
                }
                case MEGABYTE_PRICE_ELEMENT_NAME: {
                    Double megaBytePrice = Double.parseDouble(value);
                    InternetForMobileTariff internetForMobileTariff = (InternetForMobileTariff) currentTariff;
                    internetForMobileTariff.setMegaBytePrice(megaBytePrice);

                    break;
                }
                case INTERNET_AVAILABLE_ELEMENT_NAME: {
                    Boolean isInternetAvailable = Boolean.parseBoolean(value);
                    RoamingTariff roamingTariff = (RoamingTariff) currentTariff;
                    roamingTariff.setIsInternetAvailable(isInternetAvailable);

                    break;
                }
                case INTERNATIONAL_CALL_ELEMENT_NAME: {
                    Double internationalCallPerMinutePrice = Double.parseDouble(value);
                    RoamingTariff roamingTariff = (RoamingTariff) currentTariff;
                    roamingTariff.setInternationalCallPerMinutePrice(internationalCallPerMinutePrice);

                    break;
                }
            }

        }
    }

    private AbstractTariff buildAbstractTariff() {
        AbstractTariff abstractTariff = new AbstractTariff() {
        };
        CallPrices callPrices = new CallPrices();
        Parameters parameters = new Parameters();

        abstractTariff.setCallPrices(callPrices);
        abstractTariff.setParameters(parameters);

        return abstractTariff;
    }

    public List<AbstractTariff> getListOfTariffs() {
        return listOfTariffs;
    }
}
