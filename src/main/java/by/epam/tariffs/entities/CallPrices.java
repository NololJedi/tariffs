package by.epam.tariffs.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"inComingCallPerMinutePrice", "outComingCallPerMinutePrice", "cityLineCallPerMinutePrice"})
public class CallPrices {

    private Double inComingCallPerMinutePrice;
    private Double outComingCallPerMinutePrice;
    private Double cityLineCallPerMinutePrice;

    public CallPrices() {
    }

    public CallPrices(Double inComingCallPerMinutePrice, Double outComingCallPerMinutePrice, Double cityLineCallPerMinutePrice) {
        this.inComingCallPerMinutePrice = inComingCallPerMinutePrice;
        this.outComingCallPerMinutePrice = outComingCallPerMinutePrice;
        this.cityLineCallPerMinutePrice = cityLineCallPerMinutePrice;
    }

    public Double getInComingCallPerMinutePrice() {
        return inComingCallPerMinutePrice;
    }

    public void setInComingCallPerMinutePrice(Double inComingCallPerMinutePrice) {
        this.inComingCallPerMinutePrice = inComingCallPerMinutePrice;
    }

    public Double getOutComingCallPerMinutePrice() {
        return outComingCallPerMinutePrice;
    }

    public void setOutComingCallPerMinutePrice(Double outComingCallPerMinutePrice) {
        this.outComingCallPerMinutePrice = outComingCallPerMinutePrice;
    }

    public Double getCityLineCallPerMinutePrice() {
        return cityLineCallPerMinutePrice;
    }

    public void setCityLineCallPerMinutePrice(Double cityLineCallPerMinutePrice) {
        this.cityLineCallPerMinutePrice = cityLineCallPerMinutePrice;
    }

    @Override
    public String toString() {
        String result = String.format(" Same operator - %.2f. Different operator - %.2f. City line - %.2f",
                inComingCallPerMinutePrice, outComingCallPerMinutePrice, cityLineCallPerMinutePrice);

        return result;
    }
}
