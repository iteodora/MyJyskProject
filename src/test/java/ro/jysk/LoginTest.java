package ro.jysk;

import org.junit.platform.commons.function.Try;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.v6.C;

import javax.swing.*;
import java.sql.Driver;

public class LoginTest {
    WebDriver driver;
    String url="https://jysk.ro/";
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().fullscreen();
    }
    @Test
    public void login(){
        WebElement cookieAccept= driver.findElement(By.className("coi-banner__accept"));
        if (cookieAccept.isDisplayed()) {
        cookieAccept.click();}
        WebElement loginLink= driver.findElement(By.xpath("//*[@href='/customer/login']"));
        loginLink.click();
        sleep(3000);
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
        WebElement emailInput= driver.findElement(By.id("email"));
        emailInput.sendKeys("michaelscott.dundermifflin@yahoo.com");
        WebElement keepMeLoggedInButton= driver.findElement(By.cssSelector("input#keep-me-logged-in"));
        keepMeLoggedInButton.click();
        WebElement passwordInput= driver.findElement(By.id("password"));
        passwordInput.sendKeys("somehow,Imanage0");
        passwordInput.sendKeys(Keys.ENTER);
        driver.manage().window().maximize();
        sleep(3000);
        WebElement logoutButton= driver.findElement(By.linkText("Ieși din cont"));
        Assert.assertTrue(logoutButton.isDisplayed());
        /*WebElement myAccount=driver.findElement(By.xpath("//span[@class='icon-text py-2 d-block']"));
        myAccount.click();
        WebElement myProfile=driver.findElement(By.linkText("Profil"));
        myProfile.click();
        WebElement accountInformation= driver.findElement(By.cssSelector("td[data-label='Informații client și adresă de facturare']"));
        Assert.assertTrue(accountInformation.getText().contains("michaelscott.dundermifflin@yahoo.com"));*/
        String mesaj="Nu ai efectuat nicio comandă până acum.";
        actions.sendKeys(Keys.ESCAPE).perform();
        WebElement  accountInfo= driver.findElement(By.cssSelector(".order-table"));
        Assert.assertTrue(accountInfo.getText().contains(mesaj));
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

