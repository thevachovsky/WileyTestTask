package TestFrameWork.ApiTests;

import TestFrameWork.ApiTests.Commons.Commons;
import TestFrameWork.ApiTests.Commons.Structures.HttpBinPostDelayRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;

import static TestFrameWork.ApiTests.Commons.TestData.HttpBinTests.HttpBinDelayTest.*;


@RunWith(value = Parameterized.class)
public class HttpBinDelayIntegerTests {
    HttpBinPostDelayRequest steps =  new HttpBinPostDelayRequest();
    private int number;

    public HttpBinDelayIntegerTests(int number){
        this.number = number;
    }

    @Parameters
    public static List<Integer[]> data() {
        return Commons.getParametersIndexes(ExpectedStatusCodes.size());
    }

    @Test
    public void HttpBinDelayIntegerTest() {
        steps.delay = Delays.get(number);
        steps.sendPostRequest();
        steps.compareStatusCode(ExpectedStatusCodes.get(number));
        steps.compareDelay(ExpectedDelays.get(number));
        steps.compareType("application/json");
    }
}
