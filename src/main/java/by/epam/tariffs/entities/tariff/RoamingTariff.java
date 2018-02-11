package by.epam.tariffs.entities.tariff;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RoamingTariff")
@XmlType(propOrder = {"isInternetAvailable", "internationalCallPerMinutePrice"})
public class RoamingTariff extends Tariff {

    private boolean isInternetAvailable;
    private double internationalCallPerMinutePrice;

    public RoamingTariff() {
    }

    public RoamingTariff(String tariffName, Operator operator, CallPrices callPrices, double payroll, double smsPrice,
                         Parameters parameters, boolean isInternetAvailable, double internationalCallPerMinutePrice) {
        super(tariffName, operator, callPrices, payroll, smsPrice, parameters);
        this.isInternetAvailable = isInternetAvailable;
        this.internationalCallPerMinutePrice = internationalCallPerMinutePrice;
    }

    public boolean getIsInternetAvailable() {
        return isInternetAvailable;
    }

    public void setIsInternetAvailable(boolean isInternetAvailable) {
        this.isInternetAvailable = isInternetAvailable;
    }

    public double getInternationalCallPerMinutePrice() {
        return internationalCallPerMinutePrice;
    }

    public void setInternationalCallPerMinutePrice(double internationalCallPerMinutePrice) {
        this.internationalCallPerMinutePrice = internationalCallPerMinutePrice;
    }

    @Override
    public String toString() {
        String result = String.format("Roaming tariff : %s, internet included - %s, international call price - %.2f.",
                super.toString(), isInternetAvailable, internationalCallPerMinutePrice);

        return result;
    }
}
