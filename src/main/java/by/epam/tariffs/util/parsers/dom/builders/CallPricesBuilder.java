package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.util.parsers.dom.ValueInjector;
import org.w3c.dom.Element;

public class CallPricesBuilder {

    private static final String IN_COMING_CALL_ELEMENT_NAME = "inComingCallPerMinutePrice";
    private static final String OUT_COMING_CALL_ELEMENT_NAME = "outComingCallPerMinutePrice";
    private static final String CITY_LINES_CALL_ELEMENT_NAME = "cityLineCallPerMinutePrice";

    public CallPrices buildCallPrices(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        CallPrices callPrices = new CallPrices();

        Double inComingCallPerMinutePrice = ValueInjector.getDoubleValueFromElement(element, IN_COMING_CALL_ELEMENT_NAME);
        Double outComingCallPerMinutePrice = ValueInjector.getDoubleValueFromElement(element, OUT_COMING_CALL_ELEMENT_NAME);
        Double cityLineCallPerMinutePrice = ValueInjector.getDoubleValueFromElement(element, CITY_LINES_CALL_ELEMENT_NAME);

        callPrices.setInComingCallPerMinutePrice(inComingCallPerMinutePrice);
        callPrices.setOutComingCallPerMinutePrice(outComingCallPerMinutePrice);
        callPrices.setCityLineCallPerMinutePrice(cityLineCallPerMinutePrice);

        return callPrices;
    }

}
