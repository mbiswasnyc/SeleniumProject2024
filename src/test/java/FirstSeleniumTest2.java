import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumTest2 {
    WebDriver driver = new ChromeDriver();

    @Test
    public void loginTest(){
        startNavigation("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        findAndTypeCss("[name='username']", "Admin");
        findAndTypeCss("[type='password']","admin123");
        findAndClickCss("[type='submit']");
        Assert.assertEquals(findAndGetTextCss(".oxd-text--h6"), "Dashboard");
    }


   /* @AfterMethod
    public void tearDown(){
        driver.close();
    }*/

    private void startNavigation(String url){
        driver.navigate().to(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    private void implicitlyWait(int sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    private void findAndClickCss(String css){
        driver.findElement(new By.ByCssSelector(css)).click();
    }

    private void findAndTypeCss(String css, String text){
        driver.findElement(By.cssSelector(css)).sendKeys(text);
    }

    private String findAndGetTextCss(String css){
        String text = driver.findElement(By.cssSelector(css)).getText();
        return text;
    }

}
