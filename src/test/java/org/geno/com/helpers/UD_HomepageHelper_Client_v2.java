package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

public class UD_HomepageHelper_Client_v2 extends IHelper {

    public UD_HomepageHelper_Client_v2(WebDriver client) {
        super(client,"UD_HomePage_v2.xml");
    }
    
//HomePage
    
    public WebElement getUDLogo() {
        return locateWebElement("UDLogo");
    }
    
    public WebElement getClickableCopy() {
        return locateWebElement("ClickableCopy");
    }
    
    public WebElement getSignUpSeal() {
        return locateWebElement("SignUpSeal");
    }

    public WebElement getAtlantaLink() {
        return locateWebElement("AtlantaLink");
    }
    
    public WebElement getBostonLink() {
        return locateWebElement("BostonLink");
    }
    
    public WebElement getChicagoLink() {
        return locateWebElement("ChicagoLink");
    }
    
    public WebElement getDallasLink() {
        return locateWebElement("DallasLink");
    }
    
    public WebElement getWashingtonDCLink() {
        return locateWebElement("WashingtonDCLink");
    }
    
    public WebElement getDrivenLink() {
        return locateWebElement("DrivenLink");
    }
        
    public WebElement getJetsetLink() {
        return locateWebElement("JetsetLink");
    }

    public WebElement getLasVegasLink() {
        return locateWebElement("LasVegasLink");
    }

    public WebElement getLosAngelesLink() {
        return locateWebElement("LosAngelesLink");
    }

    public WebElement getMiamiLink() {
        return locateWebElement("MiamiLink");
    }

    public WebElement getNationalLink() {
        return locateWebElement("NationalLink");
    }
        
    public WebElement getNewYorkLink() {
        return locateWebElement("NewYorkLink");
    }

    public WebElement getSanFranciscoLink() {
        return locateWebElement("SanFranciscoLink");
    }
    
    public WebElement getSkiBoardLink() {
        return locateWebElement("SkiBoardLink");
    }

}