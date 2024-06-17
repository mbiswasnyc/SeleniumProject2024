import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void loginTest(){
        startNavigation("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("abc123");
        driver.findElement(By.cssSelector("[type='submit']")).click();
        String expectedUrl="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        implicitlyWait(10);
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
    }





    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    private void startNavigation(String url){
        driver.navigate().to(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    private void implicitlyWait(int sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

}
