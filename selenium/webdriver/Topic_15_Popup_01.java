package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM_01() {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(2);

        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");

        //kiem tra login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("automation");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("automation");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),"Tài khoản không tồn tại!");

        //Close popup
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepInSeconds(2);

        //kiem tra login popup khong hien thi
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_02_Fixed_Popup_In_DOM_02() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        By loginPopup = By.cssSelector("div#k-popup-account-login-mb div.modal-content");

        //kiem tra login popup dang hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        System.out.println("Popup: " + driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("div.button-submit")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");

//        //Close popup
//        driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
//        sleepInSeconds(2);
//
//        //kiem tra login popup khong hien thi
//        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM(){
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        ///sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("picture.webpimg-container>img[alt='close-icon']")).click();

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(),"Mật khẩu không được để trống");

        //dong popup
        driver.findElement(By.cssSelector("img.close-img")).click();
        sleepInSeconds(2);

        //findelement(By) and assert zero length response instead
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);

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

