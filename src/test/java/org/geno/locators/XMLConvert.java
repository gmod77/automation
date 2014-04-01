package org.geno.locators;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLConvert {
    NodeList nodes;
    public XMLConvert(String xmlName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File file = new File(xmlName);
            Document doc = dBuilder.parse(file);
            Element docEle = doc.getDocumentElement();
            nodes = docEle.getChildNodes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        XMLConvert xml = new XMLConvert("src/test/java/org/geno/locators/CheckEmail_v2.xml");
        System.out.println("<elements>");
        for (int i = 0; i < xml.nodes.getLength(); i++) {
            if (xml.nodes.item(i).hasChildNodes()) {
                System.out.println("<" + xml.nodes.item(i).getNodeName() + ">");
                NodeList stuff = xml.nodes.item(i).getChildNodes();
                for (int j = 0; j < stuff.getLength(); j++) {
                    if (stuff.item(j).hasChildNodes()) {
                        System.out.println("<element name=\"" + stuff.item(j).getNodeName() + "\" locator_type=\"\" locator_value=\"" + stuff.item(j).getTextContent() + "\"/>");
                        //System.out.println(stuff.item(j).getTextContent());
                    }
                }
                System.out.println("</" + xml.nodes.item(i).getNodeName() + ">");
            }
        }
        System.out.println("</elements>");
    }

}
