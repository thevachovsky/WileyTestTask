package TestFrameWork.ApiTests;

import TestFrameWork.ApiTests.Commons.Commons;
import TestFrameWork.ApiTests.Commons.EndPoints;
import TestFrameWork.ApiTests.Commons.Structures.HttpBinPostDelayRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;

import static TestFrameWork.ApiTests.Commons.TestData.HttpBinTests.HttpBinDelayTest.*;


@RunWith(value = Parameterized.class)
public class HttpBinDelayDoubleTests {
    HttpBinPostDelayRequest steps =  new HttpBinPostDelayRequest();
    private int number;

    public HttpBinDelayDoubleTests(int number){
        this.number = number;
    }

    @Parameters
    public static List<Integer[]> data() {
        return Commons.getParametersIndexes(ExpectedStatusCodes.size());
    }

    @Test
    public void HttpBinDelayIntegerTest() {
        steps.requestUrl = String.format(
                "%s/%s", EndPoints.HttpBinEnpoints.httpBinDelayUrl, StringWithDoublesDelays.get(number));

        steps.sendPostRequest();
        steps.delay = Delays.get(number);
        steps.compareStatusCode(ExpectedStatusCodes.get(number));
        steps.compareDelay(ExpectedDelays.get(number));
        steps.compareType("application/json");
    }
}
