package by.epam.tariffs.parsers.dom.builders;

import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import org.w3c.dom.Element;

import static by.epam.tariffs.util.ValueInjector.*;

public class InternetForMobileTariffBuilder {

    public InternetForMobileTariff buildInternetForMobileTariff(Element element) {
        AbstractTariffBuilder abstractTariffBuilder = new AbstractTariffBuilder();
        AbstractTariff abstractTariff = abstractTariffBuilder.buildAbstractTariff(element);

        InternetForMobileTariff internetForMobileTariff = new InternetForMobileTariff(abstractTariff);

        Integer megaBytesCount = getIntegerValueFromElement(element, MEGABYTES_COUNT_ELEMENT_NAME);
        Double megaBytePrice = getDoubleValueFromElement(element, MEGABYTE_PRICE_ELEMENT_NAME);

        internetForMobileTariff.setMegaBytesCount(megaBytesCount);
        internetForMobileTariff.setMegaBytePrice(megaBytePrice);

        return internetForMobileTariff;
    }

}
