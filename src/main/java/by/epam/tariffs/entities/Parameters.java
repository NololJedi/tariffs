package by.epam.tariffs.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Parameters")
public class Parameters {

    @XmlAttribute
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Parameters that = (Parameters) object;
        return Objects.equals(isFavoriteNumberAvailable, that.isFavoriteNumberAvailable) &&
                tariffication == that.tariffication &&
                Objects.equals(connectionPrice, that.connectionPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isFavoriteNumberAvailable, tariffication, connectionPrice);
    }

    @Override
    public String toString() {
        String result = String.format(" favorite number included - %s, tariffication - %s, connection price - %.2f",
                isFavoriteNumberAvailable, tariffication, connectionPrice);

        return result;
    }
}
