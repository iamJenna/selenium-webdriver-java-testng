package webdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Wait_05_Emplicit_01_Knowledge {
    WebDriver driver;
    WebDriverWait emplicitWait;
    

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_() {

    }

    @Test
    public void TC_02_() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

