package org.geno.com.pageObjects.VacationSupremacy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sargenziano
 * Date: 6/25/13
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class VSInviteFriendsEmail {

    private final WebDriver driver;

    public VSInviteFriendsEmail (WebDriver driver) {
        this.driver = driver;
    }

    private WebElement enterButton;

    private WebElement officialRulesLink;

    private WebElement privacyPolicyLink;

    private WebElement emailBodyText;

    public VSEntrancePage clickEnterButton() {
        enterButton.click();
        return PageFactory.initElements(driver, VSEntrancePage.class);
    }

    // 1a.) Upon click of "enter", the user will be redirected to the site - (use this URL for tracking purposes: http://ud.cm/mn32b
    public String getEnterButtonLink() {
        return enterButton.getAttribute("href");
    }

    public String getOfficialRulesLink() {
        return officialRulesLink.getAttribute("href");
    }

    public String getPrivacyPolicyLink() {
        return privacyPolicyLink.getAttribute("href");
    }

    public boolean bodyContainsEmail (String email) {
        return emailBodyText.getText().contains(email);
    }

}
