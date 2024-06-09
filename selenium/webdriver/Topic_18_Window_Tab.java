package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(2);

        //switch de qua trang gg
        switchToWIndowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q'")).sendKeys("Selenium");
        sleepInSeconds(2);

        //switch de quay lai
        switchToWIndowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(2);

        switchToWIndowByTitle("Facebook – log in or sign up" );

        closeallWindowWithoutParent(parentID);
        sleepInSeconds(2);
    }

    @Test
    public void TC_02_TechPanda() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product IPhone has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");


        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepInSeconds(2);
        switchToWIndowByTitle("Products Comparison List - Magento Commerce");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");

        switchToWIndowByTitle("Mobile");

        driver  .findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        sleepInSeconds(2);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void switchToWindowByID(String expectedID){
        //lay ra het tat ca tab/window ID
        Set<String> allIDs = driver.getWindowHandles();

        //dung vong lap duyet qua tung ID trong Set tren
        for(String id : allIDs){
            if(id.equals(expectedID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWIndowByTitle(String expectedTitle){
        //lay het tat ca ID cua cac w/t
        Set<String> allIDs = driver.getWindowHandles();

        //dung vong lap duyet qua Set tren
        for(String id : allIDs){
            //cho switch vao tung id truoc
            driver.switchTo().window(id);

            //lay ra title cua t/w hien tai
            String actualTitle = driver.getTitle();

            if(actualTitle.equals(expectedTitle)){
                break;
            }
        }
    }

    public void closeallWindowWithoutParent(String parentID){
        Set<String> allIDs = driver.getWindowHandles();

        for(String id : allIDs) {
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);

    }

    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

