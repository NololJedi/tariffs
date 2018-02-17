package by.epam.tariffs.util.parsers.sax;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Parameters;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class TariffHandler extends DefaultHandler {

    private static final String ROAMING_TARIFF_XML_TYPE = "RoamingTariff";
    private static final String INTERNET_FOR_MOBILE_XML_TYPE = "InternetForMobileTariff";
    private static final int VALUE_INDEX = 0;

    private List<AbstractTariff> listOfTariffs;
    private AbstractTariff currentTariff;

    public TariffHandler() {
        this.listOfTariffs = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (ROAMING_TARIFF_XML_TYPE.equals(localName)) {
            AbstractTariff abstractTariff = buildAbstractTariff();
            currentTariff = new RoamingTariff(abstractTariff);

            String tariffName = attributes.getValue(VALUE_INDEX);
            currentTariff.setTariffName(tariffName);
        } else if (INTERNET_FOR_MOBILE_XML_TYPE.equals(localName)){
            AbstractTariff abstractTariff = buildAbstractTariff();
            currentTariff = new InternetForMobileTariff(abstractTariff);

            String tariffName = attributes.getValue(VALUE_INDEX);
            currentTariff.setTariffName(tariffName);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (ROAMING_TARIFF_XML_TYPE.equals(localName) || INTERNET_FOR_MOBILE_XML_TYPE.equals(localName)) {
            listOfTariffs.add(currentTariff);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    private AbstractTariff buildAbstractTariff(){
        AbstractTariff abstractTariff = new AbstractTariff() {};
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
