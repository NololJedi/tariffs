package by.epam.tariffs.entities;

import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.entities.tariff.AbstractTariff;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Tariffs")
@XmlSeeAlso({RoamingTariff.class, InternetForMobileTariff.class})
@XmlAccessorType(XmlAccessType.NONE)
public class Tariffs {
    @XmlAnyElement
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
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractTariff tariff : listOfTariffs) {
            stringBuilder.append(tariff.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
