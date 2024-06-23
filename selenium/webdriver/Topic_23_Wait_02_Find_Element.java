package webdriver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_23_Wait_02_Find_Element {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        fluentWait = new FluentWait(driver);
//        fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Findelement() {

    }

    @Test
    public void TC_02_Findelements() {

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

