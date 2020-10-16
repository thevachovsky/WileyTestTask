package TestFrameWork.WebTests.UserLogic.PageObjects;

import TestFrameWork.Commons.LoggerFabric;
import TestFrameWork.WebTests.Commons.DriverFabric;
import TestFrameWork.WebTests.Commons.Locators;
import TestFrameWork.WebTests.UserLogic.PageObjects.WebElements.DropDownList;
import TestFrameWork.WebTests.UserLogic.PageObjects.WebElements.InputBox;
import TestFrameWork.WebTests.UserLogic.PageObjects.WebElements.PageButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasePage {
    private static final LoggerFabric.Logger logger = LoggerFabric.getLogger();

    public final static PageButton DoNotChangeLocationButton =
            new PageButton(Locators.doNotChangeLocationButton, "DoNotChangeLocationButton");

    public final static PageButton searchButton = new PageButton(Locators.searchButton, "SearchButton");

    //TODO introduce container-class for head-buttons like this 'subjects' one, for more explicit architecture
    public final static PageButton subjectsButton = new PageButton(Locators.subjectsButton, "SubjectsButton");

    public final static InputBox searchBox = new InputBox(Locators.searchBox, "SearchBox");

    public final static DropDownList searchTips = new DropDownList(Locators.searchTips,"SearchTips");

    public final static DropDownMenu subjects = new DropDownMenu(Locators.subjects,"Subjects",1);


    public static class DropDownMenu{
        int endLevel;
        int currentLevel = 0;
        String name;
        String locator;
        WebElement webElement;
        Boolean initiated=false;
        Map<String, DropDownMenuOption> innerMap;

        public static class DropDownMenuOption{

            WebElement body;
            Boolean isVisible;
            String name;
            int currentLevel;
            int endLevel;

            public DropDownMenuOption(String name, WebElement body, boolean isVisible, int endLevel, int currentLevel ){
                this.isVisible = isVisible;
                this.name = name;
                this.body = body;
                this.endLevel = endLevel;
                this.currentLevel = currentLevel;
            }

            public void placeMouseCursorOn(){
                Actions actions = new Actions(DriverFabric.getDriver());
                actions.moveToElement(this.body).perform();
                logger.info(String.format("Mouse is placed on dropdown element %s", name));
            }

            public void click(){
                body.click();
            }

            public DropDownMenu getSubDropDownMenu() throws Exception {
                if(this.currentLevel < endLevel){
                    DropDownMenu innerDropMenu = new DropDownMenu(
                            this.body, String.format("%sDropDownMenu", this.name), endLevel);
                    innerDropMenu.currentLevel += 1;
                    return innerDropMenu;
                }
                throw new Exception("This level of menu does not have nested options");
            }
        }

        public DropDownMenu(String locator, String name, int deepOfNestedElements){
            this.name = name;
            this.locator = locator;
            this.endLevel = deepOfNestedElements;
        }

        public DropDownMenu(WebElement webElement, String name, int deepOfNestedElements){
            this.webElement = webElement;
            this.name = name;
            this.endLevel = deepOfNestedElements;
        }

        public Map<String, DropDownMenuOption> getOptionList(){
            if(this.initiated){
                return this.innerMap;
            }
            if(webElement==null){
                webElement = DriverFabric.getDriver().findElement(By.xpath(locator));
            }
            this.innerMap = new HashMap<>();
            List<WebElement> rawElements = webElement.findElements(By.xpath(".//li/a"));
            if (rawElements.size()==0){
                rawElements = webElement.findElements(By.xpath("./..//li/a"));
            }

            for(WebElement element: rawElements){
                innerMap.put(element.getText(), new DropDownMenuOption(
                        element.getText(), element, element.isDisplayed(), endLevel, currentLevel));
            }

            initiated = true;
            return innerMap;
        }

        public DropDownMenuOption getElement(String element) throws Exception {

            getOptionList();

            DropDownMenuOption result = innerMap.getOrDefault(element, null);

            if(result==null){
                logger.info(String.format("Inccorect name has been requested. %s is not in dropdown list %s", element, this.name));
                throw new Exception(String.format(
                        "Inccorect name has been requested. %s is not in dropdown list %s", element, this.name));
            }
            return result;
        }

        public Set<String> getOptionNamesList(){
            getOptionList();
            return innerMap.keySet();
        }
    }


}

