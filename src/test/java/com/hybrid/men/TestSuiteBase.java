package com.hybrid.men;

import com.hybrid.base.TestBase;
import com.hybrid.util.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;

//to check the suite is run
public class TestSuiteBase extends TestBase{
    //to the check the runmode of the test Suite:
    //@BeforeSuite //check runmode yes or no

/**
 * @BeforeSuite-check the suite runmode
 * @BeforeTest-check the testcase runmode
 * @Beforeclass-openbrowser-one time browser open
 * @Beforemethod-openbrowser method-multiple times browser opens
 * @Test
 */

    @BeforeSuite
    public void checkSuiteSkip() throws IOException {
        Initialize();
        if (!TestUtil.isSuiteRunnable(projectSuite, "men")){
            //if runmode is no, then it'll throw the below message
            throw  new SkipException("Runmode of the men suite is set as no, so Skipping men suite");
        }
        //if runmode is yes it'll execute the test case:
    }

}
