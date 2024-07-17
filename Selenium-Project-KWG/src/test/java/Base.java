import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;


import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;
    public Properties prop;

    @BeforeSuite
    public void loadPropertiesFile() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/info.properties");
            prop.load(fis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

    }

    public void handleDropDown(By locator, String value, String type) throws Exception {
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        switch (type.toLowerCase()) {
            case "value": {
                dropdown.selectByValue(value);
                break;
            }
            case "index": {
                dropdown.selectByIndex(Integer.parseInt(value));
                break;
            }
            case "visible text": {
                dropdown.selectByVisibleText(value);
                break;
            }
            default: {
                throw new Exception("Handling drop down Type not found");
            }
        }
    }

    public void setValuetoTextField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    public List findMultipleElement(By locator){
        List<WebElement>  elements =  driver.findElements(locator);
       return elements;
    }

    @AfterTest
    public void endSetup() {
        driver.quit();
    }
}