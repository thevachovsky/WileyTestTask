package TestFrameWork.ApiTests.Commons;

import java.util.Arrays;
import java.util.List;

public class TestData {
    public static class HttpBinTests{
        public static class HttpBinDelayTest{
            static public List<Double> Delays = Arrays.asList(0.0d,1.0d,2.0d,10.0d,12.0d);
            static public List<String> StringWithDoublesDelays = Arrays.asList("0.0","1.0","2.0","10.0","12.0");
            static public List<Double> ExpectedDelays = Arrays.asList(1.3d,2.0d,3.0d,11.0d,11.0d);
            static public List<Integer> ExpectedStatusCodes = Arrays.asList(200, 200, 200, 200, 200);

            static public List<String> StringDelays = Arrays.asList("", "Hello!", "[]", "*");
            static public List<Integer> ExpectedStatusCodesWithStrings = Arrays.asList(404, 500, 500, 500);


        }

    }
}
