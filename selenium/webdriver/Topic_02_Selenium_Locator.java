package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @Test
    public void TC_01_ID(){
        //Tim element co id la first name
        driver.findElement(By.id("FirstName")).sendKeys("Paulle");
    }
    @Test
    public void TC_02_Class(){
        driver.findElement(By.className("header-logo"));
    }
    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname(){
        driver.findElements(By.tagName("input"));
    }
    @Test
    public void TC_05_LinkkText(){
        //do chinh xac tuyet doi
        driver.findElement(By.linkText("Shipping & returns"));
    }
    @Test
    public void TC_06_Partial_LinkText(){
        //do chinh xac tuong doi khong cao
        driver.findElement(By.partialLinkText("vendor account"));
        driver.findElement(By.partialLinkText("for vendor account"));
        driver.findElement(By.partialLinkText("Apply for vendor account"));
    }
    @Test
    public void TC_07_Css(){
        //css voi ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        //css voi Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        //css voi name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        //css voi tagname
        driver.findElement(By.cssSelector("input"));

        //css voi link
        //driver.findElement(By.cssSelector("a[href='/custom/address']"));
    }
    @Test
    public void TC_08_XPath(){
        //Xpath voi ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //Xpath voi Class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        //Xpath voi name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //Xpath voi tagname
        driver.findElement(By.xpath("//input"));

        //Xpath voi link
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        //driver.findElement(By.xpath("//a[text()='Addresseses']"));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
