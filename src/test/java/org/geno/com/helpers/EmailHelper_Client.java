package org.geno.com.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.geno.com.common.IHelper;
import org.geno.com.common.UDBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class EmailHelper_Client extends IHelper implements UDBase {

    private int emailTimeOut = 15000; //Timeout in MS for retrying emails
    private int globalTimeOut = 10; //Timeout in SEC for finding elements

    private int randPlaces = 4; //Number of digits for random email generation

    private WebDriver client;

    public EmailHelper_Client(WebDriver client) {
        super(client,"CheckEmail_v2.xml");
        this.client = client;
    }

    /**
     * Return a date in the format with a random number
     * string attached. See value of DATEFORMAT in UDBase
     * @see UDBase
     * @param dateFormat
     * @return XXXX-DATEFORMAT
     */
    public String generateDate(String dateFormat) {
        Date now = new java.util.Date();
        DateFormat df = new SimpleDateFormat(dateFormat);
        return randomNum(randPlaces) + "-" + df.format(now);
    }

    private String randomNum(int n) {
        String str = "";
        for (int i = 0; i < n; i++) {
            int r = (int) (Math.random() * 10);
            str += r;
        }
        return str;
    }

    public String generateEmailClient() {
        return EMAIL_USER_NAME + "+" + generateDate(DATEFORMAT) + EMAIL_DOMAIN;
    }

    public List<String> generateFriendClient(int count) {
        if (count > 5) {
            count = 5;
            System.out.println("Count set over 5, max is 5.");
        }
        List<String> arr = new ArrayList<String>();
        //String[] arr = new String[count];
        for (int i = 0; i<count; i++) {
            arr.add(i, EMAIL_USER_NAME + "+friend_" + (i+1) + "_" + randomNum(randPlaces) + EMAIL_DOMAIN);
        }
        return arr;
    }

    public void clientLogInToGmail() {
        //make sure you're logged out first 
        client.get(GOOGLE_EMAIL_LINK);

        WebElement em = locateWebElement("Email");
        em.sendKeys(JENKINSEMAIL);

        WebElement ps = locateWebElement("Password");
        ps.sendKeys(JENKINSEMAILPW);

        WebElement si = locateWebElement("SignIn");
        si.click();
    }

    public void clientLogoutGmail() {
        WebElement signOut = locateWebElement("LogOut");
        signOut.click();
    }

    public boolean doEmailSearch (String searchString, int timeout) {
        Boolean flag = false;
        Integer counter = 0;
        // find search box and enter the searchString parameter
        //assuming the single correct result came back, click that email

        do {
            if (System.getProperty("os.name").contains("iphone")) {
                locateWebElement("MobileSearchButton").click();
                locateWebElement("MobileSearchBox").sendKeys(searchString);
                locateWebElement("MobileSearchEmailButton").click();
                WebDriverWait mobWait = new WebDriverWait(client,10,2000);
                mobWait.until(ExpectedConditions.visibilityOfElementLocated(getWaitByLocator("FirstEmail")));
                locateWebElement("FirstEmail").click();
                flag = true;
            } else {
                try {
                    WebElement el1 = locateWebElement("SearchBox");
                 //el1.sendKeys(searchString);
                    el1.clear();
                    el1.sendKeys(searchString);

                 //find search mail button and click it
                    locateWebElement("SearchMailButton").click();
                    WebDriverWait wait = new WebDriverWait(client,10);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ts")));
                    client.findElement(By.cssSelector(".ts")).click();
                    flag = true;
                } catch (NoSuchElementException e) {
                    counter++;
                    System.out.println("Email wasn't found, trying again. Time out in> " + TimeUnit.MILLISECONDS.toSeconds(timeout) +" seconds.");
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } catch (StaleElementReferenceException sere) {
                    counter++;
                    System.out.println("Stale Element Reference");
                    System.out.println("Email wasn't found, trying again. Time out in> " + TimeUnit.MILLISECONDS.toSeconds(timeout) +" seconds.");
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                } catch (TimeoutException te) {
                    counter++;
                    System.out.println("Email wasn't found, trying again. Time out in> " + TimeUnit.MILLISECONDS.toSeconds(timeout) +" seconds.");
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } while (!flag && counter<3);

        if (counter >=3) {
            System.out.println("WARNING: Attempted email search 3 times> " + searchString);
            return false;
        } else {
            return true;
        }
    }

    public String getResetEmailLink () {
        WebDriverWait waitForLink = new WebDriverWait(client, 30);
        waitForLink.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("here")));
        WebElement link = client.findElement(By.partialLinkText("here"));
        return link.getAttribute("href");
    }

    public void clickResetEmailRequestLink (){
        client.findElement(By.partialLinkText("here")).click(); 
    }

    public boolean searchEmailBody (String searchString) {
        WebDriverWait wait = new WebDriverWait(client,globalTimeOut,5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".msg")));
        WebElement content = client.findElement(By.cssSelector(".msg"));
        String data = content.getText();
        return (data.contains(searchString));
    }

    public void searchEmail(String searchString) {
           Assert.assertTrue(searchEmailBody(searchString),"Text or email not found.");
    }

    public void silverPopLogin(String email, String pass) {
        client.navigate().to("https://login5.silverpop.com/");
        WebElement username = client.findElement(By.id("username"));
        username.sendKeys(email);
        WebElement password = client.findElement(By.id("password"));
        password.sendKeys(pass);
        WebElement login = client.findElement(By.className("button"));
        login.click();
    }

    public void navigateToSearch() {
        WebDriverWait wait = new WebDriverWait(client, globalTimeOut);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("borderRight")));
        WebElement dbSection = client.findElement(By.className("borderRight"));
        dbSection.findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td/table/tbody/tr/td/div/div/div[3]/div/table[2]/tbody/tr[2]/td/ul/li[5]/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UD Dev']")));
        WebElement dbs = client.findElement(By.xpath("//*[@id='UD Dev']"));
        dbs.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/form/table[3]/tbody/tr/td/div[2]/div/div/div[4]/div/div[3]/div/div/div/span")));
        WebElement searchTab = client.findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td/div[2]/div/div/div[4]/div/div[3]/div/div/div/span"));
        searchTab.click();
    }

    public boolean optOutSearch(String email) {
        boolean flag;
        flag = false;

        WebDriverWait wait = new WebDriverWait(client, globalTimeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchOptOutsCheckBox")));
        WebElement searchOptOutBox = client.findElement(By.id("searchOptOutsCheckBox"));
        searchOptOutBox.click();

        Select select = new Select(client.findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td/div[2]/div/div[3]/div[3]/div/div[3]/table/tbody/tr/td[3]/select")));
        select.deselectAll();
        select.selectByVisibleText("equals");

        WebElement searchText = client.findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td/div[2]/div/div[3]/div[3]/div/div[3]/table/tbody/tr/td[4]/input"));
        searchText.clear();
        searchText.sendKeys(email);

        WebElement searchButton = client.findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td/div[2]/div/div[3]/div[3]/div/div[3]/table/tbody/tr/td[5]/button"));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/form/table[3]/tbody/tr/td/div[2]/div/div[3]/div[3]/div/div[5]/table/tbody/tr")));
        WebElement result = client.findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td/div[2]/div/div[3]/div[3]/div/div[5]/table/tbody/tr"));
        List<WebElement> columns = result.findElements(By.tagName("td"));

        for (WebElement column : columns) {
            if (column.getText().equals("Opted Out")) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    // Global Email Check Methods

    /**
     * Log into Gmail and wait
     */
    public void loginToGmail() {
        clientLogInToGmail();
    }

    /**
     * Check for Welcome to Manero email is received
     */
    public void verifyWelcomeManeroEmailReceived(String emailClient) {
        Reporter.log("Searching for Welcome Email",true);
        doEmailSearch("to: " + emailClient + " subject: We Embrace You", emailTimeOut);
    }

    /**
     * Check that invitation emails were received
     */
    public void verifyInvitationsManeroEmailsReceived(List<String> friends) {
        for (int i = 0; i<friends.size(); i++) {
            Reporter.log("Searching for invitation email to friend " + (i+1),true);
            doEmailSearch("to: "+ friends.get(i)+" subject: You've been invited to join Manero Club Social y Deportivo", emailTimeOut);
        }
    }

    /**
     * Check for Welcome to UD email is received
     */
    public void verifyWelcomeUDEmailReceived(String emailClient) {
        Reporter.log("Searching for Welcome Email",true);
        doEmailSearch("to: " + emailClient + " subject: Welcome to the Club", emailTimeOut);
    }

    public void verifySharedArticleLoggedOutReceived(List<String> emailFriends) {
        for (int i = 0; i<emailFriends.size(); i++) {
            Reporter.log("Logged out test: Searching for Shared article to friend " + (i+1),true);
            doEmailSearch("from: QA TESTER to: " + emailFriends.get(i), emailTimeOut);
        }
    }

    public void verifySharedArticleLoggedInReceived(String emailClient, List<String> emailFriends) {
        for (int i = 0; i<emailFriends.size(); i++) {
            Reporter.log("Logged in test: Searching for Shared article to friend " + (i+1),true);
            doEmailSearch(String.format("from: %s to: %s subject: FW: UD |", emailClient, emailFriends.get(i)), emailTimeOut);
        }
    }

    /**
     * Check that invitation emails were received
     */
    public void verifyInvitationsUDEmailsReceived(List<String> friends) {
        for (int i = 0; i<friends.size(); i++) {
            Reporter.log("Searching for invitation email to friend " + (i+1),true);
            doEmailSearch("to: "+ friends.get(i)+" subject: You're Invited", emailTimeOut);
        }
    }

    /**
     * Check that the password reset email was received
     */
    public void verifyResetPasswordUDRequestReceivedandPasswordReset(String emailClient) {
        Reporter.log("Searching for password reset link",true);
        if (doEmailSearch("to: "+emailClient+" subject: UD | Password Reset Request", emailTimeOut)) {
            String link = getResetEmailLink();
            System.out.println(link);
        } else {
            Reporter.log("WARNING: Password Reset Email wasn't received in time.",true);
        }
    }

    /**
     * Check that the edit settings email is received
     *
     * @param emailClient Enter email to search with
     */
    public void verifyEditSettingsUDEmailReceived(String emailClient) {
        Reporter.log("Searching for account settings change mail",true);
        doEmailSearch("to: " + emailClient + " subject: You've Changed", emailTimeOut);
    }

    /**
     * Log out of Gmail
     */
    public void logoutGmail() {
        //checkEmailHelper_Client = new CheckEmailHelper_Client(client);
        //checkEmailHelper_Client.clientLogoutGmail();
        clientLogoutGmail();
    }

}
