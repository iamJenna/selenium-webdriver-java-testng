package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.text.Element;
import java.time.Duration;
import java.util.List;

public class Topic_12_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    //@Test
    public void TC_01_Default_Telerik_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/child::input");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/child::input");

        //case 1: neu mo app ma checkbox da duoc chon
        checkToElement(rearSideCheckbox);

        //case 2: neu mo app ma check box ch duoc chon
        checkToElement(dualZoneCheckbox);

        //verify checkbox da duoc chon chua
        Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        //bo chon 2 checkbox nay
        uncheckToElement(rearSideCheckbox);
        uncheckToElement(dualZoneCheckbox);

        //verify checkbox da duoc bo chon chua
        Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());



    }

    //@Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/child::input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/child::input");

        //click chon 1 trong 2
        checkToElement(twoPetrolRadio);
        //verify
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);
        //verify
        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
    }

    //@Test
    public void  TC_03_Select_All_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //chon het tat ca cac checkbox trong man hinh do
        for (WebElement checkbox : allCheckboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
                //sleepInSeconds(1);
            }
        }

        //verify het tat ca cac checkbox
        for (WebElement checkbox : allCheckboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        //chon 1 checkbox/radio nao do trong tat ca
        for(WebElement checkbox : allCheckboxes){
            if (checkbox.getAttribute("value").equals("Heart Attach") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSeconds(5);
            }
        }

        //verify het tat ca cac checkbox
        for (WebElement checkbox : allCheckboxes){
            if (checkbox.getAttribute("value").equals("Heart Attach")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }


    @Test
    public void TC_04_Custom_Radio(){

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void checkToElement(By byXpath){
        //neu element chua duoc chon thi moi click
        if(!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(3);
        }
    }

    //neu element duoc chon thi  click lan nua cho no bo chon
    public void uncheckToElement(By byXpath){
        if(driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(3);
        }
    }

    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

