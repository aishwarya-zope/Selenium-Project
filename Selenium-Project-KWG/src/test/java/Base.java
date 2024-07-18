import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;
    public Properties prop;
    public static final Logger logger = LogManager.getLogger(Base.class);


    @BeforeSuite
    public void loadPropertiesFile() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/info.properties");
            prop.load(fis);
            logger.info("loaded info.properties file");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Initializing chromeDriver");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        logger.info("Navigating to {}", prop.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void endSetup() {
        driver.quit();
        logger.info("Closed all browser window");
    }
}
