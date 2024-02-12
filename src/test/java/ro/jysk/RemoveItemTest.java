package ro.jysk;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RemoveItemTest {
    WebDriver driver;
    String url="https://jysk.ro/";
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void removeItem(){
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
        WebElement notification= driver.findElement(By.id("notification"));
        WebElement validationButton= driver.findElement(By.linkText("Validare"));
        validationButton.click();
        WebElement removeButton= driver.findElement(By.xpath("//span[@class='remove-from-basket removeFromBasket']"));
        removeButton.click();
        WebElement checkoutMessage=driver.findElement(By.xpath("//*[@id='checkout']"));
        actions.sendKeys(Keys.ESCAPE).perform();
        WebElement emptyCart= driver.findElement(By.cssSelector(".empty"));
        Assert.assertTrue(checkoutMessage.getText().contains(emptyCart.getText()));
    }
    @AfterTest
    public void tearDwon(){
        driver.close();
    }
    public static void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
}}
