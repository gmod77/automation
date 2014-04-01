package org.geno.tests.sauce;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseUDSauce;

import java.util.ArrayList;
import java.util.List;


public class UD_Admin_Article_Three_ColumnResponsiveTest extends iTestCaseUDSauce {

    List<String> articleIds = new ArrayList<String>();

    @Test (groups = {"create"})
    public void createThreeColumnDedicatedResponsiveWideArticle(){
        getSauceResultsUrl();

        Reporter.log("Login to UD Admin",true);
        loginUDAdmin();

        Reporter.log("Set defaults for Three-column article", true);
        adminDefaults("Three-Column",1);

        articleIds.add(getArticleID());

        Reporter.log("Article is dedicated",true);
        adminArticleIsDedicated();

        Reporter.log("Select responsive layout",true);
        adminArticleSelectLayout(1);

        Reporter.log("Select wide width",true);
        adminArticleSelectLayoutWidth(1);

        Reporter.log("Finish remaining steps",true);
        createArticleThreeColumn();
    }

    @Test (groups = {"create"})
    public void createThreeColumnDedicatedResponsiveNarrowArticle(){
        getSauceResultsUrl();

        Reporter.log("Login to UD Admin",true);
        loginUDAdmin();

        Reporter.log("Set defaults for Three-column article", true);
        adminDefaults("Three-Column",2);

        articleIds.add(getArticleID());

        Reporter.log("Article is dedicated",true);
        adminArticleIsDedicated();

        Reporter.log("Select responsive layout",true);
        adminArticleSelectLayout(1);

        Reporter.log("Select narrow width",true);
        adminArticleSelectLayoutWidth(0);

        Reporter.log("Finish remaining steps",true);
        createArticleThreeColumn();
    }


    @Test (groups = {"create"})
    public void createThreeColumnResponsiveWideArticle(){
        getSauceResultsUrl();

        Reporter.log("Login to UD Admin",true);
        loginUDAdmin();

        Reporter.log("Set defaults for Three-column article", true);
        adminDefaults("Three-Column",3);

        articleIds.add(getArticleID());

        Reporter.log("Select responsive layout",true);
        adminArticleSelectLayout(1);

        Reporter.log("Select wide width",true);
        adminArticleSelectLayoutWidth(1);

        Reporter.log("Finish remaining steps",true);
        createArticleThreeColumn();
    }

    @Test (groups = {"create"})
    public void createThreeColumnResponsiveNarrowArticle(){
        getSauceResultsUrl();

        Reporter.log("Login to UD Admin",true);
        loginUDAdmin();

        Reporter.log("Set defaults for Three-column article", true);
        adminDefaults("Three-Column",4);

        articleIds.add(getArticleID());

        Reporter.log("Select responsive layout",true);
        adminArticleSelectLayout(1);

        Reporter.log("Select narrow width",true);
        adminArticleSelectLayoutWidth(0);

        Reporter.log("Finish remaining steps",true);
        createArticleThreeColumn();
    }

    @DataProvider (name = "articleIds")
    public Object[][] articleIdProvider() {
        Object[][] ret = new Object[articleIds.size()][];
        for (int i = 0; i<ret.length;i++){
            ret[i] = new Object[] {articleIds.get(i)};
        }

        return ret;
    }

    @Test (dependsOnGroups = {"create"},dataProvider = "articleIds")
    public void checkCreatedThreeColumnArticle(String articleId) {
        getSauceResultsUrl();

        Reporter.log("Login to UD Admin",true);
        loginUDAdmin();

        Reporter.log("Basic checks",true);
        checkThreeColumnArticleStep1(articleId);

        Reporter.log("Detailed checks",true);
        checkThreeColumnArticleStep2(articleId);

    }

}
