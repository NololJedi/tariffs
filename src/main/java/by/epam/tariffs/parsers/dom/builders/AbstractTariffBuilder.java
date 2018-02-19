package by.epam.tariffs.parsers.dom.builders;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import static by.epam.tariffs.util.ValueInjector.*;

public class AbstractTariffBuilder {

    public AbstractTariff buildAbstractTariff(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        String tariffName = element.getAttribute(TARIFF_NAME_ELEMENT_NAME);
        Double payroll = injectDoubleValueFromElement(element, PAYROLL_ELEMENT_NAME);
        Double smsPrice = injectDoubleValueFromElement(element, SMS_PRICE_ELEMENT_NAME);
        Operator operator = (Operator) injectEnumValueFromElement(element, OPERATOR_ELEMENT_NAME, Operator.class);

        NodeList callPricesElements = element.getElementsByTagName(CALL_PRICES_ELEMENT_NAME);
        Element callPricesElement = (Element) callPricesElements.item(CURRENT_ELEMENT_INDEX);
        CallPricesBuilder callPricesBuilder = new CallPricesBuilder();
        CallPrices callPrices = callPricesBuilder.buildCallPrices(callPricesElement);

        NodeList parametersElements = element.getElementsByTagName(PARAMETERS_ELEMENT_NAME);
        Element parametersElement = (Element) parametersElements.item(CURRENT_ELEMENT_INDEX);
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
