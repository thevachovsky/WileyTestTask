package TestFrameWork.WebTests.UserLogic.PageObjects;

import TestFrameWork.WebTests.Commons.DriverFabric;
import TestFrameWork.WebTests.Commons.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class MainPage extends BasePage {

    static public class Beneficiars{

        private static List<WebElement> getBeneficiars(){
            return DriverFabric.getDriver().findElements(By.xpath(Locators.whoWeServeBlockTitle));
        }

        public static int getNumber(){
            return getBeneficiars().size();
    }

        public static List<String> getNames(){
            List<String> Names = new LinkedList<>();
            for(WebElement beneficiar: getBeneficiars()){
                Names.add(beneficiar.getText());
            }
            Names.sort(String::compareTo);
            return Names;
        }
    }
}
