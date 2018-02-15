package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.util.parsers.dom.ElementValueInjector;
import org.w3c.dom.Element;

public class InternetForMobileTariffBuilder {

    private static final String MEGABYTES_COUNT_VALUE_NAME = "megaBytesCount";
    private static final String MEGABYTE_PRICE_VALUE_NAME = "megaBytePrice";

    public InternetForMobileTariff buildInternetForMobileTariff(Element element) {
        AbstractTariffBuilder abstractTariffBuilder = new AbstractTariffBuilder();
        AbstractTariff abstractTariff = abstractTariffBuilder.buildAbstractTariff(element);

        InternetForMobileTariff internetForMobileTariff = new InternetForMobileTariff(abstractTariff);

        String megaBytesCountValue = ElementValueInjector.injectValueFromElement(element, MEGABYTES_COUNT_VALUE_NAME);
        Integer megaBytesCount = Integer.parseInt(megaBytesCountValue);

        String megaBytePriceValue = ElementValueInjector.injectValueFromElement(element, MEGABYTE_PRICE_VALUE_NAME);
        Double megaBytePrice = Double.parseDouble(megaBytePriceValue);

        internetForMobileTariff.setMegaBytesCount(megaBytesCount);
        internetForMobileTariff.setMegaBytePrice(megaBytePrice);

        return internetForMobileTariff;
    }

}
