package by.epam.tariffs.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"inSideCallPerMinutePrice", "outSideCallPerMinutePrice", "cityLineCallPerMinutePrice"})
public class CallPrices {

    private double inSideCallPerMinutePrice;
    private double outSideCallPerMinutePrice;
    private double cityLineCallPerMinutePrice;

    public CallPrices() {
    }

    public CallPrices(double inSideCallPerMinutePrice, double outSideCallPerMinutePrice, double cityLineCallPerMinutePrice) {
        this.inSideCallPerMinutePrice = inSideCallPerMinutePrice;
        this.outSideCallPerMinutePrice = outSideCallPerMinutePrice;
        this.cityLineCallPerMinutePrice = cityLineCallPerMinutePrice;
    }

    public double getInSideCallPerMinutePrice() {
        return inSideCallPerMinutePrice;
    }

    public void setInSideCallPerMinutePrice(double inSideCallPerMinutePrice) {
        this.inSideCallPerMinutePrice = inSideCallPerMinutePrice;
    }

    public double getOutSideCallPerMinutePrice() {
        return outSideCallPerMinutePrice;
    }

    public void setOutSideCallPerMinutePrice(double outSideCallPerMinutePrice) {
        this.outSideCallPerMinutePrice = outSideCallPerMinutePrice;
    }

    public double getCityLineCallPerMinutePrice() {
        return cityLineCallPerMinutePrice;
    }

    public void setCityLineCallPerMinutePrice(double cityLineCallPerMinutePrice) {
        this.cityLineCallPerMinutePrice = cityLineCallPerMinutePrice;
    }

    @Override
    public String toString() {
        String result = String.format(" Same operator - %.2f. Different operator - %.2f. City line - %.2f",
                inSideCallPerMinutePrice, outSideCallPerMinutePrice, cityLineCallPerMinutePrice);

        return result;
    }
}
