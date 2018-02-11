package by.epam.tariffs.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"isFavoriteNumberAvailable", "tariffing", "connectionPrice"})
@XmlRootElement(name = "Parameters")
public class Parameters {

    private boolean isFavoriteNumberAvailable;
    private int tariffing;
    private double connectionPrice;

    public Parameters() {
    }

    public Parameters(boolean isFavoriteNumberAvailable, int tariffing, double connectionPrice) {
        this.isFavoriteNumberAvailable = isFavoriteNumberAvailable;
        this.tariffing = tariffing;
        this.connectionPrice = connectionPrice;
    }

    public boolean getIsFavoriteNumberAvailable() {
        return isFavoriteNumberAvailable;
    }

    public void setIsFavoriteNumberAvailable(boolean isFavoriteNumberAvailable) {
        this.isFavoriteNumberAvailable = isFavoriteNumberAvailable;
    }

    public int getTariffing() {
        return tariffing;
    }

    public void setTariffing(int tariffing) {
        this.tariffing = tariffing;
    }

    public double getConnectionPrice() {
        return connectionPrice;
    }

    public void setConnectionPrice(double connectionPrice) {
        this.connectionPrice = connectionPrice;
    }

    @Override
    public String toString() {
        String result = String.format(" favorite number included - %s, tariffing - %d, connection price - %.2f",
                isFavoriteNumberAvailable, tariffing, connectionPrice);

        return result;
    }
}
