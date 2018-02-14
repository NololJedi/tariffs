package by.epam.tariffs.entities;

import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "Tariffs")
@XmlSeeAlso({RoamingTariff.class, InternetForMobileTariff.class})
@XmlAccessorType(XmlAccessType.NONE)
public class Tariffs {

    @XmlElements({
            @XmlElement(name = "RoamingTariff", type = RoamingTariff.class),
            @XmlElement(name = "InternetForMobileTariff", type = InternetForMobileTariff.class)
    })
    private List<AbstractTariff> listOfTariffs = new ArrayList<AbstractTariff>();

    public Tariffs() {
    }

    public Tariffs(List<AbstractTariff> listOfTariffs) {
        this.listOfTariffs = listOfTariffs;
    }

    public List<AbstractTariff> getListOfTariffs() {
        return this.listOfTariffs;
    }

    public void setListOfTariffs(List<AbstractTariff> listOfTariffs) {
        this.listOfTariffs = listOfTariffs;
    }

    public boolean add(AbstractTariff tariff) {
        return this.listOfTariffs.add(tariff);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Tariffs thatTariffs = (Tariffs) object;
        List<AbstractTariff> thatTariffsList = thatTariffs.getListOfTariffs();

        for (int listIndex = 0; listIndex < listOfTariffs.size(); listIndex++) {
            if (!listOfTariffs.get(listIndex).equals(thatTariffsList.get(listIndex))){
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {

        return Objects.hash(listOfTariffs);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractTariff tariff : listOfTariffs) {
            stringBuilder.append(tariff.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
