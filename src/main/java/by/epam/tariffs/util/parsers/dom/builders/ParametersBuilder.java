package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.Parameters;
import by.epam.tariffs.entities.Tariffication;
import by.epam.tariffs.util.parsers.dom.ValueInjector;
import org.w3c.dom.Element;

public class ParametersBuilder {

    private static final String FAVORITE_NUMBER_AVAILABLE_ELEMENT_NAME = "isFavoriteNumberAvailable";
    private static final String TARIFFICATION_ELEMENT_NAME = "tariffication";
    private static final String CONNECTION_PRICE_ELEMENT_NAME = "connectionPrice";

    public Parameters buildParameters(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        Parameters parameters = new Parameters();

        Boolean isFavoriteNumberAvailable =
                ValueInjector.getBooleanValueFromElement(element, FAVORITE_NUMBER_AVAILABLE_ELEMENT_NAME);
        Tariffication tariffication =
                (Tariffication) ValueInjector.getEnumValueFromElement(element, TARIFFICATION_ELEMENT_NAME, Tariffication.class);
        Double connectionPrice = ValueInjector.getDoubleValueFromElement(element, CONNECTION_PRICE_ELEMENT_NAME);

        parameters.setConnectionPrice(connectionPrice);
        parameters.setIsFavoriteNumberAvailable(isFavoriteNumberAvailable);
        parameters.setTariffication(tariffication);

        return parameters;
    }

}
