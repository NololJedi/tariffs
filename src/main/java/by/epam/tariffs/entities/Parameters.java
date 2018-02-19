package by.epam.tariffs.entities;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Parameters")
public class Parameters {

    @XmlAttribute(name = "isFavoriteNumberAvailable")
    private Boolean isFavoriteNumberAvailable;

    @XmlElement(name = "tariffication")
    private Tariffication tariffication;
    @XmlElement(name = "connectionPrice")
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
        return "Parameters{" +
                "isFavoriteNumberAvailable=" + isFavoriteNumberAvailable +
                ", tariffication=" + tariffication +
                ", connectionPrice=" + connectionPrice +
                '}';
    }
}
