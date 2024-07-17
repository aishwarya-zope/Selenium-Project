import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Iterator;
import java.util.List;

public class Specs extends Base {

    @Test
    public void validateSearchProductSuggestions() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        handleDropDown(By.id(prop.getProperty("GeneralSearchDropDownId")),"Electronics","visible text");
        setValuetoTextField(By.id(prop.getProperty("GeneralSearchTextFieldId")),"IPhone 13");
        List<WebElement> suggestions = findMultipleElement(By.xpath(prop.getProperty("GeneralSearchSuggestionXapth")));
        Iterator<WebElement> eachSuggestionValue = suggestions.iterator();
        while(eachSuggestionValue.hasNext()){
            if(!eachSuggestionValue.next().getText().toLowerCase().contains("iphone")){
                softAssert.fail(eachSuggestionValue.next().getText()+" Does not contain Iphone 13");
            }
        }
        softAssert.assertAll();
    }
}
