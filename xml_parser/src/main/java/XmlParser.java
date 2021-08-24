import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public static void main() {
        System.out.println("\n\tДомашнее задание к занятию 1.5: Работа с файлами CSV, XML, JSON\n" +
                "\tЗадача 2: XML - JSON парсер\n");

        String fileName = "data.xml";
        List<Employee> list = parseXML(fileName);
        String json = Methods.listToJson(list);
        Methods.writeString(json, "outputXML.json");
    }

    private static List<Employee> parseXML(String fileName) {
        List<Employee> employees = null;
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            Node root = doc.getDocumentElement();
            System.out.println("\tКорневой элемент: " + root.getNodeName());
            employees = readXML(root);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static List<Employee> readXML(Node node) {
        NodeList nodeList = node.getChildNodes();
        List<String> attrs = new ArrayList<>();
        List<Employee> emps = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node tempNode = nodeList.item(i);
            if (Node.ELEMENT_NODE == tempNode.getNodeType()) {
                System. out.println("\t\tТекущий узел: " + tempNode.getNodeName());
                if (tempNode.getNodeName().equals("employee")) {
                    Element element = (Element) tempNode;
                    NodeList fields = element.getChildNodes();
                    for (int j = 0; j < fields.getLength(); j++) {
                        Node field = fields.item(j);
                        if (!field.getNodeName().equals("#text")) {
                            String attrName = field.getNodeName();
                            String attrValue = field.getTextContent();
                            System.out.println("\t\t\tПоле: " + attrName + "; значение: " + attrValue);
                            attrs.add(attrValue);
                        }
                    }
                    if (attrs.size() == 5) {
                        emps.add(new Employee(Long.parseLong(attrs.get(0)), attrs.get(1), attrs.get(2), attrs.get(3), Integer.parseInt(attrs.get(4))));
                        attrs.clear();
                    }
                }
                //readXML(tempNode);
            }
        }
        return emps;
    }
}