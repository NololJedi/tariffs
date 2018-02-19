package by.epam.tariffs.entities.tariff;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractTariff {

    @XmlAttribute(name = "tariffName",required = true)
    private String tariffName;

    @XmlElement(name = "operator")
    private Operator operator;
    @XmlElement(name = "callPrices")
    private CallPrices callPrices;
    @XmlElement(name = "payroll")
    private Double payroll;
    @XmlElement(name = "smsPrice")
    private Double smsPrice;
    @XmlElement(name = "parameters")
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

    public AbstractTariff(AbstractTariff abstractTariff) {

        this.tariffName = abstractTariff.getTariffName();
        this.operator = abstractTariff.getOperator();
        this.parameters = abstractTariff.getParameters();
        this.callPrices = abstractTariff.getCallPrices();
        this.payroll = abstractTariff.getPayroll();
        this.smsPrice = abstractTariff.getSmsPrice();

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

        if (!tariffName.equals(that.tariffName)) {
            return false;
        }
        if (operator != that.operator) {
            return false;
        }
        if (!callPrices.equals(that.callPrices)) {
            return false;
        }
        if (!payroll.equals(that.payroll)) {
            return false;
        }
        if (!smsPrice.equals(that.smsPrice)) {
            return false;
        }
        if (!parameters.equals(that.parameters)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffName, operator, callPrices, payroll, smsPrice, parameters);
    }

    @Override
    public String toString() {
        return "AbstractTariff{" +
                "tariffName='" + tariffName + '\'' +
                ", operator=" + operator +
                ", callPrices=" + callPrices +
                ", payroll=" + payroll +
                ", smsPrice=" + smsPrice +
                ", parameters=" + parameters +
                '}';
    }
}
