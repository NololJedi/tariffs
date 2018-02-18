package by.epam.tariffs.util.parsers;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;

public interface TariffParser {

    Tariffs parseTariffsFromFile(String xmlFilePath) throws XMLParserException, IncorrectFileException;

}
