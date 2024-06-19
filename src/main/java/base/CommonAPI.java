package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class CommonAPI {

    WebDriver driver = new ChromeDriver();

    public void startNavigation(String url){
        driver.navigate().to(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void implicitlyWait(int sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    /*@AfterMethod
    public void tearDown(){
        driver.close();
    }*/

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }

    public void click(String locator){
        try {
            driver.findElement(By.cssSelector(locator)).click();
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).click();
        }
    }

    public void findAndType(String locator, String text){
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }
    }

    public String getElementText(String locator){
        try {
            return driver.findElement(By.xpath(locator)).getText();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).getText();
        }
    }

    public void findAndTypeById(String id, String text){
        driver.findElement(By.id(id)).sendKeys(text);
    }

    private void findAndClickByLinkedText(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    public void clickFromElementList(String locator, String elementName){
        try {
            List<WebElement> elements = driver.findElements(By.cssSelector(locator));
            for(WebElement element : elements){
                if (element.getText().equals(elementName));
                element.click();
                break;
            }
        }catch (Exception e){
            List<WebElement> elements = driver.findElements(By.xpath(locator));
            for(WebElement element : elements){
                if (element.getText().equals(elementName));
                element.click();
                break;
            }
        }
    }
}
