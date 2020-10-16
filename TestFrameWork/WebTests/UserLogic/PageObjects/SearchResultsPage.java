package TestFrameWork.WebTests.UserLogic.PageObjects;

import TestFrameWork.Commons.LoggerFabric;
import TestFrameWork.WebTests.Commons.DriverFabric;
import TestFrameWork.WebTests.Commons.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SearchResultsPage extends BasePage {
    static LoggerFabric.Logger logger = LoggerFabric.getLogger();

    public static class OfferList {

        private static final String offerLocator = Locators.offerLocator;
        private static  int numberOfOffers;
        private static  List<String> offersNames;
        private static boolean initiated = false;

        private static List<Offer> offers = new LinkedList<>();

        public static class Offer {

            public Offer(WebElement body){ this.body = body;
            }
            private static class ProductOptions {
                Map<String, WebElement> productOptionsMap;
                Boolean hasObookVersion = false;
            }

            private static class AddToCartButtonInfo {
                Boolean isVisible = false;
                Boolean isPresented = false;
            }

            public String name;
            public ProductOptions productOptions;
            public Boolean hasAddToCartButton = false;
            public Boolean addToCartButtonIsVisible = false;
            public Boolean hasObookOption = false;

            public WebElement addToCartButton;
            public WebElement body;
            private WebElement goToWileyOnlineLibraryButton;

            public WebElement getGoToWileyOnlineLibraryButton() throws Exception {
                logger.info(String.format(
                        "Trying to find a Wiley Online Library button for %s. It hase following options: %S",
                        name, String.join(", ", this.productOptions.productOptionsMap.keySet())));

                this.productOptions.productOptionsMap.get("O-BOOK").click();
                List<WebElement> SearchResults = this.body.findElements(By.xpath(Locators.viewOnWileyOnlineLibraryButton));
                WebElement goToLibraryButton = this.body.findElement(By.xpath(Locators.viewOnWileyOnlineLibraryButton));

                if (SearchResults.size() == 0 || !goToLibraryButton.isDisplayed()){
                    throw new Exception(String.format(
                            "The offer %s does not have a Wiley Online Library option: %b or it is not visible: %b",
                            name, SearchResults.size() == 0, !goToLibraryButton.isDisplayed()));
                }
                else {
                    logger.info(String.format("Wiley Online Library button has been successfully found for %s", name));
                    return SearchResults.get(0);
                }
            }

        }

        public static boolean checkAddToCartButtonForAllPaymentOptions(Offer offer){
            for(Map.Entry<String, WebElement> option : offer.productOptions.productOptionsMap.entrySet()){
                option.getValue().click();
                if(offer.body.findElement(By.xpath(Locators.addToCartButton)).isDisplayed()){
                    offer.addToCartButton = offer.body.findElement(By.xpath(Locators.addToCartButton));
                    return true;
                }
            }
            return false;
        }

        private static Offer.AddToCartButtonInfo checkAddToCartButton(Offer offer) {
            Offer.AddToCartButtonInfo info = new Offer.AddToCartButtonInfo();
            if (offer.body.findElements(By.xpath(Locators.addToCartButton)).size() != 0){
                info.isPresented = true;
                if(offer.body.findElement(By.xpath(Locators.addToCartButton)).isDisplayed()){
                    offer.addToCartButton = offer.body.findElement(By.xpath(Locators.addToCartButton));
                    info.isVisible = true;
                    }
                }

            return info;
        }

        private static Offer.ProductOptions getProductOptionsAndCheckThem(List<WebElement> options){
            Offer.ProductOptions nextProductOptions = new Offer.ProductOptions();
            Map<String, WebElement> productOptionsMap = new HashMap<>();

            for(WebElement option : options){
                String name = option.findElement(By.xpath(Locators.productOptionButton)).getText();
                productOptionsMap.put(name, option);

                if(name.equals("O-Book")){
                    nextProductOptions.hasObookVersion = true;
                    logger.info("The offer has a OBook option ");
                };
            }

            nextProductOptions.productOptionsMap = productOptionsMap;
            return nextProductOptions;
        }

        private static boolean checkObookOption(WebElement offer){
            List<WebElement> searchResults = offer.findElements(By.xpath(Locators.oBookOptionButton));
            if (searchResults.size() > 0){
                return true;
            }
            return false;
        }

        public static List<String> getOffersNames() throws Exception {
            getOffers();
            return offersNames;
        }

        public static int getNumberOfOffers() throws Exception {
            getOffers();
            return offers.size();
        }

        private static void waitOfferListToBecomeStable() throws Exception {
            int oldNumber = DriverFabric.getDriver().findElements(By.xpath(offerLocator)).size();
            int newNumber = 0;
            boolean stable = false;
            for(int i = 0; i < 10; i++){
                Thread.sleep(1000L);
                newNumber = DriverFabric.getDriver().findElements(By.xpath(offerLocator)).size();
                if(newNumber==oldNumber){
                    stable = true;
                    break;
                }
                oldNumber = newNumber;
            }

            if(!stable){
                throw new Exception("Offer list is not stable after 10 attepmts");
            }
        }

        public static List<Offer> getOffers() throws Exception {
            logger.info("Getting offers");

            if(initiated){
                return offers;
            }

            waitOfferListToBecomeStable();

            List<WebElement> rawOffers = DriverFabric.getDriver().findElements(By.xpath(offerLocator));
            offersNames = new LinkedList<>();
            numberOfOffers = 0;
            for(WebElement rawOffer: rawOffers){

                Offer nextOffer = new Offer(rawOffer);

                List<WebElement> rawProductOptions = rawOffer.findElements(By.xpath(Locators.groupNameWithPrice));
                Offer.AddToCartButtonInfo AddToCartButtonInfo = checkAddToCartButton(nextOffer);

                nextOffer.name = rawOffer.findElement(By.xpath(Locators.productTitle)).getText();
                nextOffer.productOptions = getProductOptionsAndCheckThem(rawProductOptions);
                nextOffer.hasAddToCartButton = AddToCartButtonInfo.isPresented;
                nextOffer.addToCartButtonIsVisible = AddToCartButtonInfo.isVisible;
                nextOffer.hasObookOption = checkObookOption(rawOffer);

                offersNames.add(nextOffer.name);
                offers.add(nextOffer);
            }
            initiated = true;
            return offers;
        }

        public static List<Offer> refreshAndGetOffers() throws Exception {
            initiated = false;
            return getOffers();
        }
    }
}