package com.hybrid.men; //suite

import com.hybrid.util.TestUtil;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Suits extends TestSuiteBase{
    static int count=-1;
    @BeforeTest
    public void checkTestcaseSkip(){
        if (!TestUtil.isTestCaseRunnable(men, "Suits")){
            throw new SkipException("Runmode of the Suits test case is set as no, so Skipping Suits test case");
        }
        //if runmode is yes it'll get the test data runmodes:
        runmodes = TestUtil.getTestDataRunmodes(men, "Suits");
    }

    @Test(dataProvider = "getData")
    public void suits(String sortBy,String products, String Category, String colour, String size, String fit, String use,
                      String DesignFeatures, String brand ) throws InterruptedException {
        count++;
        if (!runmodes[count].equalsIgnoreCase("Yes")){
            throw new SkipException("Runmode of the test data is set as no, so Skipping row number: "+count);
        }
        //Executing test case:
        System.out.println("Executing test case with: "+sortBy+" == "+products+" == "+" == "+Category+" == "+colour+" == "+size+
                " == "+fit+" == "+use+" == "+DesignFeatures+" == "+brand);
        //WebDriver code:
        openBrowser(CONFIG.getProperty("browsertype"));
        navigateurl(CONFIG.getProperty("baseurl"));
        mousehover(getObject("menmenu"));
        getObject("suits").click();
       // getObject("suitscategory").click();
       // getObject("suitscolour").click();
        getObject("suitssize").click();
        getObject("suitsFit").click();
        getObject("suitsUse").click();
        getObject("suitsDesign").click();
        getObject("suitsmadewith").click();
        getObject("suitsBrand").click();


        //closeBrowser();
    }

    @DataProvider
    public Object[][] getData(){
        return TestUtil.getTestData(men, "Suits");
    }

}
