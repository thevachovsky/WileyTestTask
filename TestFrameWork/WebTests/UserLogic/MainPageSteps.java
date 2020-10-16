package TestFrameWork.WebTests.UserLogic;

import TestFrameWork.WebTests.UserLogic.PageObjects.MainPage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPageSteps extends BaseSteps {
    public static int getNumberOfBeneficiarGroups(){
        return MainPage.Beneficiars.getNumber();
    }

    public static Boolean CheckIfAllGroupsPresented(List<String> groupNames){
        logger.info("Checking a content of Beneficiar Groups");
        groupNames.sort(String::compareTo);
        List<String> groupNamesOnPage = MainPage.Beneficiars.getNames();
        if(!groupNames.equals(groupNamesOnPage)){
            logger.info(String.format("%s\n is not \n%s", groupNames.toString(), groupNamesOnPage.toString()));
            return false;
        }
        return true;
    }

    public static void checkIfNumberOfBeneficiarsIsExpected(int number){
        logger.info("Checking a number of Beneficiar Groups");
        assertEquals(String.format("Number of Beneficiar Groups is not equal to %d", number),
                number, getNumberOfBeneficiarGroups());
    }

    public static void checkIfBeneficiarsGroupsAreExpected(List<String> groupsOnPage){
        assertTrue("The list of names on the page does not match to expected ones",
                CheckIfAllGroupsPresented(groupsOnPage));
    }


}
