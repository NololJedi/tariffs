package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.CallPrices;
import org.w3c.dom.Element;

public class CallPricesBuilder {

    private static final String INCOMING_CALL_VALUE_NAME = "inComingCallPerMinutePrice";
    private static final String OUTCOMING_CALL_VALUE_NAME = "outComingCallPerMinutePrice";
    private static final String CITY_LINES_CALL_VALUE_NAME = "cityLineCallPerMinutePrice";

    public CallPrices buildCallPrices(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        CallPrices callPrices = new CallPrices();

        String inComingCallPerMinutePriceValue = element.getAttribute(INCOMING_CALL_VALUE_NAME);
        Double inComingCallPerMinutePrice = Double.parseDouble(inComingCallPerMinutePriceValue);

        String outComingCallPerMinutePriceValue = element.getAttribute(OUTCOMING_CALL_VALUE_NAME);
        Double outComingCallPerMinutePrice = Double.parseDouble(outComingCallPerMinutePriceValue);

        String cityLineCallPerMinutePriceValue = element.getAttribute(CITY_LINES_CALL_VALUE_NAME);
        Double cityLineCallPerMinutePrice = Double.parseDouble(cityLineCallPerMinutePriceValue);

        callPrices.setInComingCallPerMinutePrice(inComingCallPerMinutePrice);
        callPrices.setOutComingCallPerMinutePrice(outComingCallPerMinutePrice);
        callPrices.setCityLineCallPerMinutePrice(cityLineCallPerMinutePrice);

        return callPrices;
    }

}
