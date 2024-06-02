package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;
    String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    JavascriptExecutor javascriptExecutor;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltips() {

    }

    //@Test
    public void TC_02_Hover_Menu_Login() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
    }

    //@Test
    public void TC_03_Hover_Menu_Login() {

    }

    @Test
    public void TC_05_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        //Can scroll toi element roi moi double click - chi rieng firefox
        if (driver.toString().contains("firefox")) {
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
            sleepInSeconds(3);
        } else {
            actions.scrollToElement(doubleClickButton).perform();
            sleepInSeconds(3);
        }
        actions.doubleClick(doubleClickButton).perform();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_06_RightClick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        //Chua click chuot phai thi no se khong hien thi (invisible)
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSeconds(2);

        //Moi click chuot phai len - cac element dc visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);

        //duoc cap nhat lai class cua element nay - kiem tra su kien hover thanh cong
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

        //Click len paste
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);

        driver.switchTo().alert().accept();
        sleepInSeconds(3);

        //kiem tra paste khong con hien thi
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
    }

    @Test
    public void TC_07_DragDropHTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(smallCircle, bigCircle).perform();
        sleepInSeconds(2);

        Assert.assertEquals(bigCircle.getText(), "You did great!");

        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");

    }

    @Test
    public void TC_08_DragDropHTML5_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));

        String projectPath = System.getProperty("user.dir");

        String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";

        String jsContentFile = getContentFile(dragAndDropFilePath);

        jsContentFile = jsContentFile + "$('div#column-a').simulateDragDrop({dropTarget:'div#column-b'});";

        //thuc thi doan len JS
        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(5);//tu A -> B

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(5);//tu B -> A

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");


    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}