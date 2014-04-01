package org.geno.com.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.Nullable;
import org.geno.locators.XMLParse;

import java.util.List;

/**
 * This class contains general helping methods for all
 * tests. Other helper classes are built off of this.
 * @author geno
 */

public class IHelper implements UDBase {

    private WebDriver client;
    private XMLParse xmlParse;

    public IHelper(WebDriver client, String xmlParse) {
        this.client = client;
        this.xmlParse = new XMLParse(xmlParse);
    }

    /**
     * Gets the By locator value found in the xmlfile
     * Useful for creating WebDriverWait conditions
     * @param locator input locator from appropriate xml file
     * @return By locator
     */
    public final By getWaitByLocator (final String locator) {
        return xmlParse.getLocator(locator);
    }

    /**
     * Gets the web element from the locator value passed
     * Method also waits for element to be found otherwise
     * throws a timeout exception
     * @param locator input locator from appropriate xml file
     * @return WebElement
     */
    public final WebElement locateWebElement(final String locator) {
        WebElement el;
        WebDriverWait wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        try { el = wait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(@Nullable WebDriver d) {
                        return client.findElement(xmlParse.getLocator(locator));
                    }
                });
        } catch (TimeoutException e) {
            throw new NotFoundException("Timed out waiting for " + locator + " on " + client.getCurrentUrl());
        }
        return el;
    }

    /**
     * Gets the web elements in list form from the locator value passed
     * Method also waits for element to be found otherwise
     * throws a timeout exception
     * @param locator input locator from appropriate xml file
     * @return WebElement
     */
    public final List<WebElement> locateWebElements(final String locator) {
        WebDriverWait wait = new WebDriverWait(client, GLOBAL_TIME_OUT, GLOBAL_POLLING);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xmlParse.getLocator(locator)));
        return client.findElements(xmlParse.getLocator(locator));
    }

}
