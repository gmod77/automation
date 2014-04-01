package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

public class UD_FooterHelper_Client_v2 extends IHelper {

    public UD_FooterHelper_Client_v2(WebDriver client) {
       super(client,"UD_Footer_v2.xml");
    }

    public WebElement getAboutUsHomePageLink() {
        return locateWebElement("AboutUs");
    }
    
    public WebElement getSignUpHomePageLink() {
        return locateWebElement("SignUp");
    }
    
    public WebElement getMyUDHomePageLink() {
        return locateWebElement("MyUD");
    }
    
    public WebElement getContactHomePageLink() {
        return locateWebElement("Contact");
    }
    
    public WebElement getJobsHomePageLink() {
        return locateWebElement("Jobs");
    }
    
    public WebElement getAdvertiseHomePageLink() {
        return locateWebElement("Advertise");
    }
    
    public WebElement getTipsHomePageLink() {
        return locateWebElement("Tips");
    }
    
    public WebElement getEmailIssuesHomePageLink() {
        return locateWebElement("EmailIssues");
    }
    
    public WebElement getPrivacyPolicyHomePageLink() {
        return locateWebElement("PrivacyPolicy");
    }
    
    public WebElement getUserAgreementHomePageLink() {
        return locateWebElement("UserAgreement");
    }
    
    public WebElement getUnsubscribeHomePageLink() {
        return locateWebElement("Unsubscribe");
    }
    
    public WebElement getEditorialPolicyHomePageLink() {
        return locateWebElement("EditorialPolicy");
    }

    public WebElement getMobileSiteHomePageLink() {
        return locateWebElement("MobileSite");
    }

    public WebElement getAtlantaHomePageLink() {
        return locateWebElement("Atlanta");
    }
    
    public WebElement getBostonHomePageLink() {
        return locateWebElement("Boston");
    }
    
    public WebElement getChicagoHomePageLink() {
        return locateWebElement("Chicago");
    }
    
    public WebElement getDallasHomePageLink() {
        return locateWebElement("Dallas");
    }
    
    public WebElement getDCHomePageLink() {
        return locateWebElement("DC");
    }
    
    public WebElement getDrivenHomePageLink() {
        return locateWebElement("Driven");
    }
    
    public WebElement getJetsetHomePageLink() {
        return locateWebElement("Jetset");
    }
    
    public WebElement getLasVegasHomePageLink() {
        return locateWebElement("LasVegas");
    }
    
    public WebElement getLosAngelesHomePageLink() {
        return locateWebElement("LosAngeles");
    }
    
    public WebElement getMiamiHomePageLink() {
        return locateWebElement("Miami");
    }
    
    public WebElement getNationalHomePageLink() {
        return locateWebElement("National");
    }
    
    public WebElement getNewYorkHomePageLink() {
        return locateWebElement("NewYork");
    }
    
    public WebElement getSanFranciscoHomePageLink() {
        return locateWebElement("SanFrancisco");
    }
    
    public WebElement getSkiBoardHomePageLink() {
        return locateWebElement("SkiBoard");
    }

}