package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.util.parsers.dom.ValueInjector;
import org.w3c.dom.Element;

public class RoamingTariffBuilder {

    private static final String INTERNET_AVAILABLE_ELEMENT_NAME = "isInternetAvailable";
    private static final String INTERNATIONAL_CALL_ELEMENT_NAME = "internationalCallPerMinutePrice";

    public RoamingTariff buildRoamingTariff(Element element) {
        AbstractTariffBuilder abstractTariffBuilder = new AbstractTariffBuilder();
        AbstractTariff abstractTariff = abstractTariffBuilder.buildAbstractTariff(element);

        RoamingTariff roamingTariff = new RoamingTariff(abstractTariff);

        Boolean isInternetAvailable = ValueInjector.getBooleanValueFromElement(element, INTERNET_AVAILABLE_ELEMENT_NAME);
        Double internationalCallPerMinutePrice =
                ValueInjector.getDoubleValueFromElement(element, INTERNATIONAL_CALL_ELEMENT_NAME);

        roamingTariff.setIsInternetAvailable(isInternetAvailable);
        roamingTariff.setInternationalCallPerMinutePrice(internationalCallPerMinutePrice);

        return roamingTariff;
    }

}
