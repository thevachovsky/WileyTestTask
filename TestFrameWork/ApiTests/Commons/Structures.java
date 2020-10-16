package TestFrameWork.ApiTests.Commons;

import TestFrameWork.Commons.LoggerFabric;
import io.restassured.response.Response;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Structures {
    static LoggerFabric.Logger logger = LoggerFabric.getLogger();

    public static class AutocompleteGetRequest{
        public static class Page{
            public String id;
            public Double boost;
            public String title;
            public String content;
        }

        public List<Map<String, String>> suggestions;
        public List<String> products;
        public Boolean showSeeAllProducts;
        public Boolean showSeeAllPages;
        public List<Page> pages;
        public int suggestionsWithTermAndHighlightedJava = 0;
        public int pagesWithMentionedWiley = 0;
        private AutocompleteGetRequest answer=null;
        private final String requestUrl = EndPoints.WilleyEnpoints.autoCompleteTestUrl;


        public void sendRequest(){
            Response response = given().get(requestUrl);
            answer = response.as(AutocompleteGetRequest.class);
        }

        private void checkIfSuggestionHasTermIncludingHighlightedJava(Map<String, String> suggestion){
            logger.info("Looking for terms with Wiley");
            for(String key: suggestion.keySet()){
                if(key.equals("term")&&suggestion.get(key).contains("<span class=\"search-highlight\">java</span>")){
                    suggestionsWithTermAndHighlightedJava++;
                }
            }
        }

        public  void checkSugestions(){
            for(Map<String, String> suggestion : suggestions){
                checkIfSuggestionHasTermIncludingHighlightedJava(suggestion);
                }
            }

        public void checkPages(){
            logger.info("Looking for pages with Wiley");
            for(Page page : pages){
                if(page.title.contains("Wiley")){
                    logger.info(String.format("This one contains 'Wiley': %s", page.title));
                    pagesWithMentionedWiley++;
                };
            }
        }

        public void parseAnswer(){
            checkPages();
            checkSugestions();
        }
    }

    public abstract static class HttpBinRequest {
        protected Response response;
        public String requestUrl;
        public Double delay;

        protected abstract String getUrl();

        public void compareDelay(double maximum){
            double responseDelay = response.getTime()/1000d;
            assertTrue("Delay is lower than requested", responseDelay > delay/1000d);
            assertTrue(String.format("Delay(%.2f) is bigger than maximum:(%.2f)", responseDelay, maximum),responseDelay < maximum);
            logger.info(String.format("Delay %.2f is in expected range: %.2f", responseDelay, maximum));
        }

        public void compareType(String expected){
            assertEquals(String.format("Response has unexpected Content-Type: %s. %s is expected.",
                    response.getContentType(), expected),
                    response.getContentType(), expected);

            logger.info(String.format("Response has expected Content-Type: %s", expected));
        }

        public void compareStatusCode(int expected){
            assertEquals(String.format("Response has unexpected status code: %d. %d is expected",
                    response.getStatusCode(), expected),
                    response.getStatusCode(), expected);
            logger.info(String.format("Response has expected status code: %d", expected));
        }
    }

    public abstract static class HttpBinPostRequest extends HttpBinRequest{
        public void sendPostRequest() {
            logger.info(String.format("Sending post request to %s", getUrl()));
            response = given().post(getUrl());
            logger.info(response.getHeader("Content-Type"));
        }
    }

    public static class HttpBinPostDelayRequest extends HttpBinPostRequest{
        @Override
        protected String getUrl(){
            if(requestUrl == null) {
                int intDelay = delay.intValue();
                requestUrl = String.format("%s/%d",EndPoints.HttpBinEnpoints.httpBinDelayUrl, intDelay);
            }
            return requestUrl;
        }
    }

    public abstract static class HttpBinGetRequest extends HttpBinRequest{
        public String requestUrl;
        public Double delay;

        protected abstract String getUrl();

        public void sendGetRequest() {
            logger.info(String.format("Sending get request to %s", getUrl()));
            response = given().get(getUrl());
        }
    }

    public abstract static class HttpBinGetFileRequest extends HttpBinGetRequest{
        @Override
        public void sendGetRequest() {
            logger.info(String.format("Sending get request to %s", getUrl()));
            response = given().when().get(getUrl()).andReturn();
        }

        public void compareMD5(String expected) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(response.body().asByteArray());
            byte[]digest = md.digest();
            String myChecksum = DatatypeConverter
                    .printHexBinary(digest);

            assertEquals("CheckSum is not expected", myChecksum, expected.toUpperCase());
            logger.info("CheckSum is expected");
        }

        protected boolean checkFirstByteToCheckType(String fileType, int[] fBytes){
            Map<String, int[]> firstBytesToType = new HashMap<>();
            firstBytesToType.put("PNG", new int[]{-119, 80, 78, 71});
            return Arrays.equals(firstBytesToType.get(fileType.toUpperCase()), fBytes);
        }

        protected int[] getFirstBytes(){
            byte[] fbytes = Arrays.copyOfRange(response.body().asByteArray(), 0, 4);
            int[] ints = new int[4];
            for(int i = 0; i < ints.length; i++){
                ints[i] = fbytes[i];
            }
            return ints;
        }
    }

    public static class HttpBinGetPngRequest extends HttpBinGetFileRequest{
        @Override
        protected String getUrl(){
            return EndPoints.HttpBinEnpoints.httpBinImageUrl;
        }

        public void checkIfFileIsPng(){
            assertTrue("Looks like this file is not .png:",
                    checkFirstByteToCheckType("PNG", getFirstBytes()));
            logger.info("This file is .png (Firsts bytes are expected)");
        }
    }
}




