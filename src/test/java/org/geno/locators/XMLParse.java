package org.geno.locators;


import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParse {

    NodeList nodes;

    private String LOCATORDIR = System.getProperty("user.dir") + "/src/test/java/org/geno/locators/";


    public XMLParse(String xmlName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File file = new File(LOCATORDIR + xmlName);
            Document doc = dBuilder.parse(file);
            Element docEle = doc.getDocumentElement();
            nodes = docEle.getElementsByTagName("element");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public By getLocator(String locatorName) {

        String ln = locatorName.toLowerCase();

        By ret = null;

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            if (node.getAttributes().getNamedItem("name").getNodeValue().toLowerCase().equals(ln)) {

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
                    throw new IllegalArgumentException("Locator Type '" + locatorType + "' not supported.");
                }
                break;
            }
        }
        return ret;
    }
}
