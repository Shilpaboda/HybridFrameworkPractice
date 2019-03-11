package com.hybrid.nexthome;

import com.hybrid.base.TestBase;
import com.hybrid.util.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestSuiteBase extends TestBase {
    @BeforeSuite
    public void checkSuiteSkip() throws IOException {
        Initialize();
        if (!TestUtil.isSuiteRunnable(projectSuite, "nexthome")){
            throw new SkipException("Runmode of the nexthome suite is set as no, so Skipping nexthome suite");
        }
    }
}