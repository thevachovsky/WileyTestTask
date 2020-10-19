package TestFrameWork.WebTests.UserLogic.PageObjects.WebElements;

import TestFrameWork.Commons.LoggerFabric;
import TestFrameWork.WebTests.Commons.DriverFabric;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement {
    String locator;
    String name="Unnnamed web element";

    static LoggerFabric.Logger logger = LoggerFabric.getLogger();

    public BaseElement(String locator){
        this.locator = locator;
    }

    public BaseElement(String locator, String name){
        this.locator = locator;
        this.name = name;

    }

    public void click(){
        System.out.println(String.format("Going to click %s", this.name));
        logger.info(String.format("Going to click %s", this.name));

        DriverFabric.getDriver().findElement(By.xpath(locator)).click();
        logger.info(String.format("%s has been clicked", this.name));
    }

    public boolean checkIfPresentAndVisible() throws InterruptedException {
        try {
            logger.info(String.format("Going to check if %s presents and is visible with locator %s", this.name, locator));
            WebElement tips = new WebDriverWait(DriverFabric.getDriver(), 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            Boolean isVisible = tips.isDisplayed();
            logger.info(String.format("%s presents, it is visible: %b.", this.name, isVisible));
            return true;

        }
        catch (Throwable t){
            return false;
        }
    }

    public void placeMouseCursorOn(){
        WebElement element = DriverFabric.getDriver().findElement(By.xpath(locator));
        Actions actions = new Actions(DriverFabric.getDriver());
        actions.moveToElement(element).perform();
        logger.info(String.format("Mouse is placed on %s", name));
    }
    }

