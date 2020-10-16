package TestFrameWork.WebTests.UserLogic;

import TestFrameWork.Commons.LoggerFabric;
import TestFrameWork.WebTests.UserLogic.PageObjects.SearchResultsPage;
import TestFrameWork.WebTests.UserLogic.PageObjects.SearchResultsPage.OfferList;
import TestFrameWork.WebTests.UserLogic.PageObjects.SearchResultsPage.OfferList.Offer;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchResultsSteps extends MainPageSteps {
    static LoggerFabric.Logger logger = LoggerFabric.getLogger();

    public static void checkIfNumberOfOffersIsExpected(int number) throws Exception {
        int numberOfOffers = SearchResultsPage.OfferList.getNumberOfOffers();
        assertEquals(
                String.format("The number of offers is not equal to %d. Only %d have been found.\n", number, numberOfOffers),
                    numberOfOffers, number);
    }

    public static void checkIfAllOfferNamesIncludePhrase(String phrase) throws Exception {
        logger.info(String.format("Going to check if all offer names include phrase '%s'",phrase));

        boolean result = true;
        String offerNameWithOutPhrase = "";
        for(String name: SearchResultsPage.OfferList.getOffersNames()){
            if(!name.contains(phrase)){
                result=false;
                break;
            }
            logger.info(String.format("%s includes phrase '%s'",name, phrase));
        }
        logger.info("Checking result");

        assertTrue(String.format("This offer's name does not include '%s': '%s'", phrase, offerNameWithOutPhrase),
                result);
        logger.info(String.format("All offer names include phrase '%s'\n",phrase));

    }

    public static void checkAddToCartButtons() throws Exception {
        logger.info("\nChecking all purchase options to find at least one visible 'Add-To-Cart' button");
        List<String> offersWithoutVisibleAddToCartButton = new LinkedList<>();

        for(Offer offer: OfferList.getOffers()){
            logger.info(String.format("Checking 'AddToCart' button for %s", offer.name));
            if(!offer.hasAddToCartButton||(!offer.addToCartButtonIsVisible&&!OfferList.checkAddToCartButtonForAllPaymentOptions(offer))){
                offersWithoutVisibleAddToCartButton .add(offer.name);
            }
        }
        assertEquals(String.format("\nSome of offers do not have visible 'Add-To-Cart' button: %s. ",
                String.join(",", offersWithoutVisibleAddToCartButton)),
                0, offersWithoutVisibleAddToCartButton.size());
        logger.info("Checking all purchase options to find at least one visible 'Add-To-Cart' button\n");
    }

    public static void checkObookOptions() throws Exception {
        logger.info("\nChecking if all offer items with Obook option have the link to 'Wiley Online Library'");
        for(Offer offer: OfferList.getOffers()){
            logger.info(String.format("Checking '%s'", offer.name));
            if(offer.hasObookOption){
                WebElement goToWilleyOLButton = offer.getGoToWileyOnlineLibraryButton();
                assertTrue(String.format("%s has the 'OBook' option, but respective button is not visible", offer.name),
                        goToWilleyOLButton.isDisplayed());
            }

        }
        logger.info("Checking if all offer items with a 'Obook' option have the link to 'Wiley Online Library' finished\n");
    }
}
