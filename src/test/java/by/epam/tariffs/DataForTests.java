package by.epam.tariffs;

import by.epam.tariffs.entities.*;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;

import java.util.ArrayList;
import java.util.List;

public class DataForTests {

    public static final String VALID_DATA_FILE_PATH = "./src/test/resources/data.xml";
    public static final String INCORRECT_TARIFF_TYPE = "./src/test/resources/incorrect_tariff_type.xml";
    public static final String INCORRECT_FILE_PATH = "./src/test/resources/data5.xml";

    public static Tariffs getTariffs() {
        CallPrices callPrices = new CallPrices(2.2, 3.3, 4.4);
        Parameters parameters = new Parameters(true, Tariffication.PER_MINUTES, 3.3);
        RoamingTariff roamingTariff = new RoamingTariff("Life-Roaming", Operator.LIFE, callPrices, 20.0, 2.0,
                parameters, true, 2.2);

        parameters = new Parameters(false, Tariffication.PER_MINUTES, 4.5);
        callPrices = new CallPrices(1.2, 3.3, 7.4);
        InternetForMobileTariff internetForMobileTariff = new InternetForMobileTariff("MTS-Unlim", Operator.MTS, callPrices,
                10.0, 1.0, parameters, 1000, 0.5);

        parameters = new Parameters(true, Tariffication.PER_MINUTES, 3.3);
        callPrices = new CallPrices(6.2, 10.5, 4.4);
        RoamingTariff roamingTariffNumberTwo = new RoamingTariff("Velcom-Roaming", Operator.VELCOM, callPrices,
                20.0, 2.0, parameters, false, 3.2);

        parameters = new Parameters(false, Tariffication.PER_MINUTES, 3.3);
        callPrices = new CallPrices(2.2, 3.3, 4.4);
        InternetForMobileTariff internetForMobileTariffNumberTwo = new InternetForMobileTariff("Life3000", Operator.LIFE, callPrices,
                10.0, 1.0, parameters, 3000, 0.1);

        parameters = new Parameters(true, Tariffication.PER_SECONDS, 7.6);
        callPrices = new CallPrices(12.2, 13.3, 12.4);
        RoamingTariff roamingTariffNumberThree = new RoamingTariff("MTS-Roaming", Operator.MTS, callPrices, 30.0, 0.0,
                parameters, true, 3.8);

        parameters = new Parameters(true, Tariffication.PER_SECONDS, 3.3);
        callPrices = new CallPrices(2.0, 3.0, 4.0);
        InternetForMobileTariff internetForMobileTariffNumberThree = new InternetForMobileTariff("Velcom-Business",
                Operator.VELCOM, callPrices, 17.0, 0.0, parameters, 5000, 0.1);

        List<AbstractTariff> listOfTariffs = new ArrayList<>();
        listOfTariffs.add(roamingTariff);
        listOfTariffs.add(roamingTariffNumberThree);
        listOfTariffs.add(roamingTariffNumberTwo);
        listOfTariffs.add(internetForMobileTariffNumberTwo);
        listOfTariffs.add(internetForMobileTariff);
        listOfTariffs.add(internetForMobileTariffNumberThree);

        Tariffs tariffs = new Tariffs(listOfTariffs);

        return tariffs;
    }

}
