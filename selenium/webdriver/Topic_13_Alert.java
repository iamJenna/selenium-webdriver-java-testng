package webdriver;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait expliciWait;
    By resultText = By.cssSelector("p#result");


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
//
//        Alert alert = driver.switchTo().alert();

        //cho alert persent
        //neu trong thi gian ch ma xuat hien thi tu switch vao
        //neu het time cho ma chua xuat hien thi fail
        Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
        sleepInSeconds(3);

        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Comfirm']")).click();
        Alert alert = expliciWait.until(ExpectedConditions.alertIsPresent());
        sleepInSeconds(3);

        Assert.assertEquals(alert.getText(),"I am a JS Comfirm");

        alert.dismiss();
        sleepInSeconds(3);

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

