package ro.jysk;

import org.junit.platform.commons.function.Try;
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
        WebElement passwordInput= driver.findElement(By.id("password"));
        passwordInput.sendKeys("somehow,Imanage0");
        passwordInput.sendKeys(Keys.ENTER);
        driver.manage().window().maximize();
        //WebElement logoutButton= driver.findElement(By.xpath("//*[@id='my-jysk-left-nav']/li[5]/a"));
        //Assert.assertTrue(logoutButton.isDisplayed());
        //WebElement myProfile= driver.findElement(By.linkText("Profil"));
        //myProfile.click();
        WebElement myAccount=driver.findElement(By.tagName("h1"));
        Assert.assertTrue(myAccount.isDisplayed());
        WebElement accountInformation= driver.findElement(By.className("table"));
        Assert.assertTrue(accountInformation.getText().contains("michaelscott.dundermifflin@yahoo.com"));

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

