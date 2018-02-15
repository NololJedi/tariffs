package by.epam.tariffs.util.parsers.dom;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.entities.tariff.AbstractTariff;
import by.epam.tariffs.entities.tariff.InternetForMobileTariff;
import by.epam.tariffs.entities.tariff.RoamingTariff;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;
import by.epam.tariffs.util.parsers.dom.builders.InternetForMobileTariffBuilder;
import by.epam.tariffs.util.parsers.dom.builders.RoamingTariffBuilder;
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

public class DOMParser {

    private static final String ROAMING_TARIFF_XML_TYPE = "RoamingTariff";
    private static final String INTERNET_FOR_MOBILE_XML_TYPE = "InternetForMobileTariff";

    public Tariffs parseTariffsFromFile(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        try {

            List<AbstractTariff> listOfTariffs = new ArrayList<>();
            Tariffs tariffs = new Tariffs();

            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList roamingTariffsListFromXml = root.getElementsByTagName(ROAMING_TARIFF_XML_TYPE);
            NodeList internetForMobileTariffsListFromXml = root.getElementsByTagName(INTERNET_FOR_MOBILE_XML_TYPE);

            List<AbstractTariff> roamingTariffs = ejectTariffsFromNodeList(roamingTariffsListFromXml, ROAMING_TARIFF_XML_TYPE);
            List<AbstractTariff> internetForMobileTariffs =
                    ejectTariffsFromNodeList(internetForMobileTariffsListFromXml, INTERNET_FOR_MOBILE_XML_TYPE);

            listOfTariffs.addAll(roamingTariffs);
            listOfTariffs.addAll(internetForMobileTariffs);

            tariffs.setListOfTariffs(listOfTariffs);

            return tariffs;
        } catch (ParserConfigurationException | SAXException exception) {
            throw new XMLParserException(exception);
        } catch (IOException e) {
            throw new IncorrectFileException(e);
        }

    }

    private List<AbstractTariff> ejectTariffsFromNodeList(NodeList nodeList, String tariffXmlType) {
        List<AbstractTariff> tariffs = new ArrayList<>();

        for (int listIndex = 0; listIndex < nodeList.getLength(); listIndex++) {
            Element tariffElement = (Element) nodeList.item(listIndex);

            if (tariffXmlType.equals(ROAMING_TARIFF_XML_TYPE)) {
                RoamingTariffBuilder roamingTariffBuilder = new RoamingTariffBuilder();
                RoamingTariff roamingTariff = roamingTariffBuilder.buildRoamingTariff(tariffElement);

                tariffs.add(roamingTariff);
            }

            if (tariffXmlType.equals(INTERNET_FOR_MOBILE_XML_TYPE)) {
                InternetForMobileTariffBuilder internetForMobileTariffBuilder = new InternetForMobileTariffBuilder();
                InternetForMobileTariff internetForMobileTariff = internetForMobileTariffBuilder.buildInternetForMobileTariff(tariffElement);

                tariffs.add(internetForMobileTariff);
            }

        }

        return tariffs;
    }

}
