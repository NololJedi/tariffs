package by.epam.tariffs.entities.tariff;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InternetForMobileTariff")
@XmlType(propOrder = {"megaBytesCount", "megaBytePrice"})
public class InternetForMobileTariff extends AbstractTariff {

    private Integer megaBytesCount;
    private Double megaBytePrice;

    public InternetForMobileTariff() {
    }

    public InternetForMobileTariff(String tariffName, Operator operator, CallPrices callPrices, Double payroll, Double smsPrice,
                                   Parameters parameters, Integer megaBytesCount, Double megaBytePrice) {
        super(tariffName, operator, callPrices, payroll, smsPrice, parameters);
        this.megaBytesCount = megaBytesCount;
        this.megaBytePrice = megaBytePrice;
    }

    public Integer getMegaBytesCount() {
        return megaBytesCount;
    }

    public void setMegaBytesCount(Integer megaBytesCount) {
        this.megaBytesCount = megaBytesCount;
    }

    public Double getMegaBytePrice() {
        return megaBytePrice;
    }

    public void setMegaBytePrice(Double megaBytePrice) {
        this.megaBytePrice = megaBytePrice;
    }

    @Override
    public String toString() {
        String result = String.format("Internet for mobile tariff : %s, megabytes - %d, one megabyte price - %.2f.",
                super.toString(), megaBytesCount, megaBytePrice);

        return result;
    }
}