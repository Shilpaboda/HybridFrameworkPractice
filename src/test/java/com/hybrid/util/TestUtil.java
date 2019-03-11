package com.hybrid.util;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class TestUtil {

    public static void main(String[] args) throws IOException {
       // XLS_Reader xls = new XLS_Reader("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/testdata/nexthome.xlsx");
        //System.out.println(isSuiteRunnable(xls, "WOmen"));
        //System.out.println(isTestCaseRunnable(xls, "Registration"));
        //System.out.println(getTestData(xls, "Registration"));
        //getTestDataRunmodes(xls, "Signin");
        //how to read Properties files:
        Properties OR = new Properties();
        FileInputStream ip = new FileInputStream("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/config/OR.properties");
        OR.load(ip);

        System.out.println(OR.getProperty("password"));

        Properties CONFIG = new Properties();
        FileInputStream cp = new FileInputStream("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/config/config.properties");
        CONFIG.load(cp);

        System.out.println(CONFIG.getProperty("baseurl"));
        System.out.println(CONFIG.getProperty("browsertype"));

    }


    /*
     * 1. Check the suite is runnnable or not
     * 2. Check the test case is runnable or not
     * 3. Get Test data runmodes
     * 4. Get TestData from the perticular test case
     */


    //To check the runmode of the projectsuite - suite:
    public static boolean isSuiteRunnable(XLS_Reader xls, String suiteName){
        boolean isExecutable=false;
        for(int i=2;i<=xls.getRowCount("Testsuites");i++){
            String suite = xls.getCellData("Testsuites", "TSID",i);
            String runmode = xls.getCellData("Testsuites","Runmode",i);
            System.out.println(suite+"  == "+runmode);
            if (suite.equalsIgnoreCase(suiteName)){
                if (runmode.equalsIgnoreCase("yes")){
                    isExecutable=true;
                }
            }
        }
        return isExecutable;
    }

    //To check the runmode of the test case:
    public static boolean isTestCaseRunnable(XLS_Reader xls, String testcaseName){
        boolean isExecutable = false;
        for (int i=2;i<=xls.getRowCount("Testcases");i++){
            String testcase = xls.getCellData("Testcases", "TCID", i);
            String runmode = xls.getCellData("Testcases", "Runmode", i);
            System.out.println(testcase+" == "+runmode);
            if(testcase.equalsIgnoreCase(testcaseName)){
                if (runmode.equalsIgnoreCase("yes")){
                    isExecutable=true;
                }
            }
        }
        return isExecutable;
    }


    //to get the test data from particular test case particular excel file.
    public static Object[][] getTestData(XLS_Reader xls, String sheetName){

        //to get the total rows and columns:
        int rows = xls.getRowCount(sheetName);
        int cols = xls.getColumnCount(sheetName);

        System.out.println("Total Rows: "+rows);
        System.out.println("Total Columns: "+cols);

        //declare two dimentional Object array:
        //declare array size:
        Object[][] data = new Object[rows-1][cols-1];

        //Array values declaration:
        for (int rowNum = 2; rowNum<=rows; rowNum++){
            for (int colNum =0; colNum<cols-1;colNum++){
                System.out.print(xls.getCellData(sheetName, colNum, rowNum)+" | ");
                data[rowNum-2][colNum] = xls.getCellData(sheetName, colNum, rowNum);
            }
            System.out.println();
        }
        return data;
    }

    //to get the runmodes of the testdata:
    public static String[] getTestDataRunmodes(XLS_Reader xls, String sheetName){
        if(!xls.isSheetExist(sheetName)){
            System.out.println("Test data sheet not found");
        }
        //Declare String array to store runmodes:
        String[] runmodes = new String[xls.getRowCount(sheetName)-1];  //size declaration
        for (int i=2;i<=xls.getRowCount(sheetName);i++){
            System.out.println(xls.getCellData(sheetName, "Runmode", i));
            //array values declaration:
            runmodes[i-2] = xls.getCellData(sheetName, "Runmode", i);
        }
        return runmodes;
    }


}
