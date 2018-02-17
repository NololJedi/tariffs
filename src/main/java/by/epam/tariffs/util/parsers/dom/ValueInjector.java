package by.epam.tariffs.util.parsers.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ValueInjector {

    public static final int CURRENT_ELEMENT_INDEX = 0;

    private static String injectValueFromElement(Element element, String valueName) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }
        if (valueName == null || valueName.isEmpty()) {
            throw new IllegalArgumentException("Incorrect value name was detected.");
        }

        if (element.hasAttribute(valueName)) {
            String value = element.getAttribute(valueName);

            return value;
        } else {
            NodeList nList = element.getElementsByTagName(valueName);
            Node node = nList.item(CURRENT_ELEMENT_INDEX);
            String value = node.getTextContent();

            return value;
        }
    }

    public static Double getDoubleValueFromElement(Element element, String valueName) {
        String value = injectValueFromElement(element, valueName);
        Double result = Double.parseDouble(value);

        return result;
    }

    public static Integer getIntegerValueFromElement(Element element, String valueName) {
        String value = injectValueFromElement(element, valueName);

        Integer result = Integer.parseInt(value);

        return result;
    }

    public static Boolean getBooleanValueFromElement(Element element, String valueName) {
        String value = injectValueFromElement(element, valueName);

        Boolean result = Boolean.parseBoolean(value);

        return result;
    }

    public static Enum getEnumValueFromElement(Element element, String valueName, Class enumType) {
        String value = injectValueFromElement(element, valueName);
        Enum result = Enum.valueOf(enumType, value);

        return result;
    }
}
