package ro.jysk;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemTest {
    WebDriver driver;
    String url="https://jysk.ro/";
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void addItem(){
        WebElement cookieAccept= driver.findElement(By.className("coi-banner__accept"));
        if (cookieAccept.isDisplayed()) {
            cookieAccept.click();}
        WebElement searchBar= driver.findElement(By.xpath("//input[@placeholder='Cautare produs sau categorie…']"));
        searchBar.sendKeys("fotoliu");
        searchBar.submit();
        Actions actions = new Actions(driver);
        sleep(5000);
        actions.sendKeys(Keys.ESCAPE).perform();
        WebElement fotoliu= driver.findElement(By.xpath("//a[normalize-space()='Fotoliu THORUP bej/stejar']"));
        actions.scrollToElement(fotoliu).perform();
        fotoliu.click();
        WebElement addButton= driver.findElement(By.linkText("Adaugă în coș"));
        addButton.click();
        sleep(3000);
        WebElement notification= driver.findElement(By.cssSelector(".text-header.s-2"));
        String expectedResult="Produsul a fost adăugat în coş";
        Assert.assertTrue(notification.getText().contains(expectedResult));
    }
   @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public static void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
}}
