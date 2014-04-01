package org.geno.com.common;

/**
 * Common strings used across all tests.
 *
 */
public interface UDBase {

    String UD_PROD_DOMAIN_BASE              = "www.geno.com";
    String UD_RELEASE_DOMAIN_BASE           = "geno-release.thedaddy.co";
    String UD_HOTFIX_DOMAIN_BASE            = "geno-hotfix.thedaddy.co";
    String UD_SCRATCH1_DOMAIN_BASE          = "geno-scratch1.thedaddy.co";
    String UD_SCRATCH2_DOMAIN_BASE          = "geno-scratch2.thedaddy.co";

    String UD_PROD_DOMAIN                   = "http://" + UD_PROD_DOMAIN_BASE;
    String UD_RELEASE_DOMAIN                = "http://" + UD_RELEASE_DOMAIN_BASE;
    String UD_HOTFIX_DOMAIN                 = "http://" + UD_HOTFIX_DOMAIN_BASE;
    String UD_SCRATCH1_DOMAIN               = "http://" + UD_SCRATCH1_DOMAIN_BASE;
    String UD_SCRATCH2_DOMAIN               = "http://" + UD_SCRATCH1_DOMAIN_BASE;

    String UD_RELEASE_ADMIN_DOMAIN          = UD_RELEASE_DOMAIN + "/admin.php";
    String UD_RELEASE_ADMIN_USERNAME        = "jenkins";
    String UD_RELEASE_ADMIN_PW              = "jenkins900!!";

    String UD_HOTFIX_ADMIN_DOMAIN           = UD_HOTFIX_DOMAIN + "/admin.php";
    String UD_HOTFIX_ADMIN_USERNAME         = "jenkins";
    String UD_HOTFIX_ADMIN_PW               = "jenkins900!!";
    
    String UD_SCRATCH1_ADMIN_DOMAIN         = UD_SCRATCH1_DOMAIN + "/admin.php";
    String UD_SCRATCH1_ADMIN_USERNAME       = "jenkins";
    String UD_SCRATCH1_ADMIN_PW             = "jenkins900!!";

    String UD_SCRATCH2_ADMIN_DOMAIN         = UD_SCRATCH2_DOMAIN + "/admin.php";
    String UD_SCRATCH2_ADMIN_USERNAME       = "jenkins";
    String UD_SCRATCH2_ADMIN_PW             = "jenkins900!!";


    String PERKS_PROD_DOMAIN_BASE           = "perks.geno.com";
    String PERKS_RELEASE_DOMAIN_BASE        = "perks-release.thedaddy.co";
    String PERKS_HOTFIX_DOMAIN_BASE         = "perks-hotfix.thedaddy.co";
    String PERKS_SCRATCH1_DOMAIN_BASE       = "perks-scratch1.thedaddy.co";
    String PERKS_SCRATCH2_DOMAIN_BASE       = "perks-scratch2.thedaddy.co";
    
    String PERKS_PROD_DOMAIN                = "http://" + PERKS_PROD_DOMAIN_BASE;
    String PERKS_RELEASE_DOMAIN             = "http://" + PERKS_RELEASE_DOMAIN_BASE;
    String PERKS_HOTFIX_DOMAIN              = "http://" + PERKS_HOTFIX_DOMAIN_BASE;
    String PERKS_SCRATCH1_DOMAIN            = "http://" + PERKS_SCRATCH1_DOMAIN_BASE;
    String PERKS_SCRATCH2_DOMAIN            = "http://" + PERKS_SCRATCH1_DOMAIN_BASE;

    String PERKS_RELEASE_ADMIN_DOMAIN       = "https://" + PERKS_RELEASE_DOMAIN_BASE + "/index.php/admin";
    String PERKS_RELEASE_ADMIN_USERNAME     = "jenkins";
    String PERKS_RELEASE_ADMIN_PW           = "muffin1";

    String PERKS_HOTFIX_ADMIN_DOMAIN        = "https://" + PERKS_HOTFIX_DOMAIN_BASE + "/index.php/admin";
    String PERKS_HOTFIX_ADMIN_USERNAME      = "jenkins";
    String PERKS_HOTFIX_ADMIN_PW            = "muffin1";

    String PERKS_SCRATCH1_ADMIN_DOMAIN      = "https://" + PERKS_SCRATCH1_DOMAIN_BASE + "/index.php/admin";
    String PERKS_SCRATCH1_ADMIN_USERNAME    = "jenkins";
    String PERKS_SCRATCH1_ADMIN_PW          = "muffin1";

    String PERKS_SCRATCH2_ADMIN_DOMAIN      = "https://" + PERKS_SCRATCH2_DOMAIN_BASE + "/index.php/admin";
    String PERKS_SCRATCH2_ADMIN_USERNAME    = "jenkins";
    String PERKS_SCRATCH2_ADMIN_PW          = "muffin1";


    String MANERO_PROD_DOMAIN_BASE          = "www.manero.com";
    String MANERO_RELEASE_DOMAIN_BASE       = "manero-release.thedaddy.co";
    String MANERO_HOTFIX_DOMAIN_BASE        = "manero-hotfix.thedaddy.co";
    String MANERO_SCRATCH1_DOMAIN_BASE      = "manero-scratch1.thedaddy.co";
    String MANERO_SCRATCH2_DOMAIN_BASE      = "manero-scratch2.thedaddy.co";

    String MANERO_PROD_DOMAIN               = "http://" + MANERO_PROD_DOMAIN_BASE;
    String MANERO_RELEASE_DOMAIN            = "http://" + MANERO_RELEASE_DOMAIN_BASE;
    String MANERO_HOTFIX_DOMAIN             = "http://" + MANERO_HOTFIX_DOMAIN_BASE;
    String MANERO_SCRATCH1_DOMAIN           = "http://" + MANERO_SCRATCH1_DOMAIN_BASE;
    String MANERO_SCRATCH2_DOMAIN           = "http://" + MANERO_SCRATCH2_DOMAIN_BASE;


    String DRIVEN_RELEASE_DOMAIN_BASE       = "driven-release.thedaddy.co";
    String DRIVEN_RELEASE_DOMAIN            = "http://" + DRIVEN_RELEASE_DOMAIN_BASE + "/";
    String DRIVEN_RELEASE_ADMIN_DOMAIN      = "http://driven-release.thedaddy.co";
    String DRIVEN_RELEASE_ADMIN_USERNAME    = "jenkins";
    String DRIVEN_RELEASE_ADMIN_PW          = "Naza#Poga4Feti";

    String DRIVEN_HOTFIX_DOMAIN_BASE        = "driven-hotfix.thedaddy.co";
    String DRIVEN_HOTFIX_DOMAIN             = "http://" + DRIVEN_HOTFIX_DOMAIN_BASE + "/";
    String DRIVEN_HOTFIX_ADMIN_DOMAIN       = "http://driven-hotfix.thedaddy.co";
    String DRIVEN_HOTFIX_ADMIN_USERNAME     = "jenkins";
    String DRIVEN_HOTFIX_ADMIN_PW           = "Naza#Poga4Feti";

    String DRIVEN_SCRATCH1_DOMAIN_BASE      = "driven-scratch1.thedaddy.co";
    String DRIVEN_SCRATCH1_DOMAIN           = "http://" + DRIVEN_SCRATCH1_DOMAIN_BASE + "/";
    String DRIVEN_SCRATCH1_ADMIN_DOMAIN     = "http://driven-scratch1.thedaddy.co";
    String DRIVEN_SCRATCH1_ADMIN_USERNAME   = "jenkins";
    String DRIVEN_SCRATCH1_ADMIN_PW         = "Naza#Poga4Feti";

    String DRIVEN_SCRATCH2_DOMAIN_BASE      = "driven-scratch2.thedaddy.co";
    String DRIVEN_SCRATCH2_DOMAIN           = "http://" + DRIVEN_SCRATCH2_DOMAIN_BASE + "/";
    String DRIVEN_SCRATCH2_ADMIN_DOMAIN     = "http://driven-scratch2.thedaddy.co";
    String DRIVEN_SCRATCH2_ADMIN_USERNAME   = "jenkins";
    String DRIVEN_SCRATCH2_ADMIN_PW         = "Naza#Poga4Feti";

    String DRIVEN_PROD_DOMAIN_BASE          = "driven.geno.com/";
    String DRIVEN_PROD_DOMAIN               = "http://" + DRIVEN_PROD_DOMAIN_BASE;


    String KEMPT_RELEASE_DOMAIN_BASE        = "getkempt-release.thedaddy.co";
    String KEMPT_RELEASE_DOMAIN             = "http://" + KEMPT_RELEASE_DOMAIN_BASE + "/";
    String KEMPT_RELEASE_ADMIN_DOMAIN       = KEMPT_RELEASE_DOMAIN;
    String KEMPT_RELEASE_ADMIN_USERNAME     = "jenkins";
    String KEMPT_RELEASE_ADMIN_PW           = "muffin";

    String KEMPT_HOTFIX_DOMAIN_BASE         = "getkempt-hotfix.thedaddy.co";
    String KEMPT_HOTFIX_DOMAIN              = "http://" + KEMPT_HOTFIX_DOMAIN_BASE + "/";
    String KEMPT_HOTFIX_ADMIN_DOMAIN        = KEMPT_HOTFIX_DOMAIN;
    String KEMPT_HOTFIX_ADMIN_USERNAME      = "jenkins";
    String KEMPT_HOTFIX_ADMIN_PW            = "muffin";

    String KEMPT_SCRATCH1_DOMAIN_BASE       = "getkempt-scratch1.thedaddy.co";
    String KEMPT_SCRATCH1_DOMAIN            = "http://" + KEMPT_SCRATCH1_DOMAIN_BASE + "/";
    String KEMPT_SCRATCH1_ADMIN_DOMAIN      = KEMPT_SCRATCH1_DOMAIN;
    String KEMPT_SCRATCH1_ADMIN_USERNAME    = "zsommerfield";
    String KEMPT_SCRATCH1_ADMIN_PW          = "muffin";

    String KEMPT_SCRATCH2_DOMAIN_BASE       = "getkempt-scratch1.thedaddy.co";
    String KEMPT_SCRATCH2_DOMAIN            = "http://" + KEMPT_SCRATCH2_DOMAIN_BASE + "/";
    String KEMPT_SCRATCH2_ADMIN_DOMAIN      = KEMPT_SCRATCH2_DOMAIN;
    String KEMPT_SCRATCH2_ADMIN_USERNAME    = "zsommerfield";
    String KEMPT_SCRATCH2_ADMIN_PW          = "muffin";

    String KEMPT_PROD_DOMAIN_BASE           = "www.getkempt.com";
    String KEMPT_PROD_DOMAIN                = "http://" + KEMPT_PROD_DOMAIN_BASE;


    String GOOGLE_EMAIL_LINK                = "https://mail.google.com";

    String EMAIL_USER_NAME                  = "qa.test";
    String EMAIL_DOMAIN                     = "@geno.com";
    String JENKINSEMAIL                     = EMAIL_USER_NAME + EMAIL_DOMAIN;
    String JENKINSEMAILPW                   = "commonud";

    String PASSWORD                         = "12345";
    String NEW_PASSWORD                     = "1234";

    String DATEFORMAT                       = "HHmmss";

    String CUR_DIR                          = System.getProperty("user.dir");
    String IMAGE_PATH                       = CUR_DIR + "/src/test/upload_data/";

    int GLOBAL_TIME_OUT                     = 30; // In Seconds
    int GLOBAL_POLLING                      = 3000; // In Milliseconds

    int PERKSADMIN_GLOBAL_TIME_OUT          = 90;

}
