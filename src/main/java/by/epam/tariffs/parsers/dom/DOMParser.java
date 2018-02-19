package by.epam.tariffs.parsers.dom;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import by.epam.tariffs.parsers.Parser;
import by.epam.tariffs.parsers.dom.builders.InternetForMobileTariffBuilder;
import by.epam.tariffs.parsers.dom.builders.RoamingTariffBuilder;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.tariffs.util.ValueInjector.INTERNET_FOR_MOBILE_ELEMENT_NAME;
import static by.epam.tariffs.util.ValueInjector.ROAMING_TARIFF_ELEMENT_NAME;

public class DOMParser implements Parser {

    private static final Logger LOGGER = Logger.getLogger(DOMParser.class);

    public Tariffs parseTariffsFromFile(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        LOGGER.info("Start DOM parsing.");

        try {

            List<AbstractTariff> listOfTariffs = new ArrayList<>();
            Tariffs tariffs = new Tariffs();

            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList roamingTariffsListFromXml = root.getElementsByTagName(ROAMING_TARIFF_ELEMENT_NAME);
            NodeList internetForMobileTariffsListFromXml = root.getElementsByTagName(INTERNET_FOR_MOBILE_ELEMENT_NAME);

            List<AbstractTariff> roamingTariffs = ejectTariffsFromNodeList(roamingTariffsListFromXml, ROAMING_TARIFF_ELEMENT_NAME);
            List<AbstractTariff> internetForMobileTariffs =
                    ejectTariffsFromNodeList(internetForMobileTariffsListFromXml, INTERNET_FOR_MOBILE_ELEMENT_NAME);

            listOfTariffs.addAll(roamingTariffs);
            listOfTariffs.addAll(internetForMobileTariffs);

            tariffs.setListOfTariffs(listOfTariffs);

            LOGGER.info("DOM parsing was made successfully.");

            return tariffs;

        } catch (ParserConfigurationException | SAXException exception) {
            throw new XMLParserException("DOM parsing failed.", exception);
        } catch (IOException exception) {
            throw new IncorrectFileException("Something wrong with file.",exception);
        }

    }

    private List<AbstractTariff> ejectTariffsFromNodeList(NodeList nodeList, String tariffXmlType) {
        List<AbstractTariff> tariffs = new ArrayList<>();

        for (int listIndex = 0; listIndex < nodeList.getLength(); listIndex++) {
            Element tariffElement = (Element) nodeList.item(listIndex);

            if (tariffXmlType.equals(ROAMING_TARIFF_ELEMENT_NAME)) {
                RoamingTariffBuilder roamingTariffBuilder = new RoamingTariffBuilder();
                RoamingTariff roamingTariff = roamingTariffBuilder.buildRoamingTariff(tariffElement);

                tariffs.add(roamingTariff);
            }

            if (tariffXmlType.equals(INTERNET_FOR_MOBILE_ELEMENT_NAME)) {
                InternetForMobileTariffBuilder internetForMobileTariffBuilder = new InternetForMobileTariffBuilder();
                InternetForMobileTariff internetForMobileTariff = internetForMobileTariffBuilder.buildInternetForMobileTariff(tariffElement);

                tariffs.add(internetForMobileTariff);
            }

        }

        return tariffs;
    }

}
