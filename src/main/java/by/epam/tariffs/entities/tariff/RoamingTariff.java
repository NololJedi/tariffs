package by.epam.tariffs.entities.tariff;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement(name = "RoamingTariff")
public class RoamingTariff extends AbstractTariff {

    private Boolean isInternetAvailable;
    private Double internationalCallPerMinutePrice;

    public RoamingTariff() {
    }

    public RoamingTariff(String tariffName, Operator operator, CallPrices callPrices, Double payroll, Double smsPrice,
                         Parameters parameters, Boolean isInternetAvailable, Double internationalCallPerMinutePrice) {
        super(tariffName, operator, callPrices, payroll, smsPrice, parameters);
        this.isInternetAvailable = isInternetAvailable;
        this.internationalCallPerMinutePrice = internationalCallPerMinutePrice;
    }

    public Boolean getIsInternetAvailable() {
        return isInternetAvailable;
    }

    public void setIsInternetAvailable(Boolean isInternetAvailable) {
        this.isInternetAvailable = isInternetAvailable;
    }

    public Double getInternationalCallPerMinutePrice() {
        return internationalCallPerMinutePrice;
    }

    public void setInternationalCallPerMinutePrice(Double internationalCallPerMinutePrice) {
        this.internationalCallPerMinutePrice = internationalCallPerMinutePrice;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        RoamingTariff that = (RoamingTariff) object;
        return Objects.equals(isInternetAvailable, that.isInternetAvailable) &&
                Objects.equals(internationalCallPerMinutePrice, that.internationalCallPerMinutePrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isInternetAvailable, internationalCallPerMinutePrice);
    }

    @Override
    public String toString() {
        String result = String.format("Roaming tariff : %s, internet included - %s, international call price - %.2f.",
                super.toString(), isInternetAvailable, internationalCallPerMinutePrice);

        return result;
    }
}
