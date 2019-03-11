package com.hybrid.men;

import com.hybrid.util.TestUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CasualShirts extends TestSuiteBase{
    static int count=-1;
    @BeforeTest
    public void checkTestCaseSkip(){
        if(!TestUtil.isTestCaseRunnable(men, "CasualShirts")){
            throw new SkipException("Runmode of the CasualShirts test case is set as no, so skipping CasualShirts test case");
        }
        runmodes = TestUtil.getTestDataRunmodes(men, "CasualShirts");
    }

    //Test case Execution:
    @Test(dataProvider = "getData")
    public void casualShirts(String sortBy, String products, String departments, String category, String colour, String size,
                             String fit, String use, String designFeatures, String benefit, String brand, String sleeve) throws InterruptedException, IOException {
        count++;
        //verifying test data runmodes:
        if (!runmodes[count].equalsIgnoreCase("Yes")){
            throw new SkipException("Runmode of the test data is set as no, so Skipping rownumber: "+count);
        }
        //Executing test case:
        System.out.println("Executing test case with: "+sortBy+" == "+products+" == "+departments+" == "+category+" == "+colour+" == "+size+" == "+
                fit+" == "+use+" == "+designFeatures+" == "+benefit+" == "+brand+" == "+sleeve);
        //webdriver code:
        openBrowser(CONFIG.getProperty("browsertype"));
        navigateurl(CONFIG.getProperty("baseurl"));
        Thread.sleep(3000);

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/screenshots/CasualShirts"+(count+1)+".jpg"));

        mousehover(getObject("menmenu"));

        getObject("menCaualShirts");

        selectFromList("menColor","blue");
        selectFromList("menSize","small");
        selectFromList("menFit","slimfit");

        //closeBrowser();


    }

    @DataProvider
    public Object[][] getData(){
        return TestUtil.getTestData(men, "CasualShirts");
    }

}
