import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DemoQA_WebTable {

    WebDriver driver = new ChromeDriver();
    Info info = new Info();

    @Test
    public void WebText() throws InterruptedException {
        startNavigation("https://demoqa.com/");
        Thread.sleep(2000);
        clickFromElementList(".card-up", "Elements");
        click("#item-3");
        click("#addNewRecordButton");
        findAndType("#firstName", info.getFirstName());
        findAndType("#lastName", info.getLastName());
        findAndType("#userEmail", info.getEmail());
        findAndType("#age", String.valueOf(info.getAge()));
        findAndType("#salary", String.valueOf(info.getSalary()));
        findAndType("#department", info.getDepartment());

        click("button#submit");

        //Assert.assertEquals(getElementText("//*[@id='app']/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[1]"), info.getFirstName());
        //Assert.assertEquals(getElementText("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div[1]/div[5]"), String.valueOf(info.getSalary()));

        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='rt-tr-group']/div/div[4]"));
        validateStringPresent(elements, info.getEmail());

        elements.clear();

        elements = driver.findElements(By.xpath("//div[@class='rt-tr-group']/div/div[1]"));
        validateStringPresent(elements, info.getFirstName());
    }

        private void validateStringPresent(List<WebElement> elements, String value){
            boolean isPresent = false;
            for(WebElement element : elements){
                if(element.getText().equals(value)){
                    isPresent = true;
                    break;
                }
            }
            Assert.assertTrue(isPresent);
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

