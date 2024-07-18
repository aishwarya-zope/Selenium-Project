import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ElementInteractions {
    private final WebDriver driver;
    private final Logger logger;


    ElementInteractions(WebDriver driver, Logger logger){
        this.driver = driver;
        this.logger = logger;
    }

    /**
     * Method for Drop down value select
     * @param locator
     * @param value
     * @param type
     * @throws Exception
     */
    public void selectValueFromDropDown(By locator, String value, String type) throws Exception {
        WebElement dropdownElement = driver.findElement(locator);
        logger.info("Element found by "+locator);
        Select dropdown = new Select(dropdownElement);
        switch (type.toLowerCase()) {
            case "value": {
                dropdown.selectByValue(value);
                logger.info("Drop down "+value+"selected");
                break;
            }
            case "index": {
                dropdown.selectByIndex(Integer.parseInt(value));
                logger.info("Drop down "+value+"selected");
                break;
            }
            case "visible text": {
                dropdown.selectByVisibleText(value);
                logger.info("Drop down "+value+"selected");
                break;
            }
            default: {
                throw new Exception("Handling drop down Type not found");
            }
        }
    }

    /**
     * Method for entering value in text field
     * @param locator
     * @param value
     */
    public void setValuetoTextField(By locator, String value){
        driver.findElement(locator).clear();
        logger.info("Cleared the text field located by "+locator);
        driver.findElement(locator).sendKeys(value);
        logger.info("Entered value "+value+"in text field located by "+locator);
    }

    /**
     * Method for getting the list of multiple elements
     * @param locator
     * @return
     */
    public List<WebElement> findMultipleElement(By locator){
        logger.info("finding multiple elements from "+locator);
        return driver.findElements(locator);

    }

    /**
     * Method to click
     * @param locator
     */
    public void click(By locator){
        driver.findElement(locator).click();
        logger.info("clicked on "+locator);

    }

    public boolean isElementPresent(By locator){
        try{
            driver.findElement(locator);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    /**
     * Method to switch to new tab
     */
    public void switchToNewTab(){
        String defaultWindow = driver.getWindowHandle();
       Set<String> windows =  driver.getWindowHandles();
        for (String window : windows) {
            if (!(window.equals(defaultWindow))){
             driver.switchTo().window(window);
             break;
            }
        }
    }

    /**
     * Method for mouse hovering on element
     * @param locator
     */
    public void hoverOnElement(By locator){
       Actions action = new Actions(driver);
      WebElement element =  driver.findElement(locator);
       action.moveToElement(element);
    }

    /**
     * Method to get text from element
     * @param locator
     * @return
     */
    public String getText(By locator){
        return driver.findElement(locator).getText();
    }


}
