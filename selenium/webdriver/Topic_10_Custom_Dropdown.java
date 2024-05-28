package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.rmi.server.ExportException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //ngam dinh khong ro rang cho 1 trang thai cu the cua element nao het
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    //@Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Slow");
        sleepInSeconds(3);

        selectItemInDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        sleepInSeconds(3);

        selectItemInDropdown("span#number-button", "ul#number-menu div", "15");
        sleepInSeconds(3);

        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutaion-button>span.ui-selectmenu-text")).getText(), "Dr.");

    }

    //@Test
    public void TC_02_React() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        selectItemInDropdown("i.dropdown.icon","div.item>span.text","Christian");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");

    }

    //@Test
    public void TC_03_VueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Christian");

    }

    @Test
    public void TC_04_Editable(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInDropdown("ïnput.search","div.item span","Angeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");

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

    //nhung du lieu truyen vo se duoc xem la tham so
    public void selectItemInDropdown(String parentCss, String childCss, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCss)).click();//"span#number-button"
        sleepInSeconds(1);

        // visible: nhin thay duoc/thao tac duoc co tren UI hoac HTML
        //presence: nhin thay/khong thay cung thoa man dieu kien(co the khong co tren UI nhung bat buoc co tren HTML

        //xuat hien day du trong html

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for (WebElement item : allItems){
            String textItem = item.getText();

            if (textItem.equals(itemTextExpected)){
                item.click();
                break;
            }
        }

    }

    public void selectItemInEditableDropdown(String parentCss, String childCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);

        sleepInSeconds(1);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for (WebElement item : allItems){
            String textItem = item.getText();

            if (textItem.equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }
}

