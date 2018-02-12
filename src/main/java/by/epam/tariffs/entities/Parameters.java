package by.epam.tariffs.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"isFavoriteNumberAvailable", "tariffication", "connectionPrice"})
@XmlRootElement(name = "Parameters")
public class Parameters {

    private Boolean isFavoriteNumberAvailable;
    private Tariffication tariffication;
    private Double connectionPrice;

    public Parameters() {
    }

    public Parameters(Boolean isFavoriteNumberAvailable, Tariffication tariffication, Double connectionPrice) {
        this.isFavoriteNumberAvailable = isFavoriteNumberAvailable;
        this.tariffication = tariffication;
        this.connectionPrice = connectionPrice;
    }

    public Boolean getIsFavoriteNumberAvailable() {
        return isFavoriteNumberAvailable;
    }

    public void setIsFavoriteNumberAvailable(Boolean isFavoriteNumberAvailable) {
        this.isFavoriteNumberAvailable = isFavoriteNumberAvailable;
    }

    public Tariffication getTariffication() {
        return tariffication;
    }

    public void setTariffication(Tariffication tariffication) {
        this.tariffication = tariffication;
    }

    public Double getConnectionPrice() {
        return connectionPrice;
    }

    public void setConnectionPrice(Double connectionPrice) {
        this.connectionPrice = connectionPrice;
    }

    @Override
    public String toString() {
        String result = String.format(" favorite number included - %s, tariffication - %s, connection price - %.2f",
                isFavoriteNumberAvailable, tariffication, connectionPrice);

        return result;
    }
}
