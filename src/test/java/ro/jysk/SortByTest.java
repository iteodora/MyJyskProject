package ro.jysk;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

public class SortByTest {
    WebDriver driver;
    String url = "https://jysk.ro/";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void alphabeticallySort() {
        WebElement cookieAccept = driver.findElement(By.className("coi-banner__accept"));
        if (cookieAccept.isDisplayed()) {
            cookieAccept.click();
        }
        WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Cautare produs sau categorie…']"));
        searchBar.sendKeys("fotoliu");
        searchBar.submit();
        sleep(3000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
        driver.manage().window().fullscreen();
        WebElement dropdown = driver.findElement(By.cssSelector(".form-control.product-list-sort-select"));
        Select dropdownElement = new Select(dropdown);
        dropdownElement.selectByVisibleText("Sortaţi alfabetic");
        WebElement desiredOption=driver.findElement(By.xpath("//*[@class=\"form-control product-list-sort-select \"]/option[6]"));
        dropdown.click();
        Assert.assertTrue(desiredOption.isEnabled());
    }

    @AfterTest(alwaysRun = true)
    public void tearDwon() {
        driver.close();
    }

    public static void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }}


