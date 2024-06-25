import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DemoQA_W {
    WebDriver driver = new ChromeDriver();

    @Test
    public void elementsButton(){
        startNavigation("https://demoqa.com/");

        clickFromElementList(".card-up", "Elements");

        click("#item-0");

        String name = "Mritunjoy Biswas";
        String email = "abc@gmail.com";
        String currentAddress = "123 Hillside ave, Jamaica, NY 11235";
        String permanentAddress = "456 Jamaica ave, Jamaica, NY 11435";

        findAndTypeById("userName", name);
        findAndTypeById("userEmail", email);
        findAndTypeById("currentAddress", currentAddress);
        findAndTypeById("permanentAddress", permanentAddress);

        click("[id='submit']");

        //Assert.assertTrue(driver.findElement(By.xpath("//p[@id='name']")).getText().contains(name));

        Assert.assertEquals(getElementTextById("name"), "Name:"+name);
        Assert.assertEquals(getElementTextById("email"), "Email:"+email);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='currentAddress']")).getText().contains(currentAddress));
        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText().contains(permanentAddress));



    }

    public void startNavigation(String url){
        driver.navigate().to(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void implicitlyWait(int sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    @AfterMethod
    public void quit(){
        wait(2);
        driver.close();
    }

    private void wait(int second){
        try{
            Thread.sleep(second*1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }

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
    public String getElementTextById(String locator){
        return driver.findElement(By.id(locator)).getText();
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
