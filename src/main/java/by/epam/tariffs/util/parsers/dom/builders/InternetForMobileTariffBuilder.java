package by.epam.tariffs.util.parsers.dom.builders;

import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.util.parsers.dom.ValueInjector;
import org.w3c.dom.Element;

public class InternetForMobileTariffBuilder {

    private static final String MEGABYTES_COUNT_ELEMENT_NAME = "megaBytesCount";
    private static final String MEGABYTE_PRICE_ELEMENT_NAME = "megaBytePrice";

    public InternetForMobileTariff buildInternetForMobileTariff(Element element) {
        AbstractTariffBuilder abstractTariffBuilder = new AbstractTariffBuilder();
        AbstractTariff abstractTariff = abstractTariffBuilder.buildAbstractTariff(element);

        InternetForMobileTariff internetForMobileTariff = new InternetForMobileTariff(abstractTariff);

        Integer megaBytesCount = ValueInjector.getIntegerValueFromElement(element, MEGABYTES_COUNT_ELEMENT_NAME);
        Double megaBytePrice = ValueInjector.getDoubleValueFromElement(element, MEGABYTE_PRICE_ELEMENT_NAME);

        internetForMobileTariff.setMegaBytesCount(megaBytesCount);
        internetForMobileTariff.setMegaBytePrice(megaBytePrice);

        return internetForMobileTariff;
    }

}
