package TestFrameWork.WebTests.Commons;

import TestFrameWork.Commons.LoggerFabric;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFabric {

    private static WebDriver driver;

    public static void refreshDriver(){
        LoggerFabric.Logger logger = LoggerFabric.getLogger();
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumJava\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        logger.info("Driver restarted");
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
