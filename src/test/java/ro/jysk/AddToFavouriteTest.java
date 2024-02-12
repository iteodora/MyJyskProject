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

public class AddToFavouriteTest {
    WebDriver driver;
    String url="https://jysk.ro/";
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void addToFavourite(){
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
        WebElement favouriteButton= driver.findElement(By.xpath("//button[@class='icon btn btn-link add-to-wishlist']"));
        favouriteButton.click();
        sleep(3000);
        actions.sendKeys(Keys.ESCAPE).perform();
        Assert.assertTrue(favouriteButton.isEnabled());
        WebElement wishlistLink= driver.findElement(By.xpath("//a[@id='wishlist-link']"));
        wishlistLink.click();
        driver.manage().window().fullscreen();
        WebElement wishlistContent= driver.findElement(By.xpath("//span[@class='product-name']"));
        String wishlistProduct="Fotoliu THORUP bej/stejar";
        Assert.assertTrue(wishlistContent.getText().contains(wishlistProduct));
    }
   @AfterTest
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

