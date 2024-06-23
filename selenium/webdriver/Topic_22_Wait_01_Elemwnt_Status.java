package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_01_Elemwnt_Status {
    WebDriver driver;

    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {

        //dieu kien 1: co trong HTMl cung co trong UI
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("nguyengiahan@gmail.com");
        sleepInSeconds(2);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        //Dieu kien 2: Emlement khong xuat hien tren UI va van co trong HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(2);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        //kiem tra 1 element khong hien thi
        //chay nhanh -> ket qua step nay Passed
        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_Not_In_DOM(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        //click vao icon Close de dong popup lai
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        //Dieu kien 3: Emlement khong xuat hien tren UI va khong co trong HTML
        //Tai thoi diem nay/step nay thi Confirm Email textbox dang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

    }

    @Test
    public void TC_03_Present() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("nguyengiahan@gmail.com");
        sleepInSeconds(2);

        //dieu kien 1
        //Tai thoi diem nay/step nay thi Confirm Email textbox presence(co trong HTML)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(2);
        //Dieu kien 2: Emlement khong xuat hien tren UI va van co trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));


    }

    @Test
    public void TC_04_Staleness() {
        //Staleness: dung cho TH truoc do co hien thi roi nhung hien tai khong muon hien thi
        //invisibitityOfElementLocated: khong quan tam truoc do co hien thi hay khong
        //Dieu kien 3: Emlement khong xuat hien tren UI va khong co trong HTML
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        //tai thoi diem nay element co xuat hien va se find element
        WebElement reconcofirmEmail = driver.findElement(reconfirmEmailTextbox);

        //click vao icon Close de dong popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        explicitWait.until(ExpectedConditions.stalenessOf(reconcofirmEmail));

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

