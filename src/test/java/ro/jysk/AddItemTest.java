package ro.jysk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest {
    WebDriver driver;
    String url="https://jysk.ro/";
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void addToCart(){
        WebElement cookieAccept= driver.findElement(By.className("coi-banner__accept"));
        if (cookieAccept.isDisplayed()) {
            cookieAccept.click();}
        WebElement searchBar= driver.findElement(By.xpath("//input[@placeholder='Cautare produs sau categorie…']"));
        searchBar.sendKeys("fotoliu");
        searchBar.submit();
        WebElement fotoliuAlb= driver.findElement(By.linkText("<a href='/sufragerie/fotolii/fotoliu-vildsund-alb-murdar' class='product-teaser-buttons btn btn-secondary btn-block'>Citește mai multe</a>"));
        fotoliuAlb.click();
        WebElement addButton= driver.findElement(By.linkText("Adaugă în coș"));
        addButton.click();
        WebElement notification= driver.findElement(By.id("notification"));
        String expectedResult="Produsul a fost adăugat în coş";
        Assert.assertTrue(notification.getText().contains(expectedResult));
    }
    @AfterTest(alwaysRun = true)
    public void tearDwon(){
        driver.close();
    }
}
