package TestFrameWork.WebTests.UserLogic.PageObjects.WebElements;

import TestFrameWork.WebTests.Commons.DriverFabric;
import org.openqa.selenium.By;

public class InputBox extends BaseElement{

    public InputBox(String locator, String name){
        super(locator, name);
    };

    public void sendText(String text){
        logger.info(String.format("Going to send text '%s' in input field %s", text, this.name));
        DriverFabric.getDriver().findElement(By.xpath(locator)).sendKeys(text);
        logger.info(String.format("The text '%s' in input field %s", text, this.name));

    }
}