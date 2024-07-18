import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;
import java.util.List;

public class TestCases extends Base {

    @Test
    public void validateSearchProductSuggestions() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        ElementInteractions interact = new ElementInteractions(driver,logger);
        interact.selectValueFromDropDown(By.id(prop.getProperty("GeneralSearchDropDown_Id")), "Electronics", "visible text");
        interact.setValuetoTextField(By.id(prop.getProperty("GeneralSearchTextField_Id")), "IPhone 13");
        List<WebElement> suggestions = interact.findMultipleElement(By.xpath(prop.getProperty("GeneralSearchSuggestion_Xpath")));
        Iterator<WebElement> eachSuggestionValue = suggestions.iterator();
        while (eachSuggestionValue.hasNext()) {
            if (!eachSuggestionValue.next().getText().toLowerCase().contains("iphone")) {
                softAssert.fail(eachSuggestionValue.next().getText() + " Does not contain Iphone 13");
            }
        }
        softAssert.assertAll();
    }

    @Test
    public void QuickLookUI() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        ElementInteractions interact = new ElementInteractions(driver,logger);
        interact.selectValueFromDropDown(By.id(prop.getProperty("GeneralSearchDropDown_Id")), "Electronics", "visible text");
        interact.setValuetoTextField(By.id(prop.getProperty("GeneralSearchTextField_Id")), "IPhone 13 128 GB");
        interact.click(By.xpath(prop.getProperty("ResultSearchForIphone13_128GB_Xpath")));
        if (interact.isElementPresent(By.xpath(prop.getProperty("searchResultComponent_Xpath")))) {
            interact.click(By.xpath(prop.getProperty("firstSearchedResult")));
        } else {
            Assert.fail("Search result not present");
        }
        interact.switchToNewTab();
        interact.click(By.partialLinkText("Visit the Apple Store"));
        interact.click(By.xpath(prop.getProperty("appleStore-AppleWatch")));
        interact.click(By.xpath(prop.getProperty("appleStore-WatchSE(GPS+Cellular)")));
        interact.hoverOnElement(By.xpath(prop.getProperty("appleStore-firstOptionOfWatchSE(GPS+Cellular)")));
        String actualTitleOfProduct = interact.getText(By.xpath(prop.getProperty("appleStore-ProductName")));
        if(interact.isElementPresent(By.xpath(prop.getProperty("appleStore-quicklook")))){
            interact.click(By.xpath(prop.getProperty("appleStore-quicklook")));
        }
        else{
            Assert.fail("Quick Look button not present");
        }
        String titleOfProduct = interact.getText(By.xpath(prop.getProperty("ProductNameOfWatch")));
        if(!actualTitleOfProduct.contains(titleOfProduct)){
            System.out.println(actualTitleOfProduct);
            System.out.println(titleOfProduct);
            Assert.fail("Product mismatched");
        }
    }
}
