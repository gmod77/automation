package org.geno.com.helpers.mobile_perks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sargenziano
 * Date: 7/15/13
 * Time: 12:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobilePerksProductPage {

    private final WebDriver driver;
    protected MobilePerksHeader header;

    public MobilePerksProductPage (WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver, MobilePerksHeader.class);
    }

    @FindBy(css="#product-detail>header>h1>span")
    private WebElement perksH1headerText;

    @FindBy(css="#product-detail>header>h2")
    private WebElement perksH2headerText;

    @FindBy(css=".m-item")
    private List<WebElement> itemImages;

    @FindBy(css=".summation>p")
    private WebElement perkDescription;

    @FindBy(css=".more")
    private WebElement moreInfoButton;

    @FindBy(css=".dashboard>div")
    private List<WebElement> costInfo;

    @FindBy(css=".countdown")
    private WebElement timeRemainingClock;

    @FindBy(css=".accordion>dt:nth-child(1)")
    private WebElement logisticsAccordion;

    @FindBy(css=".accordion>.logistics>.inner")
    private WebElement logisticsAccordionInfo;

    @FindBy(css=".accordion>dt:nth-child(3)")
    private WebElement finePrintAccordion;

    @FindBy(css=".accordion>.fine-print")
    private WebElement finePrintAccordionInfo;

    @FindBy(css="form>a")
    private WebElement getItNowButton;


    public String getUdMemberPrice() {
        return costInfo.get(0).findElement(By.cssSelector(".cost")).getText();
    }

    public String getEveryoneElsePrice() {
        return costInfo.get(1).findElement(By.cssSelector(".cost")).getText();
    }

    public MobilePerksShoppingBag clickGetItNowButton() {
        getItNowButton.click();
        return PageFactory.initElements(driver,MobilePerksShoppingBag.class);
    }

    public MobilePerksProductPage clickLogisticsAccordion() {
        logisticsAccordion.click();
        return this;
    }

    public String getLogisticsAccordionText() {
        return logisticsAccordionInfo.getText();
    }

    public MobilePerksProductPage clickFinePrintAccordion() {
        finePrintAccordion.click();
        return this;
    }

    public String getFinePrintAccordionText() {
        return finePrintAccordionInfo.getText();
    }

    public MobilePerksLoginPage clickGetItNowLoggedOut() {
        getItNowButton.click();
        return PageFactory.initElements(driver,MobilePerksLoginPage.class);
    }
}
