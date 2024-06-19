import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalculatorGas_W {
    WebDriver driver = new ChromeDriver();

    @Test
    public void gasCalculator(){
        startNavigation("https://www.calculator.net/");

        findAndTypeByCss("#calcSearchTerm", "gas");
        findAndClickByLinkedText("Gas Mileage Calculator");

        findAndClickByCss("[value='Clear']");

        findAndTypeByCss("#uscodreading", "68000");
        findAndTypeByCss("#uspodreading", "67600");
        findAndTypeById("usgasputin", "12");
        findAndTypeById("usgasprice", "4.59");

        findAndClickByCss("[name='x']");

        Assert.assertEquals(findAndGetTextByCss("[class='h2result']"), "Result");

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

    private void findAndClickByCss(String css){
        driver.findElement(By.cssSelector(css)).click();
    }

    private void findAndTypeByCss(String css, String text){
        driver.findElement(By.cssSelector(css)).sendKeys(text);
    }

    private void findAndTypeById(String id, String text){
        driver.findElement(By.id(id)).sendKeys(text);
    }

    private String findAndGetTextByCss(String css){
        String text = driver.findElement(By.cssSelector(css)).getText();
        return text;
    }

    private void findAndClickByLinkedText(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }
}
