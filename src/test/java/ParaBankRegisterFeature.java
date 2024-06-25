import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ParaBankRegisterFeature {
    WebDriver driver = new ChromeDriver();
    Info info = new Info();

    @Test
    public void RegisterForm(){
        startNavigation("https://parabank.parasoft.com/parabank/index.htm");

        findAndClickByLinkedText("Register");

        findAndTypeByID("customer.firstName", info.getFirstName());
        findAndTypeByID("customer.lastName", info.getLastName());
        findAndTypeByID("customer.address.street", info.getAddress());
        findAndTypeByID("customer.address.city", info.getCity());
        findAndTypeByID("customer.address.state", info.getState());
        findAndTypeByID("customer.address.zipCode", info.getZipCode());
        findAndTypeByID("customer.phoneNumber", String.valueOf(info.getPhoneNumber()));
        findAndTypeByID("customer.ssn", String.valueOf(info.getSsn()));

        findAndTypeByID("customer.username", info.getUserName());
        findAndTypeByID("customer.password", "123abc");
        findAndTypeByID("repeatedPassword", "123abc");


        clickCssOrXpath("//form[@id='customerForm']/table/tbody/tr[13]/td[2]/input");

        String expectedMassage = "Your account was created successfully. You are now logged in.";

        Assert.assertEquals(expectedMassage, getElementText("//div[@id='rightPanel']/p"));
        Assert.assertEquals(getElementText("//div[@id='rightPanel']/h1"), "Welcome "+info.getUserName());

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
        wait(5);
        driver.close();
    }

    private void wait(int seconds){
        try{
            Thread.sleep(seconds*1000);
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

    public void clickCssOrXpath(String locator){
        try {
            driver.findElement(By.cssSelector(locator)).click();
        }catch (Exception e){
            driver.findElement(By.xpath(locator)).click();
        }
    }

    public void findAndTypeByID(String locator, String text){
        driver.findElement(By.id(locator)).sendKeys(text);
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


}
