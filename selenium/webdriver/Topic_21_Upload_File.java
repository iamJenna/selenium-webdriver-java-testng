package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_21_Upload_File {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String luuName = "luu.jpg";
    String nhoName = "nho.jpg";
    String emName = "em.jpg";

    String luuFilePath = projectPath + File.separator + "\\uploadFile\\"+ File.separator + luuName;
    String nhoFilePath = projectPath + File.separator + "\\uploadFile\\"+ File.separator + nhoName;
    String emFilePath = projectPath + File.separator + "\\uploadFile\\"+ File.separator + emName;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadBy).sendKeys(luuFilePath);
        sleepInSeconds(2);

        driver.findElement(uploadBy).sendKeys(nhoFilePath);
        sleepInSeconds(2);

        driver.findElement(uploadBy).sendKeys(emFilePath);
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + luuName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nhoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + emName + "']")).isDisplayed());


        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for(WebElement button : startButtons){
            button.click();
            sleepInSeconds(3);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name'/a[@title='" + luuName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name'/a[@title='" + nhoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name'/a[@title='" + emName + "']")).isDisplayed());


    }



    @Test //2 file tro len
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadBy).sendKeys(luuFilePath +  "\n" + nhoFilePath + "\n" + emName);
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + luuName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + nhoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + emName + "']")).isDisplayed());


        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for(WebElement button : startButtons){
            button.click();
            sleepInSeconds(3);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name'/a[@title='" + luuName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name'/a[@title='" + nhoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name'/a[@title='" + emName + "']")).isDisplayed());


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

