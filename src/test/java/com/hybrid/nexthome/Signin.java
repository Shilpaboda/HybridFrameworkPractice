package com.hybrid.nexthome;

import com.hybrid.util.TestUtil;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Signin extends TestSuiteBase{
    static int count =-1;

    @BeforeTest
    public void checkTestCaseSkip(){
        if (!TestUtil.isTestCaseRunnable(nexthome, "Signin")){
            throw new SkipException("Runmode of the Signin test case is set as no, so Skipping Signin test case");
        }
        runmodes = TestUtil.getTestDataRunmodes(nexthome, "Signin");
    }

    @Test(dataProvider = "getData")
    public void signin(String uname, String pass){
        count++;
        if (!runmodes[count].equalsIgnoreCase("Yes")){
            throw new SkipException("Runmode of the test data is set as no, so skipping row number: "+count);
        }
        //Executing test case:
        System.out.println("Executing test case with : "+uname+" == "+pass);
        //webdriver code:
        openBrowser(CONFIG.getProperty("browsertype"));
        navigateurl(CONFIG.getProperty("baseurl"));
        getObject("logmyaccount").click();
        getObject("logemail").sendKeys(uname);
        getObject("logpassword").sendKeys(pass);
        getObject("logsigninButton").click();

        closeBrowser();

    }

    @DataProvider
    public Object[][] getData(){
        return TestUtil.getTestData(nexthome, "Signin");
    }

}
