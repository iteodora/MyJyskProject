package ro.jysk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.v6.C;

public class SearchPorductTest {
    WebDriver driver;
    String url="https://jysk.ro/";
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void searchProduct(){
        WebElement cookieAccept= driver.findElement(By.className("coi-banner__accept"));
        if (cookieAccept.isDisplayed()) {
            cookieAccept.click();}
        WebElement searchBar= driver.findElement(By.xpath("//input[@placeholder='Cautare produs sau categorie…']"));
        searchBar.sendKeys("fotoliu");
        searchBar.submit();
        WebElement searchResult= driver.findElement(By.cssSelector("[class='mb-4']"));
        String expectedResult="potriviri pentru: fotoliu";
        Assert.assertTrue(searchResult.getText().contains(expectedResult));
    }
    @AfterTest(alwaysRun = true)
    public void tearDwon(){
        driver.close();
    }
}
