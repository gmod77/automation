package org.geno.com.pageObjects.udweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * PageObject file containing all objects and methods for the HomePage
 */
public class UdwebLandingPage {

    private WebDriver driver;

    public UdwebLandingPage (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy (css=".splashMainHead>img")
    private WebElement UDheaderImage;

    @FindBy (css=".splashMainHead>.ajaxSignup>img")
    private WebElement UDtextHeaderImage;

    @FindBy (css=".splashBadgeHolder .ajaxSignup>img")
    private WebElement signUpSealImage;

    @FindBy (css=".select_edition")
    private WebElement selectYourEditionText;

    @FindBy (css=".splashMainNav>a>img")
    private List<WebElement> cityLinkImages;

    @FindBy (css=".splashMainNav>a:nth-child(1)>img")
    private WebElement atlantaImage;

    @FindBy (css=".splashMainNav>a:nth-child(3)>img")
    private WebElement bostonImage;

    @FindBy (css=".splashMainNav>a:nth-child(5)>img")
    private WebElement chicagoImage;

    @FindBy (css=".splashMainNav>a:nth-child(7)>img")
    private WebElement dallasImage;

    @FindBy (css=".splashMainNav>a:nth-child(9)>img")
    private WebElement washingtonDcImage;

    @FindBy (css=".splashMainNav>a:nth-child(11)>img")
    private WebElement drivenImage;

    @FindBy (css=".splashMainNav>a:nth-child(13)>img")
    private WebElement jetsetImage;

    @FindBy (css=".splashMainNav>a:nth-child(15)>img")
    private WebElement lasVegasImage;

    @FindBy (css=".splashMainNav>a:nth-child(17)>img")
    private WebElement losAngelesImage;

    @FindBy (css=".splashMainNav>a:nth-child(19)>img")
    private WebElement miamiImage;

    @FindBy (css=".splashMainNav>a:nth-child(21)>img")
    private WebElement nationalImage;

    @FindBy (css=".splashMainNav>a:nth-child(23)>img")
    private WebElement newYorkImage;

    @FindBy (css=".splashMainNav>a:nth-child(25)>img")
    private WebElement sanFranImage;

    @FindBy (css=".splashMainNav>a:nth-child(27)>img")
    private WebElement skiBoardImage;

    public boolean checkSignUpSealDisplayed() {
        return signUpSealImage.isDisplayed();
    }

    public void clickSignUpSeal() {
        signUpSealImage.click();
    }

    public void getUrl() {
        System.out.println(driver.getCurrentUrl());
    }

    public void getPageTitle() {
        System.out.println(driver.getTitle());
    }

    public void getLinkElementCount() {
        System.out.println(cityLinkImages.size());
    }

}
