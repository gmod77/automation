package org.geno.com.sauce;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *    Domain Test Methods
 *  @author geno
 */
public class iTestCaseUDSauce extends iSauceBase implements UDBase {

    private String UDcity;
    private String UDcityPerks;

    private WebDriverWait wait;
    private WebDriver client;

    private UD_AdminHelper_Client ud_adminHelper_client;
    private UD_HomepageHelper_Client_v2 ud_homepageHelper_Client;
    private UD_HeaderHelper_Client_v2 ud_headerHelper_Client;
    private UD_FooterHelper_Client_v2 ud_footerHelper_Client;
    private UD_SealHelper_Client_v2 ud_sealHelper_Client;
    private UD_SignupHelper_Client_v2 ud_signupHelper_Client;
    private UD_RoundUP_Client_v2 ud_roundUP_client;
    private UD_UnSubscribeHelper_Client_v2 ud_unSubscribeHelper_client;
    private EmailHelper_Client emailHelper_Client;

    public iTestCaseUDSauce() {

    }


// Admin Methods

    /**
     * Log into the UD Admin site
     * This is to create articles like RoundUP
     */
    public void loginUDAdmin(){
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        client.get(UD_ADMIN_DOMAIN);
        client.findElement(By.id("username")).sendKeys(UD_ADMIN_USERNAME);
        client.findElement(By.id("password")).sendKeys(UD_ADMIN_PW);
        client.findElement(By.name("commit")).click();

        try {
            wait.until(ExpectedConditions.titleIs("UD Admin | Home"));
        } catch (TimeoutException e) {
            throw new TimeoutException("Did not login to UD Admin");
        }
    }

    /**
     * Click the Dedicated checkbox for an article
     */
    public void adminArticleIsDedicated() {
        WebDriver client = getDriver();

        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        ud_adminHelper_client.getArticleIsDedicatedCheck().click();

        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    /**
     * Selects the article layout type based on parameter
     * @param layout 0 = Newsletter, 1 = Responsive Newsletter
     */
    public void adminArticleSelectLayout(int layout) {
        WebDriver client = getDriver();

        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        if (layout == 0 || layout == 1) {
            Select layoutModule = new Select(ud_adminHelper_client.getLayoutModuleDropdown());
            layoutModule.selectByIndex(layout);
        } else {
            throw new IllegalArgumentException("Layout must be 0 (Newsletter) or 1(Responsive Newsletter)");
        }

        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    /**
     * Selects the article layout width based on parameter
     * @param layout 0 = Narrow, 1 = Wide
     */
    public void adminArticleSelectLayoutWidth(int layout) {
        WebDriver client = getDriver();

        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        if (layout == 0 || layout == 1) {
            Select layoutModule = new Select(ud_adminHelper_client.getLayoutWidthDropdown());
            layoutModule.selectByIndex(layout);
        } else {
            throw new IllegalArgumentException("Layout must be 0 (Narrow) or 1(Wide)");
        }

        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    public String generateText() {
        WebDriver client = getDriver();

        return ((RemoteWebDriver) client).executeScript(
                "var xmlHttp = null;" +
                "xmlHttp = new XMLHttpRequest();" +
                "xmlHttp.open( 'GET', 'http://hipsterjesus.com/api/?paras=2&type=hipster-latin&html=false', false );" +
                "xmlHttp.send( null );" +
                "return eval('(' + xmlHttp.responseText + ')' ).text.toString();").toString();
    }

    /**
     * Fills in fields required for all article types
     * @param articleType Choose: Three-Column, Weekender, Roundup, Profile, Background Takeover, Background Takeover Weekender, Background Takeover Roundup
     * @param type Choose 1 Responsive Wide Dedicated,2 Responsive Narrow Dedicated,3 Responsive Wide,4 Responsive Narrow
     */
    public void adminDefaults (String articleType, int type) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_adminHelper_client = new UD_AdminHelper_Client(client);
        emailHelper_Client = new EmailHelper_Client(client);

        String date = emailHelper_Client.generateDate(DATEFORMAT);

        String responsiveLayoutType = "";

        switch (type) {
            case 0: responsiveLayoutType = "";
                break;
            case 1: responsiveLayoutType = " Responsive Wide Dedicated ";
                break;
            case 2: responsiveLayoutType = " Responsive Narrow Dedicated ";
                break;
            case 3: responsiveLayoutType = " Responsive Wide ";
                break;
            case 4: responsiveLayoutType = " Responsive Narrow ";
                break;
        }

        // 1. After login go to create the article
        client.get(UD_ADMIN_DOMAIN+"/articles/create");
        Assert.assertTrue(client.findElement(By.cssSelector("#sf_admin_container>h1")).
                getText().trim().equals("Edit article"),
                "Did not navigate to Create Article page. Currently> " + client.getCurrentUrl());

        // 2. change status to approved
        dropDownSelector(ud_adminHelper_client.getStatusDropdown(), "Approved");

        /*
        Move this outside of the defaults
        // 3. click dedicated
        // ud_adminHelper_client.getArticleIsDedicatedCheck().click();
         */

        // 4. choose template
        dropDownSelector(ud_adminHelper_client.getArticleTemplateDropdown(), articleType);

        // 5. choose any author
        dropDownSelector(ud_adminHelper_client.getAuthorDropdown(),"Russ Brandom");

        // 6. Enter in From Display "test <test@test.com>"
        ud_adminHelper_client.getEmailFromBox().sendKeys("test <test@test.com>");

        // 7. choose segment qa addresses
        dropDownSelector(ud_adminHelper_client.getEmailSegmentDropdown(),"QA Addresses");

        // 8. Enter an Article title
        ud_adminHelper_client.getArticleTitleBox().sendKeys("Test " + articleType + responsiveLayoutType + "Article Title " + date);

        // 9. Enter Email subject line
        ud_adminHelper_client.getArticleEmailSubjectBox().sendKeys("Test " + articleType + responsiveLayoutType + "Email Subject " + date);

        // 10. Enter Article Business Subject
        ud_adminHelper_client.getArticleBusinessSubjectBox().sendKeys("Test " + articleType + responsiveLayoutType + "Business Name " + date);

        // 11. Enter Article Blurb
        ud_adminHelper_client.getArticleBlurbBox().sendKeys(articleType + " Article Blurb Test " + responsiveLayoutType + date);

        // 12. Enter iPhone Blurb
        ud_adminHelper_client.getArticleIphoneBlurbBox().sendKeys(articleType + " iPhone Blurb Test " + responsiveLayoutType + date);

        // 13. Enter Twitter Blurb
        ud_adminHelper_client.getArticleTwitterBlurbBox().sendKeys(articleType + " Twitter Blurb Test " + responsiveLayoutType + date);

        // 14. Enter Note
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('article[footer]').SetHTML('" + articleType + responsiveLayoutType + "Article Note Test'))");

        // 15. Enter Legal Line
        ud_adminHelper_client.getArticleLegalLineBox().sendKeys(articleType + " Legal Line Test " + responsiveLayoutType + date);

        // 16. Enter Keywords
        ud_adminHelper_client.getArticleKeywordsBox().sendKeys(articleType + " Keywords Test Keywords " + responsiveLayoutType + date);

        // 17. Choose business type
        dropDownSelector(ud_adminHelper_client.getArticleBusinessTypeDropdown(),"Clothing");

        // 18. Enter Business Specialty
        ud_adminHelper_client.getBusinessSpecialtyBox().sendKeys(articleType + " Business Specialty Test " + responsiveLayoutType + date);

        // 19. Click Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    /**
    * Create a round up article
    */
    public void createRoundUP() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_adminHelper_client = new UD_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        // 7. choose any ad campaign
        dropDownSelector(ud_adminHelper_client.getAdCampaignDropdown(),"Groupon");

        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        String articleID = getArticleID();

        // 24. Click add Image
        // go to create image page
        client.get(UD_ADMIN_DOMAIN+"/article_images/create");

        // 25. Upload Email_Banner size 552 x 135
        adArticleImages(articleID,"email banner 3.jpg","Email_Banner");

        // 29. Upload thumbnail size 139 x 95
        adArticleImages(articleID,"thumbnail 3.jpg","Thumbnail");

        client.get(UD_ADMIN_DOMAIN+"/articles/edit/id/" + articleID);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_adminHelper_client.getWaitByLocator("SaveButton")));

        // 32. Add Ad module to Right module section, make it a tower
        //        a. Select ad from component dropdown

        //                a. Select ad from component dropdown

        moduleAdPlacement(5,"Ad");

        //        b. Click on newsletter_ad
        String adComponent = getAdPlacement(5,"newsletter_ad");
        client.get(adComponent);

        adminNewsLetterAdEdit("49ers SF 11-8-11 tower");

        // 33. Click "here" to add the individual articles
        client.get(UD_ADMIN_DOMAIN+"/multiarticle/edit/id/"+articleID);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sf_admin_edit_form>fieldset>table>tbody>tr>td>div")));

        // 34. Add Copy to the slots you are going to use (1 paragraph 3 sentences maxish)
        WebElement frameA = client.findElement(By.xpath(".//*[@id='sf_admin_edit_form']/fieldset/table/tbody/tr[2]/td[2]/div[1]/iframe"));
        String frameAid = frameA.getAttribute("id");

        client.switchTo().frame(frameAid);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='xToolbar']/table[6]/tbody/tr/td[6]/div/table/tbody/tr/td[2]")));
        client.findElement(By.xpath(".//*[@id='xToolbar']/table[6]/tbody/tr/td[6]/div/table/tbody/tr/td[2]")).click();
        client.findElement(By.xpath(".//*[@id='xEditingArea']/textarea")).sendKeys("Lorem Ipsum. Lorem Ipsum. Lorem Ipsum." +  date);
        client.switchTo().defaultContent();

        // 35. add in 411 and turn one word into a hyper link
        WebElement frameB = client.findElement(By.xpath(".//*[@id='sf_admin_edit_form']/fieldset/table/tbody/tr[2]/td[2]/div[2]/iframe"));
        String frameBid = frameB.getAttribute("id");

        client.switchTo().frame(frameBid);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='xToolbar']/table/tbody/tr/td[18]/div/table/tbody/tr/td[2]")));
        client.findElement(By.xpath(".//*[@id='xToolbar']/table/tbody/tr/td[18]/div/table/tbody/tr/td[2]")).click();
        client.findElement(By.xpath(".//*[@id='xEditingArea']/textarea")).sendKeys("Lorem Ipsum <a href=\"http://www.google.com\">" +  date + "</a>");
        client.switchTo().defaultContent();

        // 36. Add Header to each slot
        ud_adminHelper_client.getMultiArticleHeaderBox(1).sendKeys("RoundUp Header1 Test " + date);

        // 37. Add Sub head to each slot
        ud_adminHelper_client.getMultiArticleSubHeaderBox(1).sendKeys("RoundUp SubHeader1 Test " + date);

        // 38. add image size 120 x 120
        ud_adminHelper_client.getMultiArticleImageFileBox(1).sendKeys(IMAGE_PATH + "round up image.jpg");

        // 38a. add Alt text to slots
        ud_adminHelper_client.getMultiArticleAltTextBox(1).sendKeys("RoundUp Alt Test"+ date);

        // 39. click Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    /**
     * Confirm the created RoundUP article
     * @param articleID This comes from the return of createRoundUP()
     */
    public void confirmRoundUP(String articleID) {
        WebDriver client = getDriver();

        String url = UD_DOMAIN + "/newsletter/roundup/" + articleID + "?preview=true";
        Reporter.log("Article ID> " + articleID,true);
        client.get(url);
        Reporter.log(url,true);

        ud_roundUP_client = new UD_RoundUP_Client_v2(client);

        Assert.assertTrue(ud_roundUP_client.getArticleTitle1().isDisplayed(),"Title was not found.");
        Assert.assertTrue(ud_roundUP_client.getBlurb1().isDisplayed(),"Blurb was not found.");
        Assert.assertTrue(ud_roundUP_client.getFourOneOne1().isDisplayed(),"411 was missing.");
        Assert.assertTrue(ud_roundUP_client.getHeaderImage().isDisplayed(),"Header Image is missing.");
        Assert.assertTrue(ud_roundUP_client.getHeading1().isDisplayed(),"Heading 1 is missing.");
        Assert.assertTrue(ud_roundUP_client.getSubHeader1().isDisplayed(),"SubHeader 1 is missing.");
        Assert.assertTrue(ud_roundUP_client.getThumbImage1().isDisplayed(),"Thumbnail image 1 is missing.");
        Assert.assertTrue(ud_roundUP_client.getTowerAd().isDisplayed(), "Tower Ad is missing.");
    }

    /**
     * Create a Weekender Article
     */
    public void createArticleWeekender() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_adminHelper_client = new UD_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);


        // Begin Weekender Specific methods

        // Choose ad campaign
        dropDownSelector(ud_adminHelper_client.getAdCampaignDropdown(),"Groupon");

        // Choose any weekender category
        dropDownSelector("article_edition_category_id","NYC: The Weekender");

        //  Enter an Article SubHeader
        ud_adminHelper_client.getArticleSubHeaderBox().sendKeys("Test Weekender Article SubHeader "+ date);

        // 18. Enter text in photo credit
        ud_adminHelper_client.getArticlePhotoCreditBox().sendKeys("Weekender Photo Credits Test "+ date);

        // 19. Enter text in Article Feature
        ud_adminHelper_client.getArticleFeatureBox().sendKeys("Weekender Article/Feature Introduction Test "+ date);

        // 20. Copy is not needed
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('article[content]').SetHTML('Weekender Article Copy Test'))");

        // Save Weekender Specific methods
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // Get Article ID
        String articleID = getArticleID();

        // 17. Add Images: Email Banner, Option_A_Left_Column, and Thumbnail

        // go to create image page
        client.get(UD_ADMIN_DOMAIN+"/article_images/create");

        // browse to 1st image
        adArticleImages(articleID,"image001_optionA.jpg","Option_A_Left_Column");

        // browse to 2nd image
        adArticleImages(articleID,"image002_EmailBanner.jpg","Email_Banner");

        // browse to 3rd image
        adArticleImages(articleID,"image003_thumbnail.jpg","Thumbnail");

        // go back to the article
        client.get(UD_ADMIN_DOMAIN+"/articles/edit/id/"+articleID);

        // Save the page
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // 26. Add Ad to Bottom module
        // a. Select ad from component dropdown

        moduleAdPlacement(4,"Ad");

        // b. Click on newsletter_ad
        String bottomUrl = getAdPlacement(4,"newsletter_ad");
        client.get(bottomUrl);

        // Choose any footer ad
        adminNewsLetterAdEdit("49ers SF 11-8-11 footer");

        // 27. Add Ad to Right Module

        moduleAdPlacement(5,"Ad");

        // b. Click on newsletter_ad
        String rightNewsletterAdEditLink = getAdPlacement(5,"newsletter_ad");
        client.get(rightNewsletterAdEditLink);

        // c. Choose any Tower add.
        adminNewsLetterAdEdit("49ers SF 11-8-11 tower");

        // 31. Click save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // 32. Click “here” next to Template this takes you to individual weekenders
        //html/body/div[3]/div/div[2]/form/fieldset/div[5]/div/a

        // or:
        client.get(UD_ADMIN_DOMAIN+"/multiarticle/edit/id/"+articleID);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#sf_admin_edit_form>fieldset>table>tbody>tr>td>div")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td[2]/div[1]/iframe")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[10]/td[2]/div[2]/iframe")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#sf_admin_edit_form>fieldset>table>tbody>tr>td>div>input:nth-child(4)")));

        // 33. For each template, 3 is a good number:
        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("javascript:window.scrollBy(250,350)");
        // Day 1
        Reporter.log("Entering info for Day 1");
        // a. Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(1),"Monday");

        // b. Click show day image in title (not needed, will duplicate the day name)
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[2]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(1).sendKeys("Weekender Header1 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(1).sendKeys("Weekender SubHeader1 Test "+ date);

        // e. Put in some url for subheader URL
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(1).sendKeys("www.google.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(1).sendKeys(IMAGE_PATH + "solon.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(1).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(1).sendKeys("http://images.sodahead.com/polls/001076173/even_kittens_are_going_bad_answer_2_xlarge.jpeg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(1).sendKeys("Weekender Alt Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411
        // l. Repeat for others

        // Day 2
        Reporter.log("Entering info for Day 2");
        // a. Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(2),"Tuesday");

        // b. Click show day image in title
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[4]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(2).sendKeys("Weekender Header2 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(2).sendKeys("Weekender SubHeader2 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(2).sendKeys("www.yahoo.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(2).sendKeys(IMAGE_PATH + "plato.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(2).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(2).sendKeys("http://t0.gstatic.com/images?q=tbn:ANd9GcTYj5WyrHaLj6lqad-dIiNUTQSaKkuJmJtUKiPX3SbIpCfS-1aFqyr-mDWF");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(2).sendKeys("Weekender Alt2 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411
        // l. Repeat for others

        client.findElement(By.cssSelector("tr:nth-child(20)>td>.sf_admin_actions>li:nth-child(1)>input")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // Day 3
        Reporter.log("Entering info for Day 3");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(3),"Wednesday");
        js.executeScript("javascript:window.scrollBy(250,350)");
        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(3).sendKeys("Weekender Header3 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(3).sendKeys("Weekender SubHeader3 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(3).sendKeys("www.cnn.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(3).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(3).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(3).sendKeys("http://images.sodahead.com/polls/001076173/even_kittens_are_going_bad_answer_2_xlarge.jpeg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(3).sendKeys("Weekender Alt3 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        // Day 4
        Reporter.log("Entering info for Day 4");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(4),"Thursday");

        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(4).sendKeys("Weekender Header4 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(4).sendKeys("Weekender SubHeader4 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(4).sendKeys("www.geno.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(4).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(4).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(4).sendKeys("http://imagecache6.allposters.com/LRG/38/3842/UJXYF00Z.jpg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(4).sendKeys("Weekender Alt4 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        client.findElement(By.cssSelector("tr:nth-child(20)>td>.sf_admin_actions>li:nth-child(1)>input")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));


        // Day 5
        Reporter.log("Entering info for Day 5");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(5),"Friday");

        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(5).sendKeys("Weekender Header5 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(5).sendKeys("Weekender SubHeader5 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(5).sendKeys("perks.geno.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(5).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(5).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(5).sendKeys("http://imagecache6.allposters.com/LRG/38/3842/UJXYF00Z.jpg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(5).sendKeys("Weekender Alt5 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        // Day 6
        Reporter.log("Entering info for Day 6");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(6),"Saturday");

        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(6).sendKeys("Weekender Header6 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(6).sendKeys("Weekender SubHeader6 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(6).sendKeys("www.driven.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(6).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(6).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(6).sendKeys("http://imagecache6.allposters.com/LRG/38/3842/UJXYF00Z.jpg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(6).sendKeys("Weekender Alt6 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        //**NOTE Until deleting slots is fixed comment this out

        // m. Delete slots you do not want to use
        // Do this 3 times to remove the extra slots
        // for (int i = 0; i < 3; i++) {
        //     client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[8]/td/div[11]/input")).click();
        //     wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));
        // }

        // n. Click Save
        client.findElement(By.cssSelector("tr:nth-child(20)>td>.sf_admin_actions>li:nth-child(1)>input")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    /**
     * Create a Background Takeover Weekender Article
     */
    public void createArticleWeekenderBGTakeOver() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_adminHelper_client = new UD_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);


        // Begin Weekender Specific methods

        // Choose ad campaign
        dropDownSelector(ud_adminHelper_client.getAdCampaignDropdown(),"Groupon");

        // Choose any weekender category
        dropDownSelector("article_edition_category_id","NYC: The Weekender");

        //  Enter an Article SubHeader
        ud_adminHelper_client.getArticleSubHeaderBox().sendKeys("Test Weekender Article SubHeader "+ date);

        // 18. Enter text in photo credit
        ud_adminHelper_client.getArticlePhotoCreditBox().sendKeys("Weekender Photo Credits Test "+ date);

        // 19. Enter text in Article Feature
        ud_adminHelper_client.getArticleFeatureBox().sendKeys("Weekender Article/Feature Introduction Test "+ date);

        // 20. Copy is not needed
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('article[content]').SetHTML('Weekender Article Copy Test'))");

        // Save Weekender Specific methods
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // Get Article ID
        String articleID = getArticleID();

        // 17. Add Images: Email Banner, Option_A_Left_Column, and Thumbnail

        // go to create image page
        client.get(UD_ADMIN_DOMAIN+"/article_images/create");

        // browse to 1st image
        adArticleImages(articleID,"image001_optionA.jpg","Option_A_Left_Column");

        // browse to 2nd image
        adArticleImages(articleID,"image002_EmailBanner.jpg","Email_Banner");

        // browse to 3rd image
        adArticleImages(articleID,"image003_thumbnail.jpg","Thumbnail");

        // go back to the article
        client.get(UD_ADMIN_DOMAIN+"/articles/edit/id/"+articleID);

        // Save the page
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // 32. Click “here” next to Template this takes you to individual weekenders
        //html/body/div[3]/div/div[2]/form/fieldset/div[5]/div/a

        // or:
        client.get(UD_ADMIN_DOMAIN+"/multiarticle/edit/id/"+articleID);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#sf_admin_edit_form>fieldset>table>tbody>tr>td>div")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td[2]/div[1]/iframe")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[10]/td[2]/div[2]/iframe")));

        // 33. For each template, 3 is a good number:

        // Day 1
        Reporter.log("Entering info for Day 1");
        // a. Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(1),"Monday");

        // b. Click show day image in title (not needed, will duplicate the day name)
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[2]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(1).sendKeys("Weekender Header1 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(1).sendKeys("Weekender SubHeader1 Test "+ date);

        // e. Put in some url for subheader URL
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(1).sendKeys("www.google.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(1).sendKeys(IMAGE_PATH + "solon.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(1).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(1).sendKeys("http://images.sodahead.com/polls/001076173/even_kittens_are_going_bad_answer_2_xlarge.jpeg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(1).sendKeys("Weekender Alt Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411
        // l. Repeat for others

        // Day 2
        Reporter.log("Entering info for Day 2");
        // a. Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(2),"Tuesday");

        // b. Click show day image in title
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[4]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(2).sendKeys("Weekender Header2 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(2).sendKeys("Weekender SubHeader2 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(2).sendKeys("www.yahoo.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(2).sendKeys(IMAGE_PATH + "plato.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(2).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(2).sendKeys("http://t0.gstatic.com/images?q=tbn:ANd9GcTYj5WyrHaLj6lqad-dIiNUTQSaKkuJmJtUKiPX3SbIpCfS-1aFqyr-mDWF");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(2).sendKeys("Weekender Alt2 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411
        // l. Repeat for others

        // Day 3
        Reporter.log("Entering info for Day 3");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(3),"Wednesday");

        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(3).sendKeys("Weekender Header3 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(3).sendKeys("Weekender SubHeader3 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(3).sendKeys("www.cnn.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(3).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(3).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(3).sendKeys("http://images.sodahead.com/polls/001076173/even_kittens_are_going_bad_answer_2_xlarge.jpeg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(3).sendKeys("Weekender Alt3 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        // Day 4
        Reporter.log("Entering info for Day 4");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(4),"Thursday");

        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(4).sendKeys("Weekender Header4 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(4).sendKeys("Weekender SubHeader4 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(4).sendKeys("www.geno.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(4).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(4).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(4).sendKeys("http://imagecache6.allposters.com/LRG/38/3842/UJXYF00Z.jpg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(4).sendKeys("Weekender Alt4 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        // Day 5
        Reporter.log("Entering info for Day 5");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(5),"Friday");

        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(5).sendKeys("Weekender Header5 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(5).sendKeys("Weekender SubHeader5 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(5).sendKeys("perks.geno.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(5).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(5).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(5).sendKeys("http://imagecache6.allposters.com/LRG/38/3842/UJXYF00Z.jpg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(5).sendKeys("Weekender Alt5 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        // Day 6
        Reporter.log("Entering info for Day 6");
        // a.Choose the day
        dropDownSelector(ud_adminHelper_client.getMultiArticleDayDropdown(6),"Saturday");

        // b. Click show day image in title (Not needed, will double the Date text
        //client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[6]/td/div/input[2]")).click();

        // c. Enter text in Header
        ud_adminHelper_client.getMultiArticleHeaderBox(6).sendKeys("Weekender Header6 Test "+ date);

        // d. Enter text in Subheader
        ud_adminHelper_client.getMultiArticleSubHeaderBox(6).sendKeys("Weekender SubHeader6 Test "+ date);

        // e. Put in some url for subheader URl
        ud_adminHelper_client.getMultiArticleSubheaderURLBox(6).sendKeys("www.driven.com");

        // f. Click choose file under image and put in an image
        ud_adminHelper_client.getMultiArticleImageFileBox(6).sendKeys(IMAGE_PATH + "socrates.jpg");

        // g. Check image url
        ud_adminHelper_client.getMultiArticleImageURLCheckBox(6).click();

        // h. Put in some url for image url
        ud_adminHelper_client.getMultiArticleImageURLBox(6).sendKeys("http://imagecache6.allposters.com/LRG/38/3842/UJXYF00Z.jpg");

        // i. Enter text in Alt
        ud_adminHelper_client.getMultiArticleAltTextBox(6).sendKeys("Weekender Alt6 Test"+ date);

        // j. Enter text in Copy
        // k. Enter text in 411

        //**NOTE Until deleting slots is fixed comment this out

        // m. Delete slots you do not want to use
        // Do this 3 times to remove the extra slots
        // for (int i = 0; i < 3; i++) {
        //     client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/table/tbody/tr[8]/td/div[11]/input")).click();
        //     wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));
        // }

        // n. Click Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    /**
     * Create a Three Column Article
     */
    public void createArticleThreeColumn() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_adminHelper_client = new UD_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);


        // Begin Three-Column specific
        // Enter Article SubHeader
        ud_adminHelper_client.getArticleSubHeaderBox().sendKeys("Test Three-Column Article Subheader "+ date);

        // enter photo credits
        ud_adminHelper_client.getArticlePhotoCreditBox().sendKeys("Three-Column Photo Credits Test "+ date);

        // enter Article/Feature introduction
        ud_adminHelper_client.getArticleFeatureBox().sendKeys("Three-Column Article/Feature Introduction Test "+ date);

        // enter Copy
        ((RemoteWebDriver) client).executeScript(
            "var xmlHttp = null;" +
            "xmlHttp = new XMLHttpRequest();" +
            "xmlHttp.open( \"GET\", \"http://hipsterjesus.com/api/?paras=2&type=hipster-latin&html=false\", false );" +
            "xmlHttp.send( null );" +
            "var a = eval(\"(\" + xmlHttp.responseText + \")\" ).text.toString();" +
            "(FCKeditorAPI.GetInstance('article[content]').SetHTML('Three-Column Article Copy Test \\n' + a))");


        // Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // Get Article ID
        String articleID = getArticleID();

        // Add images to Article

        // go to create image page
        client.get(UD_ADMIN_DOMAIN+"/article_images/create");

        // browse to 1st image
        adArticleImages(articleID,"image001_optionA.jpg","Option_A_Left_Column");

        // browse to 2nd image
        adArticleImages(articleID,"image002_EmailBanner.jpg","Email_Banner");

        // browse to 3rd image
        adArticleImages(articleID,"image003_thumbnail.jpg","Thumbnail");

        // ad article header
        adArticleImages(articleID, "Email_Large_Horizontal.jpg","Email_Large_Horizontal");

        // go back to the article
        client.get(UD_ADMIN_DOMAIN + "/articles/edit/id/" + articleID);

        /*
        This section is no longer needed now that vitals & tools are there by default
        // Add Vitals to the Left Module
        // moduleAdPlacement(3,"Vitals");

        // Add Tools to the left Module,
        // moduleAdPlacement(3,"Tools");
        */
        // Add Sponsored Love to the Left Module,
        moduleAdPlacement(3,"Sponsored Love");

        String sponsoredLoveEditLeft = getAdPlacement(3, "newsletter_sponsoredlove");
        client.get(sponsoredLoveEditLeft);

        adminNewsLetterAdEdit("11111");

        // Add an Ad to the Center/Bottom Module
        moduleAdPlacement(4,"Ad");

        // b. Click on newsletter_ad
        String bottomUrl = getAdPlacement(4, "newsletter_ad");
        client.get(bottomUrl);

        adminNewsLetterAdEdit("Absolut Glimmer ATL 12-27-11 footer");

        // Add an Ad to the Right Module
        moduleAdPlacement(5,"Ad");

        // click on "Ad"
        String rightAdEdit = getAdPlacement(5, "newsletter_ad");
        client.get(rightAdEdit);
        adminNewsLetterAdEdit("Abbadabba's ATL 11-15-11 A tower");

        // Ad Sponsored Love to the Right Module (Zak request)
        moduleAdPlacement(5,"Sponsored Love");

        String sponsoredLoveEditRight = getAdPlacement(5, "newsletter_sponsoredlove");
        client.get(sponsoredLoveEditRight);
        adminNewsLetterAdEdit("11111");

        // Click Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        doTestMailing(articleID);
    }

    /**
     * Check that article elements exist
     * @param articleId
     */
    public void checkThreeColumnArticleStep1(String articleId) {
        WebDriver client = getDriver();
        client.get(UD_DOMAIN+"/newsletter/article/" + articleId +"?preview=true");

        SoftAssert m_assert = new SoftAssert();

        //onlineversiontext
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#onlineversion")).size() != 0,"Online Version text Missing");

        //toplogo
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#udlogo>a>img")).size() != 0,"Top logo is missing");

        //myUd
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#myud>a>img")).size() != 0,"My Ud seal is missing");

        //facebooklike
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#facebook>a>img")).size() != 0,"Large FB list button missing");

        //edition
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#edition>a")).size() != 0,"Edition text is missing");

        //EmailHeaderImage
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc #banner .emailwrapto100pc")).size() != 0,"Email Header image is missing");

        //SponsoredLove above date
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailspacing>a")).size() != 0,"Sponsored Love text is missing");

        //ArticleTitle
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".articletitle")).size() != 0,"Article Title missing");

        //ArticleSubtitle
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".articletitle>span")).size() != 0,"Article Subtitle missing");

        //ArticleHeaderImage
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc .emailwrapto100pc .emailspacing .emailwrapto100pc")).size() != 0,"Article header image missing");

        //Article
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".article")).size() != 0,"Article Body is missing");

        //ShareToolsLeft
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailnomob .sharetools")).size() != 0,"Left placement share tools is missing");

        //ShareToolsFooter
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailcolsplit>#article .sharetools")).size() != 0,"Footer placement share tools is missing");

        //YourPerkAwaits
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".action-item>tbody>tr>td>a>img")).size() != 0,"Your perk awaits feature is missing");

        //SponsoredLoveLeft
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc .emailcolsplit .emailwrapto100pc .sponsoredlove")).size() != 0,"Left Placement Sponsored Love missing");

        //SponsoredLoveRight
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc .emailcolsplit div:nth-child(3) .sponsoredlove")).size() != 0,"Right placement Sponsored Love missing");

        //NoteText
        m_assert.assertEquals(true,client.findElements(By.cssSelector(".notetext")).size() != 0,"Note text is missing");

        //FooterAd
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#footerad")).size() != 0,"Footer Ad is missing");

        //RightAd
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#rightad")).size() != 0,"Right Ad is missing");

        //FooterLinks
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#footerlinks")).size() != 0,"Footer links are missing");

        //PhotoCreditText
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#photocredit>div")).size() != 0,"Photo credit text is missing");

        //FooterAddress
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#footeraddress>div")).size() != 0,"Footer address is missing");

        //WhatIsSponsoredLoveLink
        m_assert.assertEquals(true,client.findElements(By.cssSelector("#footerlogo>div>a")).size() != 0,"What is Sponsored Love text is missing");

        m_assert.assertAll();
    }

    /**
     * Perform extra checks on elements
     * @param articleId
     */
    public void checkThreeColumnArticleStep2(String articleId) {
        WebDriver client = getDriver();
        SoftAssert m_assert = new SoftAssert();

        //onlineversiontext
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#onlineversion")).size() != 0,"Online Version text Missing");
        m_assert.assertEquals(true,client.findElement(By.cssSelector("#onlineversion")).getText().contains("online version"), "Text doesn't contain 'online version'");
        m_assert.assertEquals(true,client.findElement(By.cssSelector("#onlineversion")).findElements(By.tagName("a")).size() != 0, "Online version link is missing");

        //toplogo
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#udlogo>a>img")).size() != 0,"Top logo is missing");
        m_assert.assertEquals(true,client.findElement(By.cssSelector("#udlogo>a>img")).getAttribute("alt").equals("geno"),"Top Logo Alt text is missing or does not equal 'geno'");

        //myUd
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#myud>a>img")).size() != 0,"My Ud seal is missing");
        m_assert.assertEquals(true,client.findElement(By.cssSelector("#myud>a>img")).getAttribute("alt").equals("My UD"),"My Ud seal alt text is missing or does not equal 'MyUD'");

        //facebooklike
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#facebook>a>img")).size() != 0,"Large FB list button missing");
        m_assert.assertEquals(true,client.findElement(By.cssSelector("#facebook>a>img")).getAttribute("alt").equals("Like Us On Facebook"),"FB List button alt text missing or is incorrect");

        //edition
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#edition>a")).size() != 0,"Edition text is missing");

        //TODO: Figure out how to get this to be dynamic -- Check for 'is dedicated' somehow
        //m_assert.assertEquals(true,client.findElement(By.cssSelector("#edition>a")).getText().trim().contains("Sponsored Love"),"Edition text is missing");
        //m_assert.assertEquals(true,client.findElement(By.cssSelector("#edition>a")).getText().trim().contains("New York"),"Edition text is missing");

        //EmailHeaderImage
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc #banner .emailwrapto100pc")).size() != 0,"Email Header image is missing");
        // TODO: Alt text is based off of Category drop down in admin. Would say 'UD - Nightlife' if NYC: Nightlife is selected

        //SponsoredLove above date
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailspacing>a")).size() != 0,"Sponsored Love text is missing");

        //ArticleTitle
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".articletitle")).size() != 0,"Article Title missing");

        //ArticleSubtitle
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".articletitle>span")).size() != 0,"Article Subtitle missing");

        //ArticleHeaderImage
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc .emailwrapto100pc .emailspacing .emailwrapto100pc")).size() != 0,"Article header image missing");

        //Article
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".article")).size() != 0,"Article Body is missing");

        //ShareToolsLeft
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailnomob .sharetools")).size() != 0,"Left placement share tools is missing");

        //ShareToolsFooter
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailcolsplit>#article .sharetools")).size() != 0,"Footer placement share tools is missing");

        //YourPerkAwaits
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".action-item>tbody>tr>td>a>img")).size() != 0,"Your perk awaits feature is missing");

        //SponsoredLoveLeft
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc .emailcolsplit .emailwrapto100pc .sponsoredlove")).size() != 0,"Left Placement Sponsored Love missing");

        //SponsoredLoveRight
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".emailwrapto100pc .emailcolsplit div:nth-child(3) .sponsoredlove")).size() != 0,"Right placement Sponsored Love missing");

        //NoteText
        //m_assert.assertEquals(true,client.findElements(By.cssSelector(".notetext")).size() != 0,"Note text is missing");

        //FooterAd
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#footerad")).size() != 0,"Footer Ad is missing");

        //RightAd
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#rightad")).size() != 0,"Right Ad is missing");

        //FooterLinks
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#footerlinks")).size() != 0,"Footer links are missing");

        //PhotoCreditText
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#photocredit>div")).size() != 0,"Photo credit text is missing");

        //FooterAddress
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#footeraddress>div")).size() != 0,"Footer address is missing");

        //WhatIsSponsoredLoveLink
        //m_assert.assertEquals(true,client.findElements(By.cssSelector("#footerlogo>div>a")).size() != 0,"What is Sponsored Love text is missing");

        m_assert.assertAll();
    }
    /**
     * Create a Background Takeover Three Column Article
     */
    public void createArticleThreeColumnBackgrounder() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_adminHelper_client = new UD_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);


        // Begin Three-Column specific
        // Enter Article SubHeader
        ud_adminHelper_client.getArticleSubHeaderBox().sendKeys("Test Three-Column Article Subheader "+ date);

        // enter photo credits
        ud_adminHelper_client.getArticlePhotoCreditBox().sendKeys("Three-Column Photo Credits Test "+ date);

        // enter Article/Feature introduction
        ud_adminHelper_client.getArticleFeatureBox().sendKeys("Three-Column Article/Feature Introduction Test "+ date);

        // enter Copy
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('article[content]').SetHTML('Three-Column Article Copy Test'))");

        // Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        // Get Article ID
        String articleID = getArticleID();

        // Add images to Article
        // go to create image page
        client.get(UD_ADMIN_DOMAIN+"/article_images/create");

        // browse to 1st image
        adArticleImages(articleID,"image001_optionA.jpg","Option_A_Left_Column");

        // browse to 2nd image
        adArticleImages(articleID,"image002_EmailBanner.jpg","Email_Banner");

        // browse to 3rd image
        adArticleImages(articleID,"image003_thumbnail.jpg","Thumbnail");

        // go back to the article
        client.get(UD_ADMIN_DOMAIN + "/articles/edit/id/" + articleID);


        // Add Vitals to the Left Module
        moduleAdPlacement(3,"Vitals");

        // Add Tools to the Bottom Module,
        moduleAdPlacement(4,"Tools");

        // Add Sponsored Love to the Bottom Module,
        moduleAdPlacement(4,"Sponsored Love");

        String sponsoredLoveEdit = getAdPlacement(4, "newsletter_sponsoredlove");
        client.get(sponsoredLoveEdit);

        adminNewsLetterAdEdit("11111");

        // Add an Ad to the Center/Bottom Module
        moduleAdPlacement(4,"Ad");

        // b. Click on newsletter_ad
        String bottomUrl = getAdPlacement(4, "newsletter_ad");
        client.get(bottomUrl);

        adminNewsLetterAdEdit("Absolut Glimmer ATL 12-27-11 footer");

        // Click Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        doTestMailing(articleID);
    }

    /**
     * Create a PMT
     */
    public void createPMT(String memberSource){
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        //1. Go to Campaign Templates under Partnerships
        //2. Click on Create

        client.get(UD_ADMIN_DOMAIN+"/pmt_universal_settings/create");
        // Make sure the page loaded
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_edit_form']/ul/li[2]/input",10);

        //3. Enter Name
        String campaignName ="PMT "+  date;
        client.findElement(By.id("pmt_universal_settings_name")).sendKeys(campaignName);

        //4. Choose Campaign end date some day in the future
        client.findElement(By.id("pmt_universal_settings_campaign_end_date")).sendKeys("2013-08-23 17:39");

        //5. Uncheck Campaign Disabled
        client.findElement(By.id("pmt_universal_settings_campaign_disabled")).click();

        //Select Member source created earlier
        dropDownSelector("pmt_universal_settings_member_source_id",memberSource);

        //6. Check User picks editions
        client.findElement(By.id("pmt_universal_settings_user_picks_editions")).click();

        //7. Check User invites friends
        client.findElement(By.id("pmt_universal_settings_user_invites_friends")).click();

        //8. Choose a background image, must be 1280 by 568
        String PMTBackground = IMAGE_PATH + "background image.jpg";
        System.out.println(PMTBackground);
        client.findElement(By.id("pmt_universal_settings_background_image_path")).sendKeys(PMTBackground);

        //9. Choose a logo, must be 250 by 100
        //selected by Default

        //10. Choose a new Accent color hex, ex: 00FFFF
        client.findElement(By.id("pmt_universal_settings_accent_color_hex")).clear();
        client.findElement(By.id("pmt_universal_settings_accent_color_hex")).sendKeys("00FFFF");

        //11. Enter footer text
        // figure out how to write to wysiwyg editor now!!!
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_universal_settings[footer_text]').SetHTML('Footer Text Test'))");

        //12. Link to a pdf for Rules and regs
        //Web Browser needs adobe installed so it can confirm this is a PDF
        String PMTPdf = IMAGE_PATH + "rules_regulations.pdf";
        System.out.println(PMTPdf);
        client.findElement(By.id("pmt_universal_settings_rules_and_regulations_path")).sendKeys(PMTPdf);

        //13. Click SAVE
        //???     ↓ Only PDF files are allowed  ↓ for Rules and Regulations field???
        client.findElement(By.name("save")).click();

        // Make sure Success message displays
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_content']/div/h2",10);
        client.findElement(By.name("save_and_add")).click();

        //19. Click Add Partner
        // Partner 1
        WebElement addPartner = findElementAndCheckBy("xpath","//html/body/div[3]/div/div[3]/ul/li[2]/input",10);
        addPartner.click();

        //20. Enter partner name
        client.findElement(By.id("pmt_partner_settings_name")).sendKeys("Partner1 "+  date);

        //21. Enter opt in text
        client.findElement(By.id("pmt_partner_settings_opt_in_text")).sendKeys("Opt in text1 "+  date);

        //22. Enter privacy policy url
        client.findElement(By.id("pmt_partner_settings_privacy_policy_url")).sendKeys(UD_DOMAIN + "/privacypolicy");

        //23. Enter text in privacy policy label
        client.findElement(By.id("pmt_partner_settings_privacy_policy_label")).sendKeys("Privacy policy label1 "+  date);

        //24. Choose logo, must be 170 by 97
        client.findElement(By.id("pmt_partner_settings_logo_path")).sendKeys(IMAGE_PATH + "partner logo-1.png");

        //25. Click Save
        client.findElement(By.name("save")).click();
        findElementAndCheckBy("xpath","/html/body/div[3]/div/div[2]/div/h2",10);


        //26. Click List
        client.findElement(By.xpath(".//*[@id='sf_admin_edit_form']/ul/li[1]/input")).click();

        //27. Repeat Steps 19-26 for 2nd partner slot

        //19. Click Add Partner
        // Partner 2
        WebElement addPartner2 = findElementAndCheckBy("xpath","//html/body/div[3]/div/div[3]/ul/li[2]/input",10);
        addPartner2.click();

        //20. Enter partner name
        client.findElement(By.id("pmt_partner_settings_name")).sendKeys("Partner2 "+  date);

        //21. Enter opt in text
        client.findElement(By.id("pmt_partner_settings_opt_in_text")).sendKeys("Opt in text2 "+  date);

        //22. Enter privacy policy url
        client.findElement(By.id("pmt_partner_settings_privacy_policy_url")).sendKeys(UD_DOMAIN +"/privacypolicy");

        //23. Enter text in privacy policy label
        client.findElement(By.id("pmt_partner_settings_privacy_policy_label")).sendKeys("Privacy policy label2 "+  date);

        //24. Choose logo, must be 170 by 97
        client.findElement(By.id("pmt_partner_settings_logo_path")).sendKeys(IMAGE_PATH + "partner logo-2.png");

        //25. Click Save
        client.findElement(By.name("save")).click();
        findElementAndCheckBy("xpath","/html/body/div[3]/div/div[2]/div/h2",10);

        //26. Click List
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_edit_form']/ul/li[1]/input",10).click();

        //28. Click Next Step
        findElementAndCheckBy("xpath","//html/body/div[3]/div/div[3]/ul/li[4]/input",10).click();

        //29. Add Header text
        // Make sure we're on the next page
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_container']/h1",10);
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_signup_settings[header1_text]').SetHTML('Signup Header Text Test'))");

        //30. Add Subhead text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_signup_settings[header2_text]').SetHTML('Signup Subheader Text Test'))");

        //31. Add Bottom text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_signup_settings[bottom_text]').SetHTML('Signup Bottom Text Test'))");

        //32. Click next step
        client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/ul/li[4]/input")).click();
        // Wait for success message
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_content']/div/h2",10);

        //33. Select all editions
        //find editions selector
        Select EditionsMulitpleSelection = new Select(client.findElement(By.id("unassociated_preselected_editions")));

        //select all editions
        EditionsMulitpleSelection.selectByVisibleText("National");
        EditionsMulitpleSelection.selectByVisibleText("National Perks");
        EditionsMulitpleSelection.selectByVisibleText("Atlanta");
        EditionsMulitpleSelection.selectByVisibleText("Boston");
        EditionsMulitpleSelection.selectByVisibleText("Boston Perks");
        EditionsMulitpleSelection.selectByVisibleText("Chicago");
        EditionsMulitpleSelection.selectByVisibleText("Chicago Perks");
        EditionsMulitpleSelection.selectByVisibleText("Dallas");
        EditionsMulitpleSelection.selectByVisibleText("DC");
        EditionsMulitpleSelection.selectByVisibleText("DC Perks");
        EditionsMulitpleSelection.selectByVisibleText("Los Angeles");
        EditionsMulitpleSelection.selectByVisibleText("Los Angeles Perks");
        EditionsMulitpleSelection.selectByVisibleText("Miami");
        EditionsMulitpleSelection.selectByVisibleText("Miami Perks");
        EditionsMulitpleSelection.selectByVisibleText("New York");
        EditionsMulitpleSelection.selectByVisibleText("New York Perks");
        EditionsMulitpleSelection.selectByVisibleText("Philly");
        EditionsMulitpleSelection.selectByVisibleText("San Francisco");
        EditionsMulitpleSelection.selectByVisibleText("Seattle");
        EditionsMulitpleSelection.selectByVisibleText("Driven");
        EditionsMulitpleSelection.selectByVisibleText("Jetset");
        EditionsMulitpleSelection.selectByVisibleText("Las Vegas");
        EditionsMulitpleSelection.selectByVisibleText("Ski & Board");

        //34. Click Right blue arrow to move them to the Selected column
        client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/fieldset/div[2]/div/div/div[2]/input")).click();

        //35. Add Header text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_edition_settings[header1_text]').SetHTML('Editions Header Text Test'))");

        //36. Add subheader text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_edition_settings[header2_text]').SetHTML('Editions Subheader Text Test'))");

        //37. Add bottom text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_edition_settings[bottom_text]').SetHTML('Editions Bottom Text Test'))");

        //38. Click Next Step
        client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/ul/li[4]/input")).click();
        // Wait for success message
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_content']/div/h2",10);


        //39. Add Invite Friends Head text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_invite_friends_settings[header1_text]').SetHTML('Invite Friends Header Text Test'))");

        //40. Add Invite Friends Subhead text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_invite_friends_settings[header2_text]').SetHTML('Invite Friends Subheader Text Test'))");

        //41. Add Invite Friends bottom text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_invite_friends_settings[bottom_text]').SetHTML('Invite Friends Bottom Text Test'))");

        //42. Click Next Step
        client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/ul/li[4]/input")).click();
        // Wait for success message
        findElementAndCheckBy("xpath","/html/body/div[3]/div/div[2]/div/h2",10);

        //43. Add thank you head text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_thank_you_settings[header1_text]').SetHTML('Thank You Header Text Test'))");

        //44. Add thank you sub text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_thank_you_settings[header2_text]').SetHTML('Thank You Subheader Text Test'))");

        //45. Add thank you bot text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_thank_you_settings[bottom_text]').SetHTML('Thank You Bottom Text Test'))");

        //46. Add twitter copy
        client.findElement(By.id("pmt_thank_you_settings_twitter_copy")).sendKeys("Twitter copy "+  date);

        //47. Add FB title
        client.findElement(By.id("pmt_thank_you_settings_facebook_title")).sendKeys("FB Title copy "+  date);

        //48. Add FB Copy
        client.findElement(By.id("pmt_thank_you_settings_facebook_copy")).sendKeys("FB copy "+  date);

        //49. Choose FB image, must be 50 X 50
        client.findElement(By.id("pmt_thank_you_settings_facebook_image_path")).sendKeys(IMAGE_PATH + "fb-image-test.jpg");

        //50. Choose Module 1 Image
        client.findElement(By.id("pmt_thank_you_settings_module1_image_path")).sendKeys(IMAGE_PATH + "Thank You-Campaign Closed Page Image Module-driven.jpg");

        //51. Add Module 1 URL
        client.findElement(By.id("pmt_thank_you_settings_module1_url")).sendKeys("http://www.ThankYouSettingsModule1url.com");

        //52. Choose Module 2 image
        client.findElement(By.id("pmt_thank_you_settings_module2_image_path")).sendKeys(IMAGE_PATH + "Thank You-Campaign Closed Page Image Module-jetset.jpg");

        //53. Add module 2 url
        client.findElement(By.id("pmt_thank_you_settings_module2_url")).sendKeys("http://www.ThankYouSettingsModule2url.com");

        //54. Choose Module 3 image
        client.findElement(By.id("pmt_thank_you_settings_module3_image_path")).sendKeys(IMAGE_PATH + "Thank You-Campaign Closed Page Image Module-perks.jpg");

        //55. Add module 3 url
        client.findElement(By.id("pmt_thank_you_settings_module3_url")).sendKeys("http://www.ThankYouSettingsModule3url.com");

        //56. Click Next Step
        client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/ul/li[4]/input")).click();
        //Check for success message
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_content']/div/h2",10);

        //57. Repeat Steps for the Closed page
        //Add Closed head text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_closed_settings[header1_text]').SetHTML('Closed Page Header Text Test'))");

        //Add thank you sub text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_closed_settings[header2_text]').SetHTML('Closed Page Subheader Text Test'))");

        //Add thank you bot text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_closed_settings[bottom_text]').SetHTML('Closed Page Bottom Text Test'))");

        // Choose Module 1 Image
        client.findElement(By.id("pmt_closed_settings_module1_image_path")).sendKeys(IMAGE_PATH + "Thank You-Campaign Closed Page Image Module-driven.jpg");

        //51. Add Module 1 URL
        client.findElement(By.id("pmt_closed_settings_module1_url")).sendKeys("http://www.ClosedSettingsModule1url.com");

        //52. Choose Module 2 image
        client.findElement(By.id("pmt_closed_settings_module2_image_path")).sendKeys(IMAGE_PATH + "Thank You-Campaign Closed Page Image Module-jetset.jpg");

        //53. Add module 2 url
        client.findElement(By.id("pmt_closed_settings_module2_url")).sendKeys("http://www.ClosedSettingsModule2url.com");

        //54. Choose Module 3 image
        client.findElement(By.id("pmt_closed_settings_module3_image_path")).sendKeys(IMAGE_PATH + "Thank You-Campaign Closed Page Image Module-perks.jpg");

        //55. Add module 3 url
        client.findElement(By.id("pmt_closed_settings_module3_url")).sendKeys("http://www.ClosedSettingsModule3url.com");

        //56. Click Next Step
        client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/ul/li[4]/input")).click();
        // Check for success message
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_content']/div/h2",10);

        //58. Invite Email Settings
        //Choose Invite Email Header Image
        client.findElement(By.id("pmt_invite_email_settings_header_image_path")).sendKeys(IMAGE_PATH + "email-header Image.jpg");

        //Subject Text
        client.findElement(By.id("pmt_invite_email_settings_subject_text")).sendKeys("Email subject text "+  date);

        //Email Body Text
        ((RemoteWebDriver) client).executeScript("(FCKeditorAPI.GetInstance('pmt_invite_email_settings[body_text]').SetHTML('Invite Email Text Test'))");

        // Click Next Step
        client.findElement(By.xpath("//html/body/div[3]/div/div[2]/form/ul/li[4]/input")).click();
        //Check for success message
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_content']/div/h2",10);

        //59. Go to newly created campaign
        //Back to PMT Universal Settings List
        client.get(UD_ADMIN_DOMAIN+"/pmt_universal_settings");

        // Make sure the page shows up
        findElementAndCheckBy("xpath",".//*[@id='sf_admin_container']/h1",10);

        // Get the campaign just created
        System.out.println(campaignName);
        WebElement campaignLink = client.findElement(By.linkText(campaignName));
        client.get(campaignLink.getAttribute("href"));

        //60. Check for elements
        //a. Campaign Name = Title of the Page
        //Assert.assertTrue(client.getTitle().equalsIgnoreCase(campaignName));

        //b. Signup Settings Header Text
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/form/section/h1")).getText().contains("Signup Header Text Test"));

        //c. Signup Settings Subheader Text
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/form/section/h2")).getText().contains("Signup Subheader Text Test"));

        //d. Signup Settings Bottom Text
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/form/section/p")).getText().contains("Signup Bottom Text Test"));

        //e. Optin Text1
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/form/section[2]/ul/li[2]/label")).getText().contains("Opt in text1 " +  date));

        //f. Optin Text2
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/form/section[2]/ul/li[3]/label")).getText().contains("Opt in text2 " +  date));

        //g. Footer Text Test
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/p")).getText().contains("Footer Text Test"));

        //h. Privacy Policy label1
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[3]/a")).getText().contains("Privacy policy label1 " +  date));

        //h. Privacy Policy label2
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[4]/a")).getText().contains("Privacy policy label2 " +  date));

        //61. Enter Email

//                client.findElement(By.xpath("//html/body/section/form/section/fieldset/input")).sendKeys(emailClient);

        //62. Enter Zip

//                client.findElement(By.xpath("//html/body/section/form/section/fieldset/input[2]")).sendKeys("10003");

        // leave all check boxes that were checked by default onload checked

        //63. Click Enter Button

//                client.findElement(By.name("commit")).click();
        //64. On the Editions Page
        //do checks

        // Edition Settings Bottom Text Test

//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/form/section[2]/p")).getText().contains("Editions Bottom Text Test"));

        //g. Footer Text Test
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/p")).getText().contains("Footer Text Test"));

        //h. Privacy Policy label1
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[3]/a")).getText().contains("Privacy policy label1 " +  date));

        //h. Privacy Policy label2
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[4]/a")).getText().contains("Privacy policy label2 " +  date));

        //65. Click Enter Button

//                client.findElement(By.name("commit")).click();

        //66. On Invite Friends Page
        // do checks

        // Invite Friends Settings Bottom Text Test

//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/form/section[2]/p")).getText().contains("Invite Friends Bottom Text Test"));

        //g. Footer Text Test
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/p")).getText().contains("Footer Text Test"));

        //h. Privacy Policy label1
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[3]/a")).getText().contains("Privacy policy label1 " +  date));

        //h. Privacy Policy label2
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[4]/a")).getText().contains("Privacy policy label2 " +  date));

        //67. click "More" link thrice
//                client.findElement(By.xpath("//html/body/section/form/section/fieldset/div[2]/ul/li[4]/a")).click();
//                client.findElement(By.xpath("//html/body/section/form/section/fieldset/div[2]/ul/li[4]/a")).click();
//                client.findElement(By.xpath("//html/body/section/form/section/fieldset/div[2]/ul/li[4]/a")).click();

        //68. enter all the fields
        // Your name
//                client.findElement(By.id("from_name")).sendKeys("UD Tester");

        // Friend 1

//                client.findElement(By.id("friends")).sendKeys(emailFriend1);

        // Friend 2

//                client.findElement(By.xpath("(//input[@id='friends'])[2]")).sendKeys(emailFriend3);

        // Friend 3

//                client.findElement(By.xpath("(//input[@id='friends'])[3]")).sendKeys(emailFriend3);

        // Friend 4

//                client.findElement(By.xpath("(//input[@id='friends'])[4]")).sendKeys(emailFriend4);

        // Friend 5

//                client.findElement(By.xpath("(//input[@id='friends'])[5]")).sendKeys(emailFriend5);

        // Friend 6

//                client.findElement(By.xpath("(//input[@id='friends'])[6]")).sendKeys(emailFriend6);


        //67. Click Invite
//                client.findElement(By.name("commit")).click();
        //68. On Thank you page
        //do checks

        // Thank You Settings Bottom Text Test

//                Assert.assertTrue(client.findElement(By.xpath("//html/body/section/section[2]/p")).getText().contains("Thank You Bottom Text Test"));

        //g. Footer Text Test
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/p")).getText().contains("Footer Text Test"));

        //h. Privacy Policy label1
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[3]/a")).getText().contains("Privacy policy label1 " +  date));

        //h. Privacy Policy label2
//                Assert.assertTrue(client.findElement(By.xpath("//html/body/footer/nav/ul/li[4]/a")).getText().contains("Privacy policy label2 " +  date));

    }

// Admin Helper Methods
    /**
     * Get the article ID
     * ex. http://ud-release.thedaddy.co/admin.php/articles/edit/id/19468
     * Will return 19468
     * @return Article ID found at the end of the admin url
     */
    public String getArticleID() {
        WebDriver client = getDriver();

        String articleLink = client.getCurrentUrl();

        String[] separated = articleLink.split("/");
        return separated[separated.length - 1];

    }

    /**
     * When creating a Background Takeover this method is required
     * @param color Select 0 for Black, 1 for White
     * @throws IllegalArgumentException if 0 or 1 isn't used
     */
    public void backgroundTakeoverSettings(int color) throws IllegalArgumentException {
        WebDriver client = getDriver();

        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        final String bgColor;
        final String url;

        // Check the correct int was passed
        if (color == 1) {
            bgColor = "#FFFFFF";
        } else if (color == 0) {
            bgColor = "#000000";
        } else {
            throw new IllegalArgumentException("Color must be 0(black) or 1(white)");
        }

        // Grab the url before going to the background takeover page
        url = client.getCurrentUrl();

        // Click on the link next to the template type dropdown

        if (client.findElements(By.cssSelector("#sf_fieldset_design>.form-row>.content>a")).size() > 1) {
            // Weekenders have two links next to the template dropdown
            client.findElement(By.cssSelector("#sf_fieldset_design>.form-row>.content>a:nth-child(3)")).click();
        } else {
            client.findElement(By.cssSelector("#sf_fieldset_design>.form-row>.content>a")).click();
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("background_takeover_ad_campaign_id")));

        // Select Ad campaign
        Select adCampaign = new Select(ud_adminHelper_client.getBGCampaignDropDown());
        adCampaign.selectByVisibleText("Playstation VITA Q1 2012");

        // Select Background Color #FFFFFF - White #000000 - Black
        ud_adminHelper_client.getBGTColorTextBox().sendKeys(bgColor);

        // Select Left Image
        ud_adminHelper_client.getBGLeftImageTxtBox().sendKeys(IMAGE_PATH + "bg_takeover_left.jpg");
        // Set Image Width & Height
        ud_adminHelper_client.getBGLeftImgWidthTextBox().sendKeys("0");
        ud_adminHelper_client.getBGLeftImgHeightTextBox().sendKeys("0");
        // Set Left Image Alt Text
        ud_adminHelper_client.getBGLeftAltTextBox().sendKeys("ALT TEST Click Here");

        // Select Right Image
        ud_adminHelper_client.getBGRightImageTxtBox().sendKeys(IMAGE_PATH + "bg_takeover_right.jpg");
        // Set Image Width & Height
        ud_adminHelper_client.getBGRightImgWidthTextBox().sendKeys("0");
        ud_adminHelper_client.getBGRightImgHeightTextBox().sendKeys("0");
        // Set Right Image Alt Text
        ud_adminHelper_client.getBGRightAltTextBox().sendKeys("ALT TEST Click Here");

        // Save
        ud_adminHelper_client.getBGSaveButton().click();

        // Wait for save confirmation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".save-ok>h2")));
        Assert.assertTrue(ud_adminHelper_client.getBGSaveConfirmText().getText().toLowerCase().contains("saved"),
                "Success Text missing. Background Takeover image settings did not save");

        // Go back to editing article
        client.get(url);

        // Wait for the save button on page return
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("save")));
    }

    /**
     * Create a member source
     */
    public void createMemberSource(String memberSource){
        WebDriver client = getDriver();
        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        //1. Click create member source
        client.get(UD_ADMIN_DOMAIN+"/member_sources/edit");

        //2. Enter name for member source
        ud_adminHelper_client.getMemberSourceNameBox().sendKeys(memberSource);

        //3. Choose subscribe type (can use default  one if you want)
        //Not Implemented

        //4. Click save
        ud_adminHelper_client.getMemberSourceSaveButton().click();
    }

    /**
     * Get the number of placements for a particular module
     * @param placement 1 Background Left, 2 Far Left, 3 Left, 4 Bottom, 5 Right, 6 Background Right
     * @return an integer count
     */
    private int getAdPlacementCount(int placement) {
        List<WebElement> adPlacements = fluentWaitList(By.cssSelector(".newsletter_placement_table .modules_column:nth-child(" + placement + ") li"));
        return adPlacements.size();
    }

    /**
     * Get the href link for the particular placement to navigate to and set an ad
     * @param placement 1 Background Left, 2 Far Left, 3 Left, 4 Bottom, 5 Right, 6 Background Right
     * @param component Name of the link to the ad component. ex newsletter_ad
     * @return href String
     */
    private String getAdPlacement(int placement, String component) {
        WebDriver client = getDriver();

        String cssString = ".newsletter_placement_table .modules_column:nth-child(" + placement +")";
        WebElement modPlacement = client.findElement(By.cssSelector(cssString));
        List<WebElement> adPlacements = modPlacement.findElements(By.tagName("li"));

        String adHref = "";
        for (WebElement adPlacement : adPlacements) {
            if (adPlacement.getText().contains(component)) {
                adHref = adPlacement.findElement(By.cssSelector("td:nth-child(5)>a")).getAttribute("href");
                break;
            }
        }

        return adHref;
    }

    /**
     * Set the article images
     * Navigate to this page first: UD_ADMIN_DOMAIN/article_images/create
     * @param articleID ID Of the article you're editing
     * @param imageFile Image file, typically jpg
     * @param imageLocation The location on the page the image will appear
     */
    private void adArticleImages(String articleID, String imageFile, String imageLocation) {
        WebDriver client = getDriver();
        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        ud_adminHelper_client.enterArticleImage(articleID,IMAGE_PATH + imageFile, imageLocation);
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

    }

    /**
     * Activity for the 3 Column ads to send out a test mailing
     * @param articleID Provide the article id to do the mailing
     */
    private void doTestMailing(String articleID) {
        WebDriver client = getDriver();

        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        // Click HTML Newsletter,
        // Click Send Email,
        client.get(UD_ADMIN_DOMAIN+"/articles/sendMailing/id/"+articleID);

        // Click send Test Mailing,
        ud_adminHelper_client.getSendTestMailingButton().click();
        // Confirm Alert
        // Get a handle to the open alert, prompt or confirmation
        Alert alert = client.switchTo().alert();
        // Get the text of the alert or prompt
        alert.getText();
        // And acknowledge the alert (equivalent to clicking "OK")
        alert.accept();

        // Verify "Test email has been sent" success message
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"), "Test email has been sent"));

        // Add an e-mail address to the Send To field, Click Send Test Mailing,
        ud_adminHelper_client.getMailingSendToBox().sendKeys("qa.test@geno.com");

        // Click send Test Mailing,
        ud_adminHelper_client.getSendTestMailingButton().click();
        // Confirm Alert
        // Get a handle to the open alert, prompt or confirmation
        Alert alert2 = client.switchTo().alert();
        // Get the text of the alert or prompt
        alert2.getText();
        // And acknowledge the alert (equivalent to clicking "OK")
        alert2.accept();

        // Verify "Test email has been sent" success message
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"), "Test email has been sent"));

    }

    /**
     * Activity for the 3 Column ads to send out a true mailing
     * @param articleID Provide the article id to do the mailing
     */
    private void doTrueMailing(String articleID) {
        WebDriver client = getDriver();

        // Click Back to Article, Change Status to Ready to Send, Click Save, Click Send E-mail, Click Send Mailing at bottom of page
        // go back to the article
        client.get(UD_ADMIN_DOMAIN + "/articles/edit/id/" + articleID);

        // Change Status to Ready to Send, Click Save,

        // Set Status to Approved

        dropDownSelector(ud_adminHelper_client.getStatusDropdown(),"Ready to Send");

        // Save
        ud_adminHelper_client.getSaveButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"),"Your modifications have been saved"));

        //Click Send E-mail or go to sendMailing page
        client.get(UD_ADMIN_DOMAIN+"/articles/sendMailing/id/"+articleID);

        //click "Send Mailing" button
        ud_adminHelper_client.getSendMailingButton().click();

        // Confirm Alert
        // Get a handle to the open alert, prompt or confirmation
        Alert alert3 = client.switchTo().alert();
        // Get the text of the alert or prompt
        alert3.getText();
        // And acknowledge the alert (equivalent to clicking "OK")
        alert3.accept();

        // Verify "Email has been sent successfully." success message
        wait.until(ExpectedConditions.textToBePresentInElement(ud_adminHelper_client.getWaitByLocator("SuccessConfirmationText"), "Email has been sent successfully"));

    }

    /**
     * Select the module/ad for the particular placement on the page using the admin tool
     * @param location 1 BGTakeover Left, 2 Far Left, 3 Left, 4 Center/Bottom, 5 Right, 6 BGTakeover Right
     * @param adType module you're looking for. ie "Ad" or "Sponsored Love"
     */
    private void moduleAdPlacement(int location, String adType) {
        WebDriver client = getDriver();
        ud_adminHelper_client = new UD_AdminHelper_Client(client);

        int modValBefore;

        switch (location) {
            case 1:
                modValBefore = getAdPlacementCount(location);
                dropDownSelector(client.findElement(By.id("_select_modules_bgleft")),adType);
                waitForModules(modValBefore,location);
                break;
            case 2:
                modValBefore = getAdPlacementCount(location);
                dropDownSelector(client.findElement(By.id("_select_modules_farleft")),adType);
                waitForModules(modValBefore,location);
                break;
            case 3:
                modValBefore = getAdPlacementCount(location);
                dropDownSelector(ud_adminHelper_client.getSelectLeftModuleDropdown(),adType);
                waitForModules(modValBefore,location);
                break;
            case 4:
                modValBefore = getAdPlacementCount(location);
                dropDownSelector(ud_adminHelper_client.getSelectCenterModuleDropdown(),adType);
                waitForModules(modValBefore,location);
                break;
            case 5:
                modValBefore = getAdPlacementCount(location);
                dropDownSelector(ud_adminHelper_client.getSelectRightModuleDropdown(),adType);
                waitForModules(modValBefore,location);
                break;
            case 6:
                modValBefore = getAdPlacementCount(location);
                dropDownSelector(client.findElement(By.id("_select_modules_bgright")),adType);
                waitForModules(modValBefore,location);
                break;
        }
    }

    /**
     * Invoke this wait for ad modules after selecting them from the dropdown in the admin tool
     * @param initCount Initial Number of ads
     * @param module module you're looking for. ie "Ad" or "Sponsored Love"
     */
    private void waitForModules(final int initCount, final int module) {
        // Wait for link to show up
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply (WebDriver client) {
                return getAdPlacementCount(module) > initCount;
            }
        });
    }

    /**
     * Used in creation of articles. After opening the edit newsletter slot
     * page, set the name of the adName and then save the ad slot.
     *
     * @param adName Ad name
     */
    private void adminNewsLetterAdEdit (String adName) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        client.findElement(By.id("newsletter_content_slot_name")).sendKeys(adName);
        client.findElement(By.name("save")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("save-ok")));

        client.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/fieldset/div[10]/div/a")).click();

        //Wait for main edit page to return

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("save")));
    }


// UD Site Methods

    /**
     *  Navigate to the home page and perform a series of checks
     *  to make sure certain links are present
     */
    public void visitUDFirstTime(){
        WebDriver client = getDriver();

        // enter UD domain name, hit enter, arrive on homepage
        client.get(UD_DOMAIN);
        client.manage().deleteAllCookies();
        client.get(UD_DOMAIN);
        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("document.cookie='udsubpop=3;domain="+UD_DOMAIN_BASE+"; path=/'");
        //client.manage().addCookie(new Cookie ("udsubpop", "3",UD_DOMAIN_BASE, "/", null));
    }

    /**
     * Share an article via email
     * PreRequisite: visitUDFirstTime()
     */
    public void shareArticle(String mailClient, List<String> friends){
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        // Grab the first article under "The Five You Need To Read"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".wideContentInnerBox.boxOne>p>a")));
        client.findElement(By.cssSelector(".wideContentInnerBox.boxOne>p>a")).click();

        // Grab article title
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div/div[3]/div/div/h1/span")));
        String articleTitle = client.findElement(By.xpath("//html/body/div/div[3]/div/div/h1/span")).getText();
        System.out.println("Article Title> " + articleTitle);
        // Click on the Forward button
        client.findElement(By.cssSelector(".smallButton.buttonForward")).click();

        // Handle popup window
        String handler = popUpHandler(client);

        // Confirm Name of Article Appears
        // Wait for it to showup first
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/p")));
        // Now get the title text
        String confirmTitle = client.findElement(By.xpath("/html/body/div[1]/div/div/div/p")).getText();
        // Report it
        Reporter.log("Confirm Title> " + confirmTitle);
        // Compare it to what was captured
        Assert.assertTrue(confirmTitle.contains(articleTitle), "Article title was not found on popup.");

        // Fill in email addresses
        for (int i = 0; i < friends.size(); i++) {
            client.findElement(By.id("invite_email_" + i)).sendKeys(friends.get(i));
        }

        // If visible fill in Name
        if (client.findElement(By.id("name")).isDisplayed()) {
            WebElement name = client.findElement(By.id("name"));
            name.sendKeys("QA TESTER");
        }

        // If visible fill in Email
        if (client.findElement(By.id("email")).isDisplayed()) {
            WebElement email = client.findElement(By.id("email"));
            email.sendKeys(mailClient);
        }


        //Fill in personal message
        client.findElement(By.id("msg")).sendKeys("QA Tester, personal message.\nLorem ipsum lorem ipsum.");

        //Click Submit
        client.findElement(By.id("myaccount_invite_users_form")).submit();

        //Confirmation screen check
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div/div/div/div[1]/p")));
        String str = client.findElement(By.xpath("//html/body/div/div/div/div[1]/p")).getText();
        Assert.assertTrue(str.contains("Thank you. The article has been forwarded to your friend(s)."), "Popup failed to submit");

        //Close Popup
        client.findElement(By.tagName("input")).click();

        //Return to Parent Window
        returnToParentWindow(handler,client);

    }

    /**
     * Save an article to your account
     * PreRequisite: Have an account
     */
    public void saveArticleToAccount() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        // Grab the first article under "The Five You Need To Read"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("wideContentBoxTxt")));
        WebElement bigFiveBox = client.findElement(By.className("wideContentBoxTxt"));
        bigFiveBox.findElement(By.className("boxOne")).findElement(By.tagName("a")).click();

        // Click the save button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#buttonSave")));
        client.findElement(By.cssSelector("#buttonSave")).click();

        // Save the URL
        String url = client.getCurrentUrl();

        // Confirm save button disappeared
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='shareTools']/div[2]/a[4]")));

        // Navigate to MyUD
        client.get(UD_DOMAIN + "/myud");

        // Check MyUD box "Favorites" has incremented
        String favCountXpath = "/html/body/div/div[2]/div[3]/div/div[2]/div/div/div[2]/div[2]/dl/dd/b/a";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(favCountXpath)));
        String favCount = client.findElement(By.xpath(favCountXpath)).getText();
        Assert.assertEquals(favCount,"1","The article didn't save or count didn't increment to 1");

        // Check that the article appears under Favorite
        WebElement articleLink = client.findElement(By.xpath("/html/body/div/div[3]/div/div/div[2]/div[2]/div/div[2]/div/ul/li/a"));

        String favUrlMyUD = articleLink.getAttribute("href");
        // They add /favorites to the end
        Assert.assertEquals(url + "/favorites", favUrlMyUD, "Original article doesn't match saved article.");

    }

    /**
     * Return to the   Homepage
     */
    public void goBackToUDHomepage(){
        WebDriver client = getDriver();

        client.get(UD_DOMAIN);
    }

    /**
     * Check the   header
     * Checks to see the links exist
     */
    public void checkUDHomepageHeader() {
        WebDriver client = getDriver();
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);

        Boolean isNational=false;
        if (client.getCurrentUrl().contains("ntl")) {
            isNational=true;
        }
        //Do all homepage header checks
        if (!isNational) {
            Reporter.log("Checking city link display");
            Assert.assertTrue(ud_headerHelper_Client.getFoodLink().isDisplayed(),"Food link is missing.");
            Assert.assertTrue(ud_headerHelper_Client.getNightlifeLink().isDisplayed(),"Nightlife link is missing.");
            Assert.assertTrue(ud_headerHelper_Client.getLeisureLink().isDisplayed(),"Leisure link is missing.");
        } else {
            Reporter.log("Checking National specific link display");
            Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");
            Assert.assertTrue(ud_headerHelper_Client.getEntertainmentLink().isDisplayed(),"Entertainment link is missing.");
            Assert.assertTrue(ud_headerHelper_Client.getTravelLink().isDisplayed(),"Travel link is missing.");
        }

        // Common between National and City Sites
        Assert.assertTrue(ud_headerHelper_Client.getGearLink().isDisplayed(),"Gear link is missing.");
        Assert.assertTrue(ud_headerHelper_Client.getStyleLink().isDisplayed(),"Style link is missing.");


        Assert.assertTrue(ud_headerHelper_Client.getDrivenLink().isDisplayed(),"Driven link is missing.");
        Assert.assertTrue(ud_headerHelper_Client.getPerksLink().isDisplayed(),"Perks link is missing.");
        Assert.assertTrue(ud_headerHelper_Client.getTheChroniclesLink().isDisplayed(), "Chronicles link is missing.");
        Assert.assertTrue(ud_headerHelper_Client.getKemptLink().isDisplayed(), "Kempt link is missing.");
        Assert.assertTrue(ud_headerHelper_Client.getMobileLink().isDisplayed(),"Mobile link is missing.");

        Assert.assertTrue(ud_headerHelper_Client.getFacebookLink().isDisplayed(), "Facebook Button is Missing");
        Assert.assertTrue(ud_headerHelper_Client.getTwitterLink().isDisplayed(), "Twitter Button is Missing");
    }

    /**
     * Go to each of the available sites in the header and confirm they show up
     */
    public void spiderUDHomepageLinks() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);

        Boolean isNational=false;
        if (client.getCurrentUrl().contains("ntl")) {
            isNational=true;
        }

        String lastURL = client.getCurrentUrl();
        Boolean reporterOut = true;

        if (!isNational) {
            Reporter.log("Navigating to city header links",reporterOut);
            Reporter.log("Navigating to Nightlife",reporterOut);
            ud_headerHelper_Client.getNightlifeLink().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#articleHead")));
            Assert.assertTrue(isNightlifeAccessible(), "Nightlife was not accessible.");

            Reporter.log("Navigating to Food",reporterOut);
            ud_headerHelper_Client.getFoodLink().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#articleHead")));
            Assert.assertTrue(isFoodAccessible(), "Food was not accessible.");

            Reporter.log("Navigating to Leisure",reporterOut);
            ud_headerHelper_Client.getLeisureLink().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#articleHead")));
            Assert.assertTrue(isLeisureAccessible(), "Leisure was not accessible.");
        } else {
            Reporter.log("Navigating to National header links",reporterOut);
            Reporter.log("Navigating to Food & Drink",reporterOut);
            ud_headerHelper_Client.getFoodDrinkNationalLink().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#articleHead")));
            Assert.assertTrue(isFoodDrinkNationalAccessible(),"Food&Drink is not accessible.");

            Reporter.log("Navigating to Entertainment",reporterOut);
            ud_headerHelper_Client.getEntertainmentLink().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#articleHead")));
            Assert.assertTrue(isEntertainmentAccessible(),"Entertainment is not accessible.");

            Reporter.log("Navigating to Travel",reporterOut);
            ud_headerHelper_Client.getTravelLink().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#articleHead")));
            Assert.assertTrue(isTravelAccessible(), "Travel is not Accessible.");
        }

        // Common link navigation
        Reporter.log("Navigating to Style",reporterOut);
        ud_headerHelper_Client.getStyleLink().click();
        wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(".pathBox"),"Style"));
        Assert.assertTrue(isStyleAccessible(), "Style was not accessible.");

        Reporter.log("Navigating to Gear",reporterOut);
        ud_headerHelper_Client.getGearLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#articleHead")));
        Assert.assertTrue(isGearAccessible(), "Gear was not accessible.");


        Reporter.log("Navigating to Perks",reporterOut);
        ud_headerHelper_Client.getPerksLink().click();
        String handlerPerks = popUpHandler(client);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header-logo>a>img")));
        Assert.assertTrue(isPerksAccessible(), "Perks was not accessible.");
        client.close();
        returnToParentWindow(handlerPerks,client);
        client.get(lastURL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mainHeadContent")));

        Reporter.log("Navigating to Mobile",reporterOut);
        ud_headerHelper_Client.getMobileLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mobile-form>h1")));
        Assert.assertTrue(isMobileAccessible(), "Mobile wasn't accessible");
        client.get(lastURL);

        Reporter.log("UD_DOMAIN> " + UD_DOMAIN,true);


        // Don't go to these if we're in a scratch environment
        if (!UD_DOMAIN.contains("scratch")) {

            Reporter.log("Navigating to Driven",reporterOut);
            ud_headerHelper_Client.getDrivenLink().click();
            String handlerDriven = popUpHandler(client);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logo")));
            Assert.assertTrue(isDrivenAccessible(), "Driven was not accessible.");
            client.close();
            returnToParentWindow(handlerDriven,client);
            client.get(lastURL);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mainHeadContent")));

            Reporter.log("Navigating to Chronicles",reporterOut);
            ud_headerHelper_Client.getTheChroniclesLink().click();
            String handlerChronicles = popUpHandler(client);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1>.ir")));
            Assert.assertTrue(isPartiesAccessible(), "Chronicles was not accessible.");
            client.close();
            returnToParentWindow(handlerChronicles,client);
            client.get(lastURL);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mainHeadContent")));

            Reporter.log("Navigating to Kempt",reporterOut);
            ud_headerHelper_Client.getKemptLink().click();
            String handlerKempt = popUpHandler(client);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logo>a>img")));
            Assert.assertTrue(isKemptAccessible(), "Kempt was not accessible.");
            client.close();
            returnToParentWindow(handlerKempt,client);
            client.get(lastURL);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mainHeadContent")));

        }

        Reporter.log("Navigating to UD Facebook",reporterOut);
        ud_headerHelper_Client.getFacebookLink().click();
        String parent = popUpHandler(client);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".profilePic.img")));
        Assert.assertTrue(client.getCurrentUrl().contains("www.facebook.com/geno"), "Didn't navigate to UD FB page");
        client.close();
        returnToParentWindow(parent,client);

        Reporter.log("Navigating to UD Twitter",reporterOut);
        ud_headerHelper_Client.getTwitterLink().click();
        parent = popUpHandler(client);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".avatar.size73")));
        Assert.assertTrue(client.getCurrentUrl().contains("twitter.com/geno"), "Didn't navigate to UD Twitter page");
        client.close();
        returnToParentWindow(parent,client);
    }

    /**
     * Check the   footer while logged out
     * Checks to see the links exist
     */
    public void checkUDHomepageFooter() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_footerHelper_Client = new UD_FooterHelper_Client_v2(client);

        // If we're signed out do this check
        if (client.findElement(By.cssSelector(".trigger")).getText().equals("Sign Up")) {
            Assert.assertTrue(ud_footerHelper_Client.getSignUpHomePageLink().getText().equals("Sign Up"), "Sign Up link was not present or text incorrect.");
        } else {
            Assert.assertTrue(client.findElements(ud_footerHelper_Client.getWaitByLocator("SignUp")).size() == 0, "Sign up link was displayed when already signed in.");
        }

        //do all city homepage footer checks
        ud_footerHelper_Client = new UD_FooterHelper_Client_v2(client);
        Assert.assertTrue(ud_footerHelper_Client.getAboutUsHomePageLink().getText().equals("About Us"), "About Us link was not present.");
        Assert.assertTrue(ud_footerHelper_Client.getMyUDHomePageLink().getText().equals("My UD"), "My UD link was not present.");
        Assert.assertTrue(ud_footerHelper_Client.getContactHomePageLink().getText().equals("Contact"), "Contact link was not present.");
        Assert.assertTrue(ud_footerHelper_Client.getJobsHomePageLink().getText().equals("Jobs"), "Jobs/Careers link was not present.");
        Assert.assertTrue(ud_footerHelper_Client.getAdvertiseHomePageLink().getText().equals("Advertise"), "Advertise link was not present.");
        Assert.assertTrue(ud_footerHelper_Client.getTipsHomePageLink().getText().equals("Tips"), "Tips link was not present.");
        Assert.assertTrue(ud_footerHelper_Client.getUnsubscribeHomePageLink().getText().equals("Unsubscribe"), "Unsubscribe link was not present.");
        Assert.assertTrue(ud_footerHelper_Client.getMobileSiteHomePageLink().getText().equals("Mobile Site"), "Mobile link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getEmailIssuesHomePageLink().getText().equals("Email Issues"), "Email issues link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getPrivacyPolicyHomePageLink().getText().equals("Privacy Policy"), "Privacy policy link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getUserAgreementHomePageLink().getText().equals("User Agreement"), "User agreement link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getEditorialPolicyHomePageLink().getText().equals("Editorial Policy"), "Editorial policy was not present.");

        Assert.assertTrue(ud_footerHelper_Client.getAtlantaHomePageLink().getText().equals("Atlanta"), "Atlanta link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getBostonHomePageLink().getText().equals("Boston"), "Boston link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getChicagoHomePageLink().getText().equals("Chicago"), "Chicago link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getDallasHomePageLink().getText().equals("Dallas"), "Dallas link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getDCHomePageLink().getText().equals("DC"), "DC link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getDrivenHomePageLink().getText().equals("Driven"), "Driven link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getJetsetHomePageLink().getText().equals("Jetset"), "Jetset link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getLasVegasHomePageLink().getText().equals("Las Vegas"), "Las Vegas link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getLosAngelesHomePageLink().getText().equals("Los Angeles"), "Los Angeles link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getMiamiHomePageLink().getText().equals("Miami"), "Miami link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getNationalHomePageLink().getText().equals("National"), "National link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getNewYorkHomePageLink().getText().equals("New York"), "New York link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getSanFranciscoHomePageLink().getText().equals("San Francisco"), "San Francisco link was not present or text incorrect.");
        Assert.assertTrue(ud_footerHelper_Client.getSkiBoardHomePageLink().getText().equals("Ski & Board"), "Ski & Board link was not present or text incorrect.");

    }

    /**
     * Confirm Night Life is accessible
     *
     * @return boolean
     */
    private boolean isNightlifeAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/nightlife");
    }

    /**
     * Confirm The Chronicles is Accessible
     *
     * @return boolean
     */
    private boolean isPartiesAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains("thechronicles");
    }

    /**
     * Confirm Kempt is accessible
     *
     * @return boolean
     */
    private boolean isKemptAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains("kempt");
    }

    /**
     * Confirm Driven is accessible
     *
     * @return boolean
     */
    private boolean isDrivenAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains("driven");
    }

    /**
     * Confirm Food is accessible
     *
     * @return boolean
     */
    private boolean isFoodAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/food");
    }

    /**
     * Confirm Food & Drink is accessible
     *
     * @return boolean
     */
    private boolean isFoodDrinkNationalAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/fooddrink");
    }

    /**
     * Confirm Entertainment link is accessible
     *
     * @return boolean
     */
    private boolean isEntertainmentAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/entertainment");
    }

    /**
     * Confirm Mobile link is accessible
     *
     * @return boolean
     */
    private boolean isMobileAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/mobile");
    }

    /**
     * Confirm Travel link is accessible
     *
     * @return boolean
     */
    private boolean isTravelAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/travel");
    }

    /**
     * Confirm Style link is accessible
     *
     * @return boolean
     */
    private boolean isStyleAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/style");
    }

    /**
     * Confirm Gear link is accessible
     *
     * @return boolean
     */
    private boolean isGearAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/gear");
    }

    /**
     * Confirm Leisure link is accessible
     *
     * @return boolean
     */
    private boolean isLeisureAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(UD_DOMAIN + "/archives/" + UDcity + "/leisure");
    }

    /**
     * Confirm perks link is accessible
     * UPDATE: there is some change to the perks URL that happens when you're logged in
     * the City name is replaced. When this is fixed uncomment the other return value.
     * @return boolean
     */
    private boolean isPerksAccessible(){
        WebDriver client = getDriver();
        return client.getCurrentUrl().contains(PERKS_DOMAIN);
    }

    /**
     * Run this method after logging in to unsubscribe
     * from all perks and editorial emails
     */
    public void unSubscribeFromEmails() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        // Declare helpers
        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);
        ud_unSubscribeHelper_client = new UD_UnSubscribeHelper_Client_v2(client);

        // Assume logged in, edit settings
        ud_sealHelper_Client.getEditSettingsLink().click();

        // Pause for a couple secs until ajax window shows up
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("myUDpopupEditorials")));

        // Uncheck all the editorial boxes
        WebElement editorial = client.findElement(By.className("myUDpopupEditorials"));
        List<WebElement> editions = editorial.findElements(By.tagName("div"));
        for (WebElement edition : editions) {
            if (edition.findElement(By.tagName("input")).isSelected()) {
                WebElement box = edition.findElement(By.tagName("input"));
                box.click();
            }
        }

        // Uncheck all the perks boxes
        WebElement perksList = client.findElement(By.className("htmlEditionsHolder"));
        List<WebElement> perks = perksList.findElements(By.tagName("div"));
        for (WebElement perk : perks) {
            if (perk.findElement(By.tagName("input")).isSelected()) {
                WebElement box = perk.findElement(By.tagName("input"));
                box.click();
            }
        }

        ud_sealHelper_Client.getEnterPasswordMyUDBox().sendKeys(PASSWORD);
        ud_sealHelper_Client.getConfirmPasswordMyUDBox().sendKeys(PASSWORD);

        // Click update
        ud_sealHelper_Client.getUpdateButton().click();

        // Confirm unsubscription

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_unSubscribeHelper_client.getWaitByLocator("ConfirmButton")));
        ud_unSubscribeHelper_client.getConfirmButton().click();

        // Close the lightbox

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".input.ajaxClose.directLink")));
        WebElement closeButton = client.findElement(By.cssSelector(".input.ajaxClose.directLink"));
        try {
            closeButton.click();
        } catch (StaleElementReferenceException e) {
            Reporter.log("WARNING: Close button caused stale element exception");
            StaleElementHandler(By.className(".input.ajaxClose.directLink"));
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(".input.ajaxClose.directLink")));
    }

    /**
     * Confirm the unsubscribe email contains the text
     * "successfully unsubscribed"
     */
    public void unSubscribeMailConfirm(){
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        emailHelper_Client.searchEmail("successfully unsubscribed");
    }

    public void signUpUDNonModal () {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);

        //step1, 1st signup modal:
        //a. Click SignUp Seal

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_sealHelper_Client.getWaitByLocator("JoinNowButton")));
        String url = ud_sealHelper_Client.getJoinNowButton().getAttribute("href");

        client.get(url);
    }

    public void signUpUDModal () {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);

        //step1, 1st signup modal:
        //a. Click SignUp Seal

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_sealHelper_Client.getWaitByLocator("JoinNowButton")));
        ud_sealHelper_Client.getJoinNowButton().click();
    }

    /**
     * Sign up and register for perks & editorial
     * via New York UD edition
     * @param email An email to create the account with
     */
    public void signUpUDStep1(String email) {

        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);

        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);

        //step1, 1st signup modal:
        //a. Click SignUp Seal

//        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_sealHelper_Client.getWaitByLocator("JoinNowButton")));
//        ud_sealHelper_Client.getJoinNowButton().click();

        //b. Enter email address
        ud_signupHelper_Client.getEnterEmailBox().sendKeys(email);
        System.out.println("UD EMAIL CLIENT> " + email);

        // *** COMMENTED OUT BECAUSE PRODUCTION CANT HANDLE ***
        // *** PROD TAKES OVER A MINUTE TO PROCESS EMAIL ADDRESS ***
        //c. Select Editions
        //New York, New York Perks are selected by default

//        ud_signupHelper_Client.getDrivenSelect().click();
//        ud_signupHelper_Client.getJetSetSelect().click();
//        ud_signupHelper_Client.getLasVegasEditionSelect().click();
//        ud_signupHelper_Client.getNationalEditionSelect().click();
//        ud_signupHelper_Client.getSkiBoardSelect().click();
//
//        //click "more" link to show all Editorials
//        ud_signupHelper_Client.getMoreLink1().click();
//
//        // check all of them
//        ud_signupHelper_Client.getAtlantaEditionSelect().click();
//        ud_signupHelper_Client.getBostonEditionSelect().click();
//        ud_signupHelper_Client.getChicagoEditionSelect().click();
//        ud_signupHelper_Client.getDallasEditionSelect().click();
//        ud_signupHelper_Client.getDCEditionSelect().click();
//        ud_signupHelper_Client.getLosAngelesEditionSelect().click();
//        ud_signupHelper_Client.getMiamiEditionSelect().click();
//        ud_signupHelper_Client.getSanFranciscoEditionSelect().click();
//
//        //click "more" link to see the Perks editions
//        ud_signupHelper_Client.getMoreLink2().click();
//
//        // check all of them
//        ud_signupHelper_Client.getBostonPerksSelect().click();
//        ud_signupHelper_Client.getChicagoPerksSelect().click();
//        ud_signupHelper_Client.getDCPerksSelect().click();
//        ud_signupHelper_Client.getLosAngelesPerksSelect().click();
//        ud_signupHelper_Client.getMiamiPerksSelect().click();
//        ud_signupHelper_Client.getNationalPerksSelect().click();

        //click "JOIN" button
        ud_signupHelper_Client.getJoinButton().click();

        //Wait for the ajax response back. The email address will be displayed in the 'Seal' sign up box.
        //Wait up to 90 seconds polling every 2 seconds
//        WebDriverWait ajaxWait = new WebDriverWait(client,90,2000);
//        try {
//            ajaxWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".homeMyUDText>div")));
//        } catch (TimeoutException TE) {
//            throw new TimeoutException("POST TOOK TOO LONG");
//        }
    }

    public void signUpUDStep1ModalPostCheck () {
        WebDriver client = getDriver();
        WebDriverWait ajaxWait = new WebDriverWait(client,90,2000);
        try {
            ajaxWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".homeMyUDText>div")));
        } catch (TimeoutException TE) {
            throw new TimeoutException("POST TOOK TOO LONG");
        }
    }

    public void signUpUDStep1NonModalPostCheck () {
        WebDriver client = getDriver();
        WebDriverWait ajaxWait = new WebDriverWait(client,90,2000);
        try {
            ajaxWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".myUDUserInfo>b")));
        } catch (TimeoutException TE) {
            throw new TimeoutException("POST TOOK TOO LONG");
        }
    }

    /**
     * Complete registration form by filling out
     * name, gender, income range, zip code, etc.
     */
    public void signUpUDStep2() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);

        String var = emailHelper_Client.generateDate(DATEFORMAT);

        //step2, 2nd signup modal:
        //enter PASSWORD
        ud_signupHelper_Client.getPasswordTextBox().sendKeys(PASSWORD);
        //confirm PASSWORD
        ud_signupHelper_Client.getConfirmPasswordTextBox().sendKeys(PASSWORD);
        //First Name
        ud_signupHelper_Client.getFirstNameTextBox().sendKeys("FN_" + var);
        //Last Name
        ud_signupHelper_Client.getLastNameTextBox().sendKeys("LN_" + var);
        //Gender
        ud_signupHelper_Client.selectGenderRandom();
        //Income Range
        ud_signupHelper_Client.selectIncomeRangeRandom();
        //Zip Code
        ud_signupHelper_Client.getZipCodeTextBox().sendKeys("10001");
        //Birthday (MM/DD/YYYY)
        ud_signupHelper_Client.getBirtdayTextBox().sendKeys("07/07/1977");
        //click "SUBMIT" button
        ud_signupHelper_Client.getSubmitButton().click();
    }

    /**
     * Fills out the refer a friend form.
     * @param friend Array of friend emails
     * @param skip Set to true if you want to skip signing up friends
     */
    public void signUpUDStep3(List<String> friend, Boolean skip) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);

        // step3, 3rd Sign Up modal: Invite Friends
        // First wait for the modal window to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".useAjaxProxy.inviteDirect")));
        // If set skip to false if you want to sign up friends
        if (!skip) {
            for (int i = 0; i < friend.size(); i++) {
                ud_signupHelper_Client.getEmailFriendTextBox((i + 1)).clear();
                ud_signupHelper_Client.getEmailFriendTextBox((i + 1)).sendKeys(friend.get(i));
                System.out.println(friend.get(i));
            }
            ud_signupHelper_Client.getInviteButton().click();
        } else {
        // True if you want to skip
            ud_signupHelper_Client.getSkipLink().click();
        }
    }

    /**
     * Close the sign up form.
     */
    public void signUpUDStep4() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        //step4, 4th Sign Up modal confirmation, close final confirm Sign Up box
        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_signupHelper_Client.getWaitByLocator("CloseButton")));
        ud_signupHelper_Client.getCloseButton().click();

        //end of registration
    }

    /**
     * Click the change city link from the homepage
     */
    public void changeCityFromUDHomepage() {
        WebDriver client = getDriver();

        //click on "Change City" from any city home page, navigate back to home page
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);

        ud_headerHelper_Client.getChangeCityLink().click();
    }

    /**
     * Click the Atlanta link from the homepage
     */
    public void accessAtlantaFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getAtlantaLink().click();
        UDcity = "atl";
        UDcityPerks = "/national.html";
    }

    /**
     * Click the Boston link from the homepage
     */
    public void accessBostonFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getBostonLink().click();
        UDcity = "bos";
        UDcityPerks = "/boston.html";
    }

    /**
     * Click the Chicago link from the homepage
     */
    public void accessChicagoFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getChicagoLink().click();
        UDcity = "chi";
        UDcityPerks = "/chicago.html";
    }

    /**
     * Click the Dallas link from the homepage
     */
    public void accessDallasFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getDallasLink().click();
        UDcity = "dal";
        UDcityPerks = "/national.html";
    }

    /**
     * Click the DC link from the homepage
     */
    public void accessWashingtonDCFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getWashingtonDCLink().click();
        UDcity = "dc";
        UDcityPerks = "/washington-dc.html";
    }

    /**
     * Click the Jetset link from the homepage
     */
    public void accessJetsetFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getJetsetLink().click();
        UDcity = "jt";
        UDcityPerks = "/national.html";
    }

    /**
     * Click the Las Vegas link from the homepage
     */
    public void accessLasVegasFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getLasVegasLink().click();
        UDcity = "lv";
        UDcityPerks = "/national.html";
    }

    /**
     * Click the LA link from the homepage
     */
    public void accessLosAngelesFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getLosAngelesLink().click();
        UDcity = "la";
        UDcityPerks = "/los-angeles.html";
    }

    /**
     * Click the Miami link from the homepage
     */
    public void accessMiamiFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getMiamiLink().click();
        UDcity = "mia";
        UDcityPerks = "/miami.html";
    }

    /**
     * Click the National edition link from the homepage
     */
    public void accessNationalFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getNationalLink().click();
        UDcity = "ntl";
        UDcityPerks = "/national.html";
    }

    /**
     * Click the New York link from the homepage
     */
    public void accessNewYorkFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getNewYorkLink().click();
        UDcity = "nyc";
        UDcityPerks = "/new-york.html";
    }

    /**
     * Click the San Francisco link from the homepage
     */
    public void accessSanFranciscoFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getSanFranciscoLink().click();
        UDcity = "sfo";
        UDcityPerks = "/national.html";
    }

    /**
     * Click the Ski Board link from the home page
     */
    public void accessSkiBoardFromUDHomepage() {
        WebDriver client = getDriver();
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getSkiBoardLink().click();
        UDcity = "bos";
        UDcityPerks = "/boston.html";
    }

// Sign-up methods end here

// Login/SignOut methods

    /**
     * Use to pass email and PASSWORD in via other tests.
     * ex. currently used in UD_Unsubscribe_EditionsPerks
     * @param email Email address
     * @param pw PASSWORD
     * @throws TimeoutException thrown if Ajax call takes too long
     */
    public void loginUD(String email, String pw) throws TimeoutException {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);
        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);
        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);
        client.get(UD_DOMAIN);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_headerHelper_Client.getWaitByLocator("SignInHover")));

        displayCssHover();
        ud_headerHelper_Client.getMemberLoginLink().click();

        ud_signupHelper_Client.getSignInEmailTextBox().sendKeys(email);
        ud_signupHelper_Client.getSignInPasswordTextBox().sendKeys(pw);
        ud_signupHelper_Client.getSignInButton().click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(ud_signupHelper_Client.getWaitByLocator("SignInSpinner")));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".homeMyUDUserInfo>b")));
        } catch (TimeoutException TE) {
            throw new TimeoutException("POST TOOK TOO LONG");
        }
    }

    /**
     * Logout of UD
     */
    public void logoutUD() {
        WebDriver client = getDriver();
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_headerHelper_Client.getWaitByLocator("MyAccountHover")));

        displayCssHover();
        ud_headerHelper_Client.getLogoutLink().click();

    }

    /**
     * Perform a PASSWORD reset.
     */
    public void resetPasswordUD(String emailClient) {
        WebDriver client = getDriver();
        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);
        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_headerHelper_Client.getWaitByLocator("SignInHover")));

        // Display the hover
        displayCssHover();

        // Click member login link
        ud_headerHelper_Client.getMemberLoginLink().click();

        // Perform PW reset Stale element seems to happen here in production periodically
        try {
            ud_signupHelper_Client.getSignInForgotPWLink().click();
        } catch (StaleElementReferenceException e) {
            // Try to get it again
            ud_signupHelper_Client.getSignInForgotPWLink().click();
        }

        ud_signupHelper_Client.getSignInPWResetEmailTextBox().sendKeys(emailClient);
        ud_signupHelper_Client.getSignInPWResetSubmitButton().click();

        // Wait for the confirmation message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".errorfield.slideDown")));

        // Confirm the confirmation message text
        Assert.assertTrue(client.findElement(By.cssSelector(".errorfield.slideDown"))
                .getText().contains("You'll receive an email shortly allowing you to reset your password."),
                "Confirmation message incorrect or didn't appear");
    }

    /**
     * Login to the UD site then update your account
     * by checking some editions and perks
     * @param modal 0 for modal Edit Settings 1 for non-modal
     */
    public void editSettingsUD(int modal) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);

        if (modal == 0) {
            ud_sealHelper_Client.getEditSettingsLink().click();
        } else if (modal == 1) {
            client.get(ud_sealHelper_Client.getEditSettingsLink().getAttribute("href"));
        } else {
            throw new IllegalArgumentException("Incorrect parameter sent: 0 for modal, 1 for non-modal");
        }

        ud_sealHelper_Client.getEnterPasswordMyUDBox().sendKeys(PASSWORD);
        ud_sealHelper_Client.getConfirmPasswordMyUDBox().sendKeys(PASSWORD);

        ud_sealHelper_Client.getDCEditionSelect().click();
        ud_sealHelper_Client.getPhillyEditionSelect().click();
        ud_sealHelper_Client.getSeattleEditionSelect().click();
        ud_sealHelper_Client.getDCPerksSelect().click();

        ud_sealHelper_Client.getUpdateButton().click();

        if (modal == 0) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ud_sealHelper_Client.getWaitByLocator("Close")));
            ud_sealHelper_Client.getCloseButton().click();
        }

    }

    public void generateSignupModal() {
        WebDriver client = getDriver();
        WebDriverWait wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        int counter = 3;
        while (counter > 0) {
            counter--;
            randomPageLink();
            Reporter.log("Got URL> " + client.getCurrentUrl(), true);

            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
                    JavascriptExecutor js = (JavascriptExecutor) d;
                    //System.out.println(js.executeScript("return document.readyState == 'complete'"));
                    return (Boolean) js.executeScript("return document.readyState == 'complete'");
                }
            });
        }

        Reporter.log("Waiting for modal popup to appear",true);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modalContent")));

    }

    private void randomPageLink() {
        WebDriver client = getDriver();

        List<WebElement> links = client.findElements(By.tagName("a"));
        List<WebElement> visibleLinks = new ArrayList<WebElement>();
        for (WebElement link : links) {
            if (link.isDisplayed()) {
                visibleLinks.add(link);
            }
        }
        Random r = new Random();
        int i = r.nextInt(visibleLinks.size());

        //Reporter.log(links.get(i).getAttribute("href"),true);
        String link = visibleLinks.get(i).getAttribute("href");
        while (!link.contains(UD_DOMAIN)) {
            i = r.nextInt(visibleLinks.size());
            link = visibleLinks.get(i).getAttribute("href");
        }
        visibleLinks.get(i).click();
    }

    /**
     * Use the findElement method to extend some functionality to searching for
     * elements. Attempts to find the element 3 times before throwing an error.
     *
     * @param type  Search by name, xpath, tagname, classname, css, id
     * @param locator Enter the name of the element you're looking for
     * @param timeout Enter the number of seconds for the timeout
     * @return that web element or throw an error
     */
    private WebElement findElementAndCheckBy(final String type, String locator, int timeout) throws ElementNotVisibleException {
        WebDriver client = getDriver();

        int counter = 0;
        boolean flag = false;
        final String ele = locator;
        WebElement a = null;

        do {
            try {
                System.out.println("Trying to find the element> " + ele + "\nTimeout in> " + timeout + " seconds.");
                a = (new WebDriverWait(client, timeout)).until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        WebElement ret = null;
                        if (type.equals("name")) {
                            ret = d.findElement(By.name(ele));
                        } else if (type.equals("xpath")) {
                            ret = d.findElement(By.xpath(ele));
                        } else if (type.equals("tagname")) {
                            ret = d.findElement(By.tagName(ele));
                        } else if (type.equals("classname")) {
                            ret = d.findElement(By.className(ele));
                        } else if (type.equals("css")) {
                            ret = d.findElement(By.cssSelector(ele));
                        } else if (type.equals("id")) {
                            ret = d.findElement(By.id(ele));
                        }
                        System.out.println("Found> " + ele);
                        return ret;
                    }
                });
                flag = true;
            } catch (TimeoutException e) {
                counter++;
                System.out.println("Attempt " + counter + ": Could not find> " + ele);
                System.out.println("Trying again");
            }
        } while (counter <= 2 && !flag);
        if (a == null) {
            throw new ElementNotVisibleException("3rd Attempt reached. Could not find> " + ele);
        } else {
            return a;
        }
    }

    /**
     * Use this to switch to the popup and handle the return to parent window value
     * @param client WebDriver
     * @return parentWindowHandle
     */
    private String popUpHandler(WebDriver client) {
        String parentWindowHandle=client.getWindowHandle();
        //handle pop-up window
        Set<?> s_add2=client.getWindowHandles();
        //this method will you handle of all opened windows

        for (Object aS_add2 : s_add2) {
            String windowHandle = aS_add2.toString();
            if (!windowHandle.equals(parentWindowHandle)) {
                client.switchTo().window(windowHandle);
                break;
            }
        }
        return parentWindowHandle;
    }

    /**
     * Use this to return to parent window
     * @param parent Parent window
     * @param client WebDriver
     */
    private void returnToParentWindow(String parent, WebDriver client) {
        client.switchTo().window(parent);
    }

    /**
     * Select item from dropdown given the dropdown's ID
     * @param dropDownID ID of the dropdown
     * @param value The item from the dropdown to select
     */
    private void dropDownSelector (String dropDownID, String value) {
        WebDriver client = getDriver();

        Select dropdown = new Select(client.findElement(By.id(dropDownID)));
        dropdown.selectByVisibleText(value);
    }

    /**
     * Select item from dropdown given the dropdown's ID
     * @param element WebElement related to the dropdown
     * @param value The item from the dropdown to select
     */
    private void dropDownSelector (WebElement element, String value) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }

    /**
     * Force visibility of the CssHover to display instead of using mouse over
     */
    private void displayCssHover() {
        WebDriver client = getDriver();

        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("$j('#global-navigation .user .sub ul').css({'display': 'block'});");
    }

    /**
     * Use the to handle the possiblility of stale elements
     * @param by By.ID, By.cssSelector
     */
    private void StaleElementHandler (By by) {
        WebDriver client = getDriver();

        int count = 0;
        while (count < 4){
            try {
                WebElement el = client.findElement(by);
                el.click();
            } catch (StaleElementReferenceException e){
                e.toString();
                Reporter.log("Trying to recover from a stale element :" + e.getMessage());
                count = count+1;
            }
            count = count+4;
        }
    }

    /**
     * Use this to find a webelement
     * @param locator By.id, By.cssSelector etc
     * @return WebElement
     */
    private WebElement fluentWait(final By locator){
        WebDriver client = getDriver();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(client)
                .withTimeout(GLOBAL_TIME_OUT, TimeUnit.SECONDS)
                .pollingEvery(GLOBAL_POLLING, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement el = wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                }
        );
        return  el;
    }

    /**
     * Use this to find a list of available webelements
     * @param locator By.id, By.cssSelector etc
     * @return WebElement List object
     */
    private List<WebElement> fluentWaitList(final By locator){
        WebDriver client = getDriver();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(client)
                .withTimeout(GLOBAL_TIME_OUT, TimeUnit.SECONDS)
                .pollingEvery(GLOBAL_POLLING, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        List<WebElement> el = wait.until(
                new Function<WebDriver, List<WebElement>>() {
                    public List<WebElement> apply(WebDriver driver) {
                        return driver.findElements(locator);
                    }
                }
        );
        return el;
    }

}
