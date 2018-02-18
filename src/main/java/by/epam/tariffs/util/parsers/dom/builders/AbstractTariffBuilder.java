package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.util.parsers.dom.ValueInjector;
import org.w3c.dom.Element;

import static by.epam.tariffs.util.parsers.XmlElementNameConstants.*;
import static by.epam.tariffs.util.parsers.dom.ValueInjector.CURRENT_ELEMENT_INDEX;

public class AbstractTariffBuilder {

    public AbstractTariff buildAbstractTariff(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        String tariffName = element.getAttribute(TARIFF_NAME_ELEMENT_NAME);
        Double payroll = ValueInjector.getDoubleValueFromElement(element, PAYROLL_ELEMENT_NAME);
        Double smsPrice = ValueInjector.getDoubleValueFromElement(element, SMS_PRICE_ELEMENT_NAME);
        Operator operator = (Operator) ValueInjector.getEnumValueFromElement(element, OPERATOR_ELEMENT_NAME, Operator.class);

        Element callPricesElement = (Element) element.getElementsByTagName(CALL_PRICES_ELEMENT_NAME).item(CURRENT_ELEMENT_INDEX);
        CallPricesBuilder callPricesBuilder = new CallPricesBuilder();
        CallPrices callPrices = callPricesBuilder.buildCallPrices(callPricesElement);

        Element parametersElement = (Element) element.getElementsByTagName(PARAMETERS_ELEMENT_NAME).item(CURRENT_ELEMENT_INDEX);
        ParametersBuilder parametersBuilder = new ParametersBuilder();
        Parameters parameters = parametersBuilder.buildParameters(parametersElement);

        AbstractTariff abstractTariff = new AbstractTariff() {
        };

        abstractTariff.setTariffName(tariffName);
        abstractTariff.setPayroll(payroll);
        abstractTariff.setSmsPrice(smsPrice);
        abstractTariff.setOperator(operator);
        abstractTariff.setCallPrices(callPrices);
        abstractTariff.setParameters(parameters);

        return abstractTariff;
    }

}
