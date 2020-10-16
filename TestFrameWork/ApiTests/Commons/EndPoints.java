package TestFrameWork.ApiTests.Commons;

public class EndPoints {
    static public class WilleyEnpoints{
        static public String autoCompleteTestUrl = "https://www.wiley.com/en-us/search/autocomplete/comp_00001H9J?term=Java";
    }
    static public class HttpBinEnpoints{
        static public String httpBinBaseUrl = "https://httpbin.org/";
        static public String httpBinDelayUrl = String.format("%s/delay", httpBinBaseUrl);
        static public String httpBinImageUrl = String.format("%s/image/png", httpBinBaseUrl);
    }
}
