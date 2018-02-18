package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.Parameters;
import by.epam.tariffs.entities.Tariffication;
import org.w3c.dom.Element;

import static by.epam.tariffs.util.ValueInjector.*;

public class ParametersBuilder {

    public Parameters buildParameters(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        Parameters parameters = new Parameters();

        Boolean isFavoriteNumberAvailable = getBooleanValueFromElement(element, FAVORITE_NUMBER_AVAILABLE_ELEMENT_NAME);
        Tariffication tariffication = (Tariffication) getEnumValueFromElement(element, TARIFFICATION_ELEMENT_NAME, Tariffication.class);
        Double connectionPrice = getDoubleValueFromElement(element, CONNECTION_PRICE_ELEMENT_NAME);

        parameters.setConnectionPrice(connectionPrice);
        parameters.setIsFavoriteNumberAvailable(isFavoriteNumberAvailable);
        parameters.setTariffication(tariffication);

        return parameters;
    }

}
