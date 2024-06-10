package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_New_Driver {
    WebDriver userDriver;
    WebDriver adminDriver;
    String firstName = "Jenna", lastName =  "Nguyen", emailAddress = "nguyengiahan147@gmail.com";
    String companyName = "Selenium WebDriver", password = "123456";
    String day = "16", month = "December", year = "2003";

    @BeforeClass
    public void beforeClass() {

        userDriver = new FirefoxDriver();
        userDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        adminDriver = new FirefoxDriver();
        adminDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }

    @Test
    public void TC_01_() {
        //user
        userDriver.get("https://demo.nopcommerce.com/");
        userDriver.findElement(By.cssSelector("a.ico-register")).click();

        userDriver.findElement(By.id("FirstName")).sendKeys(firstName);
        userDriver.findElement(By.id("LastName")).sendKeys(lastName);

        //day dropdown
        Select dayDropDown = new Select(userDriver.findElement(By.name("DateOfBirthDay")));

        //chon ngay
        dayDropDown.selectByVisibleText(day);

        //verify dropdown nay la single(kp multiple)
        Assert.assertFalse(dayDropDown.isMultiple());

        //verify so luong item trong dropdown nay la 32 item
        Assert.assertEquals(dayDropDown.getOptions().size(),32);

        new Select(userDriver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(userDriver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        userDriver.findElement(By.id("Email")).sendKeys(emailAddress);
        userDriver.findElement(By.id("Company")).sendKeys(companyName);
        userDriver.findElement(By.id("Password")).sendKeys(password);
        userDriver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        userDriver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(2);

        Assert.assertEquals(userDriver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        //admin
        adminDriver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

        adminDriver.findElement(By.cssSelector("input#Email")).clear();
        adminDriver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");

        adminDriver.findElement(By.cssSelector("input#Password")).clear();
        adminDriver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

        adminDriver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(4);

        //check...

        //user
        userDriver.get("https://demo.nopcommerce.com/");

        userDriver.findElement(By.cssSelector("a.ico-logout")).click();


        //login
        userDriver.findElement(By.cssSelector("a.ico-login")).click();
        userDriver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        userDriver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        userDriver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(5);

    }

    @Test
    public void TC_02_() {

    }


    @AfterClass
    public void afterClass() {
        userDriver.quit();
        adminDriver.quit();
    }
    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

