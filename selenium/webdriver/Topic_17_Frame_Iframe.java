package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_Iframe {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Form_site() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(5);

        //iframe element
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        //switch vao frame/iframe truoc khi thao tac voi cac element ben trong ->qua trang B
        driver.switchTo().frame(formIframe);

        //khong tim thay element (element nam trong iframe)
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(3);

        //switch ra lại trang A
        driver.switchTo().defaultContent();

        //thao tc voi 1 element ben ngoai iframe
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");

    }

    @Test
    public void TC_02_KynaEnglish() {
        driver.get("https://skills.kynaenglish.vn/");

        driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
        driver.findElement(By.cssSelector("div.button_bar")).click();
        sleepInSeconds(4);

        driver.findElement(By.cssSelector("input.input_name")).sendKeys("Jenna");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0283776546");
        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Dang ky khoa hoc Java");


    }


    @Test
    public void TC_03_Frame_HDFC_Bank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("frame[name='login_page']");
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("Jenna");
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("a.login-btn")).click();

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

