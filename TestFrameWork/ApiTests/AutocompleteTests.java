package TestFrameWork.ApiTests;
import TestFrameWork.ApiTests.Commons.Structures.AutocompleteGetRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AutocompleteTests {
    AutocompleteGetRequest steps = new AutocompleteGetRequest();

    @Test
    public void AutocompleteTest() {
        steps.sendRequest();
        steps.parseAnswer();

        assertEquals("The number of suggestions with terms with highlighted word 'Java' is not expected",
                4, steps.suggestionsWithTermAndHighlightedJava);

        assertEquals("The number of pages with mentioned Wiley is not expected",
                4, steps.pagesWithMentionedWiley);
    }
}
