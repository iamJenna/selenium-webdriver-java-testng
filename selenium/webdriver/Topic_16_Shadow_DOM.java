package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom");
        sleepInSeconds(3);

        //shadowRootContext = dai dien cho cai shadow DOM 1 ben trong
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText,"some text");

        WebElement checkboxShadow = shadowRootContext.findElement(By.cssSelector("input[type='checkbox'"));
        Assert.assertFalse(checkboxShadow.isSelected());

        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());

        //nestedShadowHostElement = dai dien cho nested shadow DOM 2(nam trong Shadow DOM 1)
        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();

        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        Assert.assertEquals(nestedText,"nested text");
    }

    @Test
    public void TC_02_Shadow_DOM_Shopee() {
        driver.get("https://shopee.vn/");

        WebElement shadowHostElement = driver.findElement(By.cssSelector(""))
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

