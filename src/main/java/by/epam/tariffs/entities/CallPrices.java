package by.epam.tariffs.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CallPrices {

    @XmlAttribute
    private Double inComingCallPerMinutePrice;

    @XmlAttribute
    private Double outComingCallPerMinutePrice;

    @XmlAttribute
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
