package com.hybrid.nexthome;

import com.hybrid.util.TestUtil;
import org.junit.Before;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Registration extends TestSuiteBase{
    static  int count=-1;
    @BeforeTest
    public void checkTestcaseSkip(){
        if (!TestUtil.isTestCaseRunnable(nexthome, "Registration")){
            throw new SkipException("Runmode of the Registration test case is set as no, so skipping Registration test case");
        }
        runmodes = TestUtil.getTestDataRunmodes(nexthome, "Registration");
    }

    //Test case execution:
    @Test(dataProvider = "getData")
    public void registration(String title, String firstname, String lastname, String  email, String password, String dob,
                             String contacttelephone, String housenumber, String postcode){
        count++;
        if (!runmodes[count].equalsIgnoreCase("yes")){
            throw new SkipException("Runmode of the test data is set as no, so skipping row number: "+count);
        }
        //Executing test cases:
        System.out.println("Executing test case with: "+title+" == "+firstname+" == "+lastname+" == "+email+" == "+password+" == "+dob
                +" == "+contacttelephone+" == "+housenumber+" == "+postcode);
        //webdriver code:
        openBrowser(CONFIG.getProperty("browsertype"));
        navigateurl(CONFIG.getProperty("baseurl"));
        getObject("logmyaccount").click();
        getObject("regRegistrationNow").click();
        getObject("regTitle").sendKeys(title);
        getObject("regFirstName").sendKeys(firstname);
        getObject("regLastName").sendKeys(lastname);
        getObject("regEmail").sendKeys(email);
        getObject("regPassword").sendKeys(password);

        getObject("regDoB").sendKeys(password);
        getObject("regContactName").sendKeys(password);
        getObject("regHouseNo").sendKeys(password);
        getObject("regPostcode").sendKeys(password);
        getObject("regSearchPostcode").sendKeys(password);
        getObject("regGreatRegisterMyaccount").sendKeys(password);

        closeBrowser();
    }

    //to get the test data from test case:
    @DataProvider
    public Object[][] getData(){
        return TestUtil.getTestData(nexthome, "Registration");
    }


}
