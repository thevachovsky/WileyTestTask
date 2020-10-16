package TestFrameWork.WebTests;

import TestFrameWork.WebTests.Commons.TestData;
import org.junit.Test;

public class WhoWeServeFeature extends MainPageTest {

    @Test
    public void testBeneficiars(){
        checkIfNumberOfBeneficiarsIsExpected(TestData.WhoWeServeFeature.groupsNumber);
        checkIfBeneficiarsGroupsAreExpected(TestData.WhoWeServeFeature.groupsOnPage);
        logger.info("testBeneficiars succesfully finished");

    }
}

