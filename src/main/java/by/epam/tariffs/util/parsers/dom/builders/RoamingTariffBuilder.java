package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import org.w3c.dom.Element;

import static by.epam.tariffs.util.ValueInjector.*;

public class RoamingTariffBuilder {

    public RoamingTariff buildRoamingTariff(Element element) {
        AbstractTariffBuilder abstractTariffBuilder = new AbstractTariffBuilder();
        AbstractTariff abstractTariff = abstractTariffBuilder.buildAbstractTariff(element);

        RoamingTariff roamingTariff = new RoamingTariff(abstractTariff);

        Boolean isInternetAvailable = getBooleanValueFromElement(element, INTERNET_AVAILABLE_ELEMENT_NAME);
        Double internationalCallPerMinutePrice = getDoubleValueFromElement(element, INTERNATIONAL_CALL_ELEMENT_NAME);

        roamingTariff.setIsInternetAvailable(isInternetAvailable);
        roamingTariff.setInternationalCallPerMinutePrice(internationalCallPerMinutePrice);

        return roamingTariff;
    }

}
