package webdriver;


//import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.awt.*;
import java.time.Duration;
import java.util.Locale;

public class Topic_11_Button_Radio_Checkbox {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));

        //verify button bi disable khi chua click vao checkbox
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(3);

        //verify button da duoc enable sau khi click vao checkbox
        Assert.assertTrue(registerButton.isEnabled());

        //lay ma mau nen cua btn
        String registerBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println("Background Color RGB: " + registerBackgroundRGB);

        //convert tu kieu String(ma RGB) qua kiểu color
        Color registerButtonBackgroundColour = Color.fromString(registerBackgroundRGB);

        //convert qua kiểu hexa
        String registerBackgroundHexa = registerButtonBackgroundColour.asHex();
        System.out.println("Background color Hexa: " + registerBackgroundHexa);

        Assert.assertEquals(registerBackgroundHexa.toLowerCase(),"#ef5a00");

    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        //chuyển qua tab đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(3);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        //verify btn login is disabled
        Assert.assertFalse(loginButton.isEnabled());


        //verify btn login = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");

        //nhap email/pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("han@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(2);

        //verify btn login is enabled
        Assert.assertTrue(loginButton.isEnabled());

        //verify btn login = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");


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

