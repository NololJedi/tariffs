package by.epam.tariffs.entities.tariff;

import by.epam.tariffs.entities.CallPrices;
import by.epam.tariffs.entities.Operator;
import by.epam.tariffs.entities.Parameters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "InternetForMobileTariff")
public class InternetForMobileTariff extends AbstractTariff {

    @XmlElement(name = "megaBytesCount")
    private Integer megaBytesCount;
    @XmlElement(name = "megaBytePrice")
    private Double megaBytePrice;

    public InternetForMobileTariff() {
    }

    public InternetForMobileTariff(AbstractTariff abstractTariff) {
        super(abstractTariff);
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
        InternetForMobileTariff that = (InternetForMobileTariff) object;
        return Objects.equals(megaBytesCount, that.megaBytesCount) &&
                Objects.equals(megaBytePrice, that.megaBytePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), megaBytesCount, megaBytePrice);
    }

    @Override
    public String toString() {
        return "InternetForMobileTariff{" +
                "megaBytesCount=" + megaBytesCount +
                ", megaBytePrice=" + megaBytePrice +
                '}';
    }
}
