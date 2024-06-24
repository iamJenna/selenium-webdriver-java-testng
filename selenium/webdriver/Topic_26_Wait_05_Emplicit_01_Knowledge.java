package webdriver;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic_26_Wait_05_Emplicit_01_Knowledge {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Method() {
        //Cho cho 1 alert presence trong html/dom truoc khi thao tac len
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        //Cho cho alement khong con trong dom
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Cho cho element co trong dom (khong quan tam co tren UI khong)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //cho cho 1 list element co trong dom
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector(""), By.cssSelector("")));

        //Cho 1-n element duoc hien thi tren UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));

        //cho cho 1 element duoc phep click: link/btn/checkbox,radio,...
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //cho cho page hien tai co title nhu mong doi
        explicitWait.until(ExpectedConditions.titleIs(""));
        driver.getTitle();

        //Ket hop nhieu dieu kien - 2 dk dung
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        //Ket hopw nhieu dieu kien - 1 trong 2 dk dung
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        //Cho 1 element co attribute chua gia tri mong doi (tuong doi)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"",""));

        //CHo cho 1 element co attribute co gia tri mong doi (tuyet doi)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"",""));

        //cho cho 1 element co attribute khac null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),""));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),"",""));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),"","" ));

        //cho cho 1 element duoc selected thanh cong
        //checkbox/radio/dropdown Item(Default)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //cho cho 1 element duoc selected roi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));

        //cho cho 1 element chua duoc selected
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false));

        //cho cho 1 frame/iframe duoc avaiable va switch qua
        //name or ID
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

        //Index
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));

        //By or Element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));

        //cho cho 1 element bien mat (khong hien thi tren UI)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //cho cho 1 doan code JS can tra ve gia tri
        explicitWait.until(ExpectedConditions.jsReturnsValue(""));

        //cho cho 1 doan code JS duoc thuc thi khong nem ra ngoai le nao het
        //khong nem ra:true
        //co ngoai le:false
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions(""));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("")));

        //Cho so luong element bang 1 so co dinh
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""),7));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""),7));

        //cho cho window/tab la bao nhieu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),"Mobile"));

        Pattern pattern = Pattern.compile("This is root of mobile",Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""),pattern));

        //bat buoc text nay co trong dom/html
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""),""));
        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));

        //cho cho 1 dk ma element nay bi update trang thai - load lai html
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(" "))));

    }

    @Test
    public void TC_02_() {

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

