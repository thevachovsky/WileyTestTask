package TestFrameWork.WebTests.UserLogic.PageObjects.WebElements;

import TestFrameWork.WebTests.Commons.DriverFabric;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageButton extends BaseElement{

    public PageButton(String locator, String name){
        super(locator, name);
    }


    public void clickIfExists(){
        List<WebElement> elements = DriverFabric.getDriver().findElements(By.xpath(locator));
        if (elements.size() > 0){
            elements.get(0).click();
        }
    }
}
