package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Popup_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Random_In_DOM(){
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

    @Test
    public void TC_02_Random_In_Dom() {
        driver.get("https://vnk.edu.vn/");

        sleepInSeconds(20);

        By marketingPopup = By.cssSelector("div.tve-leads-conversion-object");

        if(driver.findElement(marketingPopup).isDisplayed()){
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            sleepInSeconds(2);
            System.out.println("Popup hien thi");
        }else {
            System.out.println("Khong hien thi");
        }

        driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(),"Lịch Khai Giảng Tháng 06");

    }

    @Test
    public void TC_03_Random_Not_In_Dom() {
        driver.get("https://dehieu.vn/");

        By marketingPopup = By.cssSelector("button.close");

        if(driver.findElements(marketingPopup).size() > 0 && driver.findElements(marketingPopup).get(0).isDisplayed()){
            System.out.println("Popup hien thi");

            int heightBrowser = driver.manage().window().getSize().getHeight();
            System.out.println("Browser height: " + heightBrowser);
            if(heightBrowser < 1920){
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.cssSelector("button.close")));

            }else{
                driver.findElement(By.cssSelector("button#close-popup")).click();
            }
            sleepInSeconds(5);
        }

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

