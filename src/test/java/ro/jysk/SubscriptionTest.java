package ro.jysk;

import org.checkerframework.checker.units.qual.A;
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

public class SubscriptionTest {
    WebDriver driver;
    String url="https://jysk.ro/";
    @BeforeTest
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void newsletterSubscription(){
        WebElement cookieAccept= driver.findElement(By.className("coi-banner__accept"));
        if (cookieAccept.isDisplayed()) {
            cookieAccept.click();}
        sleep(3000);
        Actions action=new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
        driver.manage().window().fullscreen();
        WebElement nameInput= driver.findElement(By.xpath("//form[@id='jysk-teradata-subscribe-form']//input[@name='name']"));
        nameInput.sendKeys("Michael Scott");
        WebElement emailInput= driver.findElement(By.xpath("/html//input[@id='teradata-email']"));
        emailInput.sendKeys("michaelscott.dundermifflin@yahoo.com");
        WebElement submitButton= driver.findElement(By.xpath("//form[@id='jysk-teradata-subscribe-form']//button[@type='submit']"));
        submitButton.submit();
        WebElement footer= driver.findElement(By.cssSelector(".footer-some-icon"));
        action.scrollToElement(footer).perform();
        WebElement checkBox= driver.findElement(By.id("edit-terms"));
        action.click(checkBox).perform();
        submitButton.submit();
        Assert.assertTrue(checkBox.isSelected());
        WebElement subscribeInfo= driver.findElement(By.cssSelector(".form-check.terms"));
        String expectedMessage="Nu puteţi utiliza această adresă de e-mail, deoarece este deja înregistrată.";
        Assert.assertTrue(subscribeInfo.getText().contains(expectedMessage));
    }
    @AfterTest(alwaysRun = true)
    public void tearDown(){driver.close();}
    public static void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
