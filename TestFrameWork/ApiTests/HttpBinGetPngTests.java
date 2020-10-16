package TestFrameWork.ApiTests;

import TestFrameWork.ApiTests.Commons.Commons;
import TestFrameWork.ApiTests.Commons.Structures.HttpBinGetPngRequest;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static TestFrameWork.ApiTests.Commons.TestData.HttpBinTests.HttpBinDelayTest.ExpectedStatusCodesWithStrings;

public class HttpBinGetPngTests {

    HttpBinGetPngRequest steps = new HttpBinGetPngRequest();

    @Parameters
    public static List<Integer[]> data() {
        return Commons.getParametersIndexes(ExpectedStatusCodesWithStrings.size());
    }

    @Test
    public void HttpBinGetPngTest() throws NoSuchAlgorithmException {
        steps.sendGetRequest();
        steps.compareStatusCode(200);
        steps.compareType("image/png");
        steps.compareMD5("5cca6069f68fbf739fce37e0963f21e7");
        steps.checkIfFileIsPng();
    }
}
