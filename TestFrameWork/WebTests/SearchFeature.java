package TestFrameWork.WebTests;

import org.junit.Test;

public class SearchFeature extends MainPageTest {

    @Test
    public void testSearchTips() throws InterruptedException {
        enterPhraseInSearchBox("Java");
        searchIfSearchDropDownListArePresented();
        logger.info("testSearchTips succesfully finished");
    }

    @Test
    public void testSearchResult() throws Exception {
        searchPhrase("Java");
        checkIfAllOfferNamesIncludePhrase("Java");
        checkIfNumberOfOffersIsExpected(10);
        checkObookOptions();
        checkAddToCartButtons();
        logger.info("testSearchResult succesfully finished");
    }
}

