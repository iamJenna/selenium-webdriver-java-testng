package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebElement_Commands_01 {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //neu mong doi 1 element hien thi thi verifyTrue
        //neu mong doi 1 element khong hien thi thi verifyFalse
        if(driver.findElement(By.cssSelector("input#mail")).isDisplayed()){
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email Textbox is displayed");

        }else{
            System.out.println("Email Textbox is not displayed");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isDisplayed()){
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 Radio is displayed");

        }else{
            System.out.println("Under 18 Radio is not displayed");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education TextArea is displayed");

        }else{
            System.out.println("Education TextAre is not displayed");
        }

        if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            System.out.println("Name User5 is displayed");

        }else{
            System.out.println("Name User5 is not displayed");
        }


    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if(driver.findElement(By.cssSelector("input#mail")).isEnabled()){
            System.out.println("Email Textbox is enabled");

        }else{
            System.out.println("Email Textbox is disabled");
        }

        if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()){
            System.out.println("Password Textbox is enabled");

        }else{
            System.out.println("Password Textbox is disabled");
        }

        if(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()){
            System.out.println("Checkbox is enabled");

        }else{
            System.out.println("Checkbox is disabled");
        }
    }

    @Test
    public void TC_03_selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());

        driver.findElement(By.cssSelector("input#java")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());
    }

    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");

        //case 1 - number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());

        //case 2 - lower key
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());

        //case 3 - upper key
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());

        //case 4 - special chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("$%#&*");
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());

        //case 5 - max lenght
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345678");
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());

        //case 6 - Valid
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Auto123@#$");
        sleepInSeconds(3);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());

        //case 6 - empty
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());


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

