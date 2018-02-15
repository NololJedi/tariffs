package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.util.parsers.dom.ElementValueInjector;
import org.w3c.dom.Element;

public class RoamingTariffBuilder {

    private static final String INTERNET_AVAILABLE_VALUE_NAME = "isInternetAvailable";
    private static final String INTERNATIONAL_CALL_VALUE_NAME = "internationalCallPerMinutePrice";

    public RoamingTariff buildRoamingTariff(Element element){
        AbstractTariffBuilder abstractTariffBuilder = new AbstractTariffBuilder();
        AbstractTariff abstractTariff = abstractTariffBuilder.buildAbstractTariff(element);

        RoamingTariff roamingTariff = new RoamingTariff(abstractTariff);

        String isInternetAvailableValue = ElementValueInjector.injectValueFromElement(element, INTERNET_AVAILABLE_VALUE_NAME);
        Boolean isInternetAvailable = Boolean.parseBoolean(isInternetAvailableValue);

        String internationalCallPerMinutePriceValue = ElementValueInjector.injectValueFromElement(element, INTERNATIONAL_CALL_VALUE_NAME);
        Double internationalCallPerMinutePrice = Double.parseDouble(internationalCallPerMinutePriceValue);

        roamingTariff.setIsInternetAvailable(isInternetAvailable);
        roamingTariff.setInternationalCallPerMinutePrice(internationalCallPerMinutePrice);

        return roamingTariff;
    }

}
