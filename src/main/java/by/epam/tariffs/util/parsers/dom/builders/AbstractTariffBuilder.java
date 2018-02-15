package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;
import by.epam.tariffs.entities.tariff.AbstractTariff;

import by.epam.tariffs.util.parsers.dom.ElementValueInjector;
import org.w3c.dom.Element;

import static by.epam.tariffs.util.parsers.dom.ElementValueInjector.CURRENT_ELEMENT_INDEX;

public class AbstractTariffBuilder {

    public AbstractTariff buildAbstractTariff(Element element){
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        String tariffName = element.getAttribute("tariffName");

        String payrollValueName = "payroll";
        String payrollValue = ElementValueInjector.injectValueFromElement(element,payrollValueName);
        Double payroll = Double.parseDouble(payrollValue);

        String smsPriceName = "smsPrice";
        String smsPriceValue = ElementValueInjector.injectValueFromElement(element,smsPriceName);
        Double smsPrice = Double.parseDouble(payrollValue);

        String operatorName = "operator";
        String operatorValue = ElementValueInjector.injectValueFromElement(element,operatorName);
        Operator operator = Operator.valueOf(operatorValue);

        String callPricesName = "callPrices";
        Element callPricesElement = (Element) element.getElementsByTagName(callPricesName).item(CURRENT_ELEMENT_INDEX);
        CallPricesBuilder callPricesBuilder = new CallPricesBuilder();
        CallPrices callPrices = callPricesBuilder.buildCallPrices(callPricesElement);

        String parametersName = "parameters";
        Element parametersElement = (Element)element.getElementsByTagName(parametersName).item(CURRENT_ELEMENT_INDEX);
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
