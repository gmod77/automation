package org.geno.locators;


import org.openqa.selenium.By;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLTester {
    static NodeList nodes;
    public XMLTester(String xmlName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File file = new File(xmlName);
            Document doc = dBuilder.parse(file);
            Element docEle = doc.getDocumentElement();
            nodes = docEle.getElementsByTagName("element");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new XMLTester("src/test/java/org/geno/locators/UD_SignUp_v2.xml");

        String locatorName = "SignUpEmail";

        By ret = null;

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            if (node.getAttributes().getNamedItem("name").getNodeValue().equals(locatorName)) {

                String locatorType = node.getAttributes().getNamedItem("locator_type").getNodeValue().toLowerCase();
                String locatorValue = node.getAttributes().getNamedItem("locator_value").getNodeValue();

                if (locatorType.equals("id")) {
                    ret = By.id(locatorValue);
                } else if (locatorType.equals("name")) {
                    ret = By.name(locatorValue);
                } else if (locatorType.equals("classname")) {
                    ret = By.className(locatorValue);
                } else if (locatorType.equals("linktext")) {
                    ret = By.linkText(locatorValue);
                } else if (locatorType.equals("partiallinktext")) {
                    ret = By.partialLinkText(locatorValue);
                } else if (locatorType.equals("css")) {
                    ret = By.cssSelector(locatorValue);
                } else if (locatorType.equals("xpath")) {
                    ret = By.xpath(locatorValue);
                } else if (locatorType.equals("tagname")) {
                    ret = By.tagName(locatorValue);
                } else {
                    //no get here
                }

                break;

            }

//        System.out.println(xml.nodes.getLength());
//        for (int i = 0; i < xml.nodes.getLength(); i++) {
//            NamedNodeMap stuff = xml.nodes.item(i).getAttributes();
//            for (int j = 0; j <stuff.getLength(); j++) {
//                System.out.println(stuff.item(j).toString());
//            }

//                NodeList stuff = xml.nodes.item(i).getChildNodes();
//                for (int j = 0; j < stuff.getLength(); j++) {
//                    if (stuff.item(j).hasChildNodes()) {
//                        System.out.println(stuff.item(j).getNodeName());
//                    }
//                }
//
//            }
        }
        System.out.println(ret);
    }
}



