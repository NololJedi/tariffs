package by.epam.tariffs.entities.tariff;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InternetForMobileTariff")
@XmlType(propOrder = {"megaBytesCount", "megaBytePrice"})
public class InternetForMobileTariff extends Tariff {

    private int megaBytesCount;
    private double megaBytePrice;

    public InternetForMobileTariff() {
    }

    public InternetForMobileTariff(String tariffName, Operator operator, CallPrices callPrices, double payroll, double smsPrice,
                                   Parameters parameters, int megaBytesCount, double megaBytePrice) {
        super(tariffName, operator, callPrices, payroll, smsPrice, parameters);
        this.megaBytesCount = megaBytesCount;
        this.megaBytePrice = megaBytePrice;
    }

    public int getMegaBytesCount() {
        return megaBytesCount;
    }

    public void setMegaBytesCount(int megaBytesCount) {
        this.megaBytesCount = megaBytesCount;
    }

    public double getMegaBytePrice() {
        return megaBytePrice;
    }

    public void setMegaBytePrice(double megaBytePrice) {
        this.megaBytePrice = megaBytePrice;
    }

    @Override
    public String toString() {
        String result = String.format("Internet for mobile tariff : %s, megabytes - %d, one megabyte price - %.2f.",
                super.toString(), megaBytesCount, megaBytePrice);

        return result;
    }
}
