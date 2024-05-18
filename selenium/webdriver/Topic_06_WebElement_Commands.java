package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebElement_Commands {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element() {
        //tim chua tuong tac len
        driver.findElement(By.id(""));

        //tim va tuong tac len
        driver.findElement(By.id("")).click();
        //tim va luu vao 1 bien webelement
        WebElement fullnameTextbox = driver.findElement(By.id(""));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

