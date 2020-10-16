package TestFrameWork.WebTests;

import TestFrameWork.Commons.LoggerFabric;
import TestFrameWork.WebTests.Commons.DriverFabric;
import TestFrameWork.WebTests.UserLogic.AllSteps;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class BaseWebTest extends AllSteps{
    static String url = "Implement it in you class";
    static LoggerFabric.Logger logger = LoggerFabric.getLogger();

    @Before
    public void  beforeScript(){
        DriverFabric.refreshDriver();
        DriverFabric.getDriver().get(url);
        pageSpecificBeforeActions();
    }

    @After
    public void afterScript(){
            DriverFabric.getDriver().quit();
        }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            logger.info(String.format("\n\n=======\nStarting test: %s ", description.getMethodName()));
        }
    };

    @AfterClass
    public static void afterClassScript(){
        logger.info("========\n\n\n");
    }

    public void pageSpecificBeforeActions(){
    }

}

