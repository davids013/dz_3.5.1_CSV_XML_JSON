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

//        List<Employee> list =
                parseXML(fileName);

    }

    private static void parseXML(String fileName) {
        List<Employee> employees = new ArrayList<>();
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            Node root = doc.getDocumentElement();
            System.out.println("Корневой элемент: " + root.getNodeName());
            readXML(root);


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void readXML(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node tempNode = nodeList.item(i);
            if (Node.ELEMENT_NODE == tempNode.getNodeType()) {
                System. out.println("Текущий узел: " + tempNode.getNodeName());
                if (tempNode.getNodeName().equals("employee")) {
                    //System.out.println("\tНыряй сюда!");
                    Element element = (Element) tempNode;
                    //System.out.println("<->" + element.getTextContent());
                    NodeList fields = element.getChildNodes();
                    for (int j = 0; j < fields.getLength(); j++) {
                        Node field = fields.item(j);
                        if (!field.getNodeName().equals("#text")) {
                            String attrName = field.getNodeName();
                            String attrValue = field.getTextContent();
                            System. out.println("Атрибут: " + attrName + "; значение: " + attrValue);
                        }

                    }
                }
                //readXML(tempNode);
            }
        }

    }
}
