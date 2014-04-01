package org.geno.tests.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCasePerksSauce;

import java.util.List;

@Test (groups = {"Perks Admin"})
public class Perks_Admin_PerkMaintenance extends iTestCasePerksSauce {

    @Test
    public void createRegularPerk(){
        WebDriver client = getDriver();

        getSauceResultsUrl();

        List<WebElement> els;

        Reporter.log("Login to Perks Admin",true);
        adminPerksLogin();

        if (!client.findElements(By.id("message-popup-window")).isEmpty()) {
            client.findElement(By.id("message-popup-window")).findElement(By.xpath("/html/body/div/div[4]/div/a")).click();
        }

        // Click on manage products under Catalog--Get the URL and open that instead.
        client.get(client.findElement(By.xpath("/html/body/div/div/div[3]/ul/li[3]/ul/li/a")).getAttribute("href"));

        Reporter.log("Searching for Perks",true);
        adminSearchByPerkName("QA ");

        els = adminGetPerkList();

        Reporter.log("Deleting Perks",true);
        adminDeletePerks(els);

    }
}
