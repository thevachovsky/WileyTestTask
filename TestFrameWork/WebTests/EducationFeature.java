package TestFrameWork.WebTests;


import TestFrameWork.WebTests.Commons.TestData;
import org.junit.Test;

public class EducationFeature extends MainPageTest {

    @Test
    public void testEducationSubjects() throws Exception {
        placeCursorOnSubjectsMenu();
        checkIfSubjectsOptionPresent("Education");
        selectSubjectsOption("Education");
        checkElementsOfSubjectsSubMenu("Education", TestData.EducationFeature.expectedEducationOptions);
        logger.info("testEducationSubjects succesfully finished");
    }
}

