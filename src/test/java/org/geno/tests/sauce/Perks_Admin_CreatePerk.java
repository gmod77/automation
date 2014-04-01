package org.geno.tests.sauce;

import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCasePerksSauce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Possible parameters:
// National
// New York
// Chicago
// Miami
// Boston
// Washington DC
// Los Angeles
// San Francisco

@Test (groups = {"Perks","Perks Admin"})
public class Perks_Admin_CreatePerk extends iTestCasePerksSauce {

    private List<String> perkNames;

    private Perks_Admin_CreatePerk() {
        perkNames = new ArrayList<String>();
    }

    private List<String> cities = Arrays.asList("National", "New York", "Chicago", "Miami", "Boston", "Washington DC", "Los Angeles", "San Francisco");


    @Parameters ({"city"})
    @Test (groups = {"Perk Create","ForGrouped"})
    public void createRegularPerk(@Optional("all") String city){
        getSauceResultsUrl();

        String perkType = "Simple Product";

        Reporter.log("THE CITY> " + city,true);

        setR(generateDate("DDDHHmmSSS"));
        perkNames.add(getR());

        Reporter.log("Login to Perks Admin",true);
        adminPerksLogin();

        Reporter.log("Create Perk",true);
        adminCreatePerk(perkType);

        Reporter.log("Filling out General Tab", true);
        adminCreatePerkGeneral(perkType);

        Reporter.log("Filling out Dashboard Options",true);
        adminCreatePerkDashboard();

        Reporter.log("Filling out Inventory Counters",true);
        adminCreatePerkInventoryCounters();

        Reporter.log("Filling out perk Price Tab",true);
        adminCreatePerkPrice();

        Reporter.log("Filling out perk Inventory Tab",true);
        adminCreatePerkInventory();

        Reporter.log("Filling out Website Tab",true);
        adminCreatePerkWebSite();

        Reporter.log("Filling out Perk Reporting Tab",true);
        adminCreatePerkReporting();

        Reporter.log("Filling out Perk Categories Tab",true);
        adminCreatePerkCategories();
        if (city.equals("all")) {
            for (String c : cities) {
                selectProductCategory(c);
            }
        } else {
            selectProductCategory(city);
        }

        Reporter.log("Selecting random perk size", true);
        adminCreatePerkModuleSize();

        Reporter.log("Saving perk",true);
        adminCreatePerkSave();

        Reporter.log("Make Perk visible", true);
        getManageCategories();

        if (city.equals("all")) {
            for (String c : cities) {
                makePerkVisible(c);
            }
        } else {
                makePerkVisible(city);
        }

    }

    @Parameters ({"city"})
    @Test (groups = {"Perk Create","ForGrouped"})
    public void createDownloadablePerk(@Optional("all") String city){
        getSauceResultsUrl();

        String perkType = "Downloadable Product";

        setR(generateDate("DDDHHmmSSS"));
        perkNames.add(getR());

        Reporter.log("Login to Perks Admin",true);
        adminPerksLogin();

        Reporter.log("Create Perk",true);
        adminCreatePerk(perkType);

        Reporter.log("Filling out General Tab", true);
        adminCreatePerkGeneral(perkType);

        Reporter.log("Setting Downloadable type", true);
        adminSetDownloadableInfo();

        Reporter.log("Filling out Dashboard Options",true);
        adminCreatePerkDashboard();

        Reporter.log("Filling out Inventory Counters",true);
        adminCreatePerkInventoryCounters();

        Reporter.log("Filling out perk Price Tab",true);
        adminCreatePerkPrice();

        Reporter.log("Filling out perk Inventory Tab",true);
        adminCreatePerkInventory();

        Reporter.log("Filling out Website Tab",true);
        adminCreatePerkWebSite();

        Reporter.log("Filling out Perk Categories Tab",true);
        adminCreatePerkCategories();
        if (city.equals("all")) {
            for (String c : cities) {
                selectProductCategory(c);
            }
        } else {
            selectProductCategory(city);
        }

        Reporter.log("Filling out Perk Reporting Tab",true);
        adminCreatePerkReporting();

        Reporter.log("Selecting random perk size", true);
        adminCreatePerkModuleSize();

        Reporter.log("Saving perk",true);
        adminCreatePerkSave();

        Reporter.log("Make Perk visible", true);
        getManageCategories();

        if (city.equals("all")) {
            for (String c : cities) {
                makePerkVisible(c);
            }
        } else {
            makePerkVisible(city);
        }
    }

    @Parameters ({"city"})
    @Test (groups = {"Perk Create"}, dependsOnGroups = {"ForGrouped"})
    public void createGroupedPerk(@Optional("all") String city){
        getSauceResultsUrl();

        String perkType = "Grouped Product";

        setR(generateDate("DDDHHmmSSS"));
        Reporter.log(getR(),true);

        Reporter.log("Login to Perks Admin",true);
        adminPerksLogin();

        Reporter.log("Create Perk",true);
        adminCreatePerk(perkType);

        Reporter.log("Filling out General Tab",true);
        adminCreatePerkGeneral(perkType);

        Reporter.log("Selecting associated products",true);
        adminCreatePerkAssociatedProducts(perkNames);

        Reporter.log("Filling out Inventory Counters",true);
        adminCreatePerkInventoryCounters();

        Reporter.log("Filling out perk Inventory Tab",true);
        adminCreatePerkInventory();

        Reporter.log("Filling out Website Tab",true);
        adminCreatePerkWebSite();

        Reporter.log("Filling out Perk Categories Tab",true);
        adminCreatePerkCategories();
        if (city.equals("all")) {
            for (String c : cities) {
                selectProductCategory(c);
            }
        } else {
            selectProductCategory(city);
        }

        Reporter.log("Filling out Perk Reporting Tab",true);
        adminCreatePerkReporting();

        Reporter.log("Selecting random perk size", true);
        adminCreatePerkModuleSize();

        Reporter.log("Saving perk",true);
        adminCreatePerkSave();

        Reporter.log("Make Perk visible", true);
        getManageCategories();

        if (city.equals("all")) {
            for (String c : cities) {
                makePerkVisible(c);
            }
        } else {
            makePerkVisible(city);
        }
    }

}
