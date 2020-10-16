package TestFrameWork.WebTests;

public class MainPageTest extends BaseWebTest {

    public MainPageTest(){
        url = "https://www.wiley.com/en-us";
    }

    @Override
    public void pageSpecificBeforeActions(){
        clickDontChangeLocationButton();
    }


}

