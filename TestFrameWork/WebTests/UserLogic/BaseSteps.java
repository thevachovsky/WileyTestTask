package TestFrameWork.WebTests.UserLogic;

import TestFrameWork.Commons.LoggerFabric;
import TestFrameWork.WebTests.UserLogic.PageObjects.BasePage;
import TestFrameWork.WebTests.UserLogic.PageObjects.BasePage.DropDownMenu.MenuOption;
import TestFrameWork.WebTests.UserLogic.PageObjects.BasePage.DropDownMenu.DropDownMenuOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseSteps {
    public static final LoggerFabric.Logger logger = LoggerFabric.getLogger();

    public static void placeCursorOnSubjectsMenu(){
        BasePage.subjectsButton.placeMouseCursorOn();
    }

    public static void clickDontChangeLocationButton(){
        BasePage.DoNotChangeLocationButton.click();
    }

    public static void enterPhraseInSearchBox(String text){
        BasePage.searchBox.sendText(text);
    }

    public static void searchPhrase(String text){
        enterPhraseInSearchBox(text);
        BasePage.searchButton.click();

    }

    public static void searchIfSearchDropDownListArePresented() throws InterruptedException {
        logger.info("Checking if search tips are not visible");
        assertTrue("Search tips are not visible", BasePage.searchTips.checkIfPresentAndVisible());
    }

    public static void moveCursorToSubjectsMenu(){
        BasePage.subjectsButton.placeMouseCursorOn();
    }

    //TODO Replace this with a method with automated search of parental element.
    public static void checkIfSubjectsOptionPresent(String element) throws Exception {
        logger.info(String.format("Trying to find DropDown element %s", element));
        BasePage.subjects.checkElement(element);
    }

    public static void clickSubjectsOption(String element) throws Exception {
        BasePage.subjects.clickElement(element);
    }

    public static void  selectSubjectsOption(String element) throws Exception {
        MenuOption checkedElement = BasePage.subjects.getElement(element);
        checkedElement.placeMouseCursorOn();
    }

    public static void checkElementsOfSubjectsSubMenu(String element, String[] elementsToCheck) throws Exception {
        DropDownMenuOption subjectsOption = (DropDownMenuOption) BasePage.subjects.getElement(element);
        List<String> requestedOptions = Arrays.asList(elementsToCheck);
        List<String> presentedOptions = new ArrayList<>(subjectsOption.getSubDropDownMenu().getOptionNamesSet());
        requestedOptions.sort(String::compareTo);
        presentedOptions.sort(String::compareTo);
        logger.info(String.format("Requested list of options: %s", requestedOptions.toString()));
        logger.info(String.format("Presented list of options: %s", presentedOptions.toString()));
        assertEquals("Requested list of options in is not equal to presented", requestedOptions, presentedOptions);
    }
}
