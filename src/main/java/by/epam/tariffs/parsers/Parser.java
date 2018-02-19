package by.epam.tariffs.parsers;

import by.epam.tariffs.entities.Tariffs;
import by.epam.tariffs.exceptions.IncorrectFileException;
import by.epam.tariffs.exceptions.XMLParserException;

public interface Parser {

    Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException;

}
