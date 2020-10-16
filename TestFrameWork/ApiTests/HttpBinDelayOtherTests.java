package TestFrameWork.ApiTests;

import TestFrameWork.ApiTests.Commons.Commons;
import TestFrameWork.ApiTests.Commons.EndPoints;
import TestFrameWork.ApiTests.Commons.Structures.HttpBinPostDelayRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;

import static TestFrameWork.ApiTests.Commons.TestData.HttpBinTests.HttpBinDelayTest.ExpectedStatusCodesWithStrings;
import static TestFrameWork.ApiTests.Commons.TestData.HttpBinTests.HttpBinDelayTest.StringDelays;

@RunWith(value = Parameterized.class)
public class HttpBinDelayOtherTests {

    int number;
    HttpBinPostDelayRequest steps =  new HttpBinPostDelayRequest();

    public HttpBinDelayOtherTests(int number){
        this.number = number;
    }

    @Parameters
    public static List<Integer[]> data() {
        return Commons.getParametersIndexes(ExpectedStatusCodesWithStrings.size());
    }

    @Test
    public void HttpBinDelayStringTest() {
        steps.requestUrl = String.format("%s/%s", EndPoints.HttpBinEnpoints.httpBinDelayUrl, StringDelays.get(number));
        steps.sendPostRequest();
        //steps.compareStatusCode(ExpectedStatusCodesWithStrings.get(number));
        steps.compareType("text/html");
    }
}
