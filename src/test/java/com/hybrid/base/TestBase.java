package com.hybrid.base;

import com.hybrid.util.XLS_Reader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class TestBase {
    //to declare all the global variables n methods
    public static boolean isInitialized=false;
    public static XLS_Reader projectSuite, nexthome, homefurniture, men, women;
    public static Properties OR, CONFIG;
    public static FileInputStream ip, cp;
    public static String[] runmodes;

    public WebDriver driver;
//Creating initialize functions to load all the property files and excel files.

    public static void Initialize() throws IOException {
        if(!isInitialized){
            OR = new Properties();
            ip = new FileInputStream("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/config/OR.properties");
            OR.load(ip);

            CONFIG = new Properties();
            cp = new FileInputStream("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/config/config.properties");
            CONFIG.load(cp);

            projectSuite = new XLS_Reader("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/testdata/projectsuite.xlsx");

            men = new XLS_Reader("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/testdata/men.xlsx");
            nexthome = new XLS_Reader("/Users/shilpa/IdeaProjects/HybridFrameworkPractice/src/test/java/com/hybrid/testdata/nexthome.xlsx");

        }
    }


    public void openBrowser(String browser){
        if (browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "/Users/shilpa/IdeaProjects/HybridFrameworkPractice/resources/geckodriver");
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "/Users/shilpa/IdeaProjects/HybridFrameworkPractice/resources/chromedriver");
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver", "/Users/shilpa/IdeaProjects/HybridFrameworkPractice/resources/");
            driver = new InternetExplorerDriver();
        }
    }

    public void navigateurl(String url){
       driver.get(url);
    }

    public void closeBrowser(){
        driver.quit();
    }

    public WebElement getObject(String xpathkey){
        return driver.findElement(By.xpath(OR.getProperty(xpathkey)));
    }

    public void mousehover(WebElement element) throws InterruptedException {
        new Actions(driver).moveToElement(element).build().perform();
        Thread.sleep(3000);
    }

    public WebElement selectFromList(String xpathkey, String text){
        WebElement element=null;
        List<WebElement> options = driver.findElements(By.xpath(OR.getProperty(xpathkey)));
        for(int i=0;i<options.size();i++){
            if (text.equalsIgnoreCase(options.get(i).getText())){
                element= options.get(i);
                break;
            }
        }
        return element;

    }

}
