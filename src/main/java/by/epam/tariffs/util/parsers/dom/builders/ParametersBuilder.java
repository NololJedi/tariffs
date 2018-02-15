package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.Parameters;

import by.epam.tariffs.entities.Tariffication;
import by.epam.tariffs.util.parsers.dom.ElementValueInjector;
import org.w3c.dom.Element;

public class ParametersBuilder {

    private static final String FAVORITE_NUMBER_AVAILABLE_VALUE_NAME = "isFavoriteNumberAvailable";
    private static final String TARIFFICATION_VALUE_NAME = "tariffication";
    private static final String CONNECTION_PRICE_VALUE_NAME = "connectionPrice";

    public Parameters buildParameters(Element element){
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        Parameters parameters = new Parameters();

        String isFavoriteNumberAvailableValue = element.getAttribute(FAVORITE_NUMBER_AVAILABLE_VALUE_NAME);
        Boolean isFavoriteNumberAvailable = Boolean.parseBoolean(isFavoriteNumberAvailableValue);

        String tarifficationValue = ElementValueInjector.injectValueFromElement(element,TARIFFICATION_VALUE_NAME);
        Tariffication tariffication = Tariffication.valueOf(tarifficationValue);

        String connectionPriceValue = ElementValueInjector.injectValueFromElement(element, CONNECTION_PRICE_VALUE_NAME);
        Double connectionPrice = Double.parseDouble(connectionPriceValue);

        parameters.setConnectionPrice(connectionPrice);
        parameters.setIsFavoriteNumberAvailable(isFavoriteNumberAvailable);
        parameters.setTariffication(tariffication);

        return parameters;
    }

}
