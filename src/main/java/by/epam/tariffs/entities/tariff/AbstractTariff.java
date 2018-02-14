package by.epam.tariffs.entities.tariff;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlTransient
@XmlType(propOrder = {"tariffName", "operator", "callPrices", "payroll", "smsPrice", "parameters"})
public abstract class AbstractTariff {

    private String tariffName;
    private Operator operator;
    private CallPrices callPrices;
    private Double payroll;
    private Double smsPrice;
    private Parameters parameters;

    public AbstractTariff() {
    }

    public AbstractTariff(String tariffName, Operator operator, CallPrices callPrices, Double payroll, Double smsPrice, Parameters parameters) {
        this.tariffName = tariffName;
        this.operator = operator;
        this.callPrices = callPrices;
        this.payroll = payroll;
        this.smsPrice = smsPrice;
        this.parameters = parameters;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Double getPayroll() {
        return payroll;
    }

    public void setPayroll(Double payroll) {
        this.payroll = payroll;
    }

    public Double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(Double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AbstractTariff that = (AbstractTariff) object;
        return Objects.equals(tariffName, that.tariffName) &&
                operator == that.operator &&
                Objects.equals(callPrices, that.callPrices) &&
                Objects.equals(payroll, that.payroll) &&
                Objects.equals(smsPrice, that.smsPrice) &&
                Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tariffName, operator, callPrices, payroll, smsPrice, parameters);
    }

    @Override
    public String toString() {
        String result = String.format("name - %s, operator - %s, call prices - %s, payroll - %2f, sms price - %2f, parameters - %s",
                tariffName, operator, callPrices.toString(), payroll, smsPrice, parameters.toString());

        return result;
    }
}
