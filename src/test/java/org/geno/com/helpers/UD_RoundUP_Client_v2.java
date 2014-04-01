package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

public class UD_RoundUP_Client_v2 extends IHelper {

    public UD_RoundUP_Client_v2(WebDriver client) {
        super(client,"UD_RoundUP_v2.xml");
    }

    public WebElement getHeaderImage() {
        return locateWebElement("HeaderImage");
    }
                 
    public WebElement getTowerAd() {
        return locateWebElement("TowerAd");
    }

    public WebElement getThumbImage1() {
        return locateWebElement("ThumbImage1");
    }

    public WebElement getArticleTitle1() {
        return locateWebElement("Title1");
    }

    public WebElement getHeading1() {
        return locateWebElement("Heading1");
    }

    public WebElement getSubHeader1() {
        return locateWebElement("SubHeader1");
    }

    public WebElement getBlurb1() {
        return locateWebElement("Blurb1");
    }

    public WebElement getFourOneOne1() {
        return locateWebElement("FourOneOne1");
    }

}
