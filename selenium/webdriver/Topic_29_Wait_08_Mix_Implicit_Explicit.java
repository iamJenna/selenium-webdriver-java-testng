package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_29_Wait_08_Mix_Implicit_Explicit {
    WebDriver driver;

    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Only_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        //khi vào tìm element thì tìm thây ngay
        //khong can cho het timeout
        driver.findElement(By.cssSelector("inpt#email"));


    }

    @Test
    public void TC_02_Only_Implicit_Not_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        //khi vào tìm element thì tìm thây ngay
        //polling moi nua giay tim lai 1 lan
        //khi het timeout se danh fail testcase va throe exception:NoSuchElementException
        driver.findElement(By.cssSelector("inpt#automation"));

    }

    @Test
    public void TC_03_Only_Explicit_Not_Found() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }

    @Test
    public void TC_04_Only_Explicit_Not_Found() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //khi vào tìm element thì tìm thây ngay
        //polling moi nua giay tim lai 1 lan
        //khi het timeout se danh fail testcase va throe exception:TimeOutException
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));

    }

    @Test
    public void TC_05_Mix_Implicit_Explicit() {
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("input#email"));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

