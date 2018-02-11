package by.epam.tariffs.entities;

import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.entities.tariff.Tariff;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Tariffs")
@XmlSeeAlso({RoamingTariff.class, InternetForMobileTariff.class})
@XmlAccessorType(XmlAccessType.NONE)
public class Tariffs {
    @XmlAnyElement
    private List<Tariff> listOfTariffs = new ArrayList<Tariff>();

    public Tariffs() {
    }

    public Tariffs(List<Tariff> listOfTariffs) {
        this.listOfTariffs = listOfTariffs;
    }

    public List<Tariff> getListOfTariffs() {
        return this.listOfTariffs;
    }

    public void setListOfTariffs(List<Tariff> listOfTariffs) {
        this.listOfTariffs = listOfTariffs;
    }

    public boolean add(Tariff tariff) {
        return this.listOfTariffs.add(tariff);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Tariff tariff : listOfTariffs) {
            stringBuilder.append(tariff.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
