package by.epam.tariffs.util.parsers.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementValueInjector {

    public static final int CURRENT_ELEMENT_INDEX = 0;

    public static String injectValueFromElement(Element element, String valueName) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }
        if (valueName == null || valueName.isEmpty()) {
            throw new IllegalArgumentException("Incorrect value name was detected.");
        }

        NodeList nList = element.getElementsByTagName(valueName);
        Node node = nList.item(CURRENT_ELEMENT_INDEX);
        String value = node.getTextContent();

        return value;
    }

}
