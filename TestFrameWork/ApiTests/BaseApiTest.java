package TestFrameWork.ApiTests;

import TestFrameWork.Commons.LoggerFabric;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class BaseApiTest {
    static LoggerFabric.Logger logger = LoggerFabric.getLogger();

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            logger.info(String.format("=======\nStarting test: %s ", description.getMethodName()));
        }
    };

    @AfterClass
    public static void afterClassScript(){
        logger.info("========\n\n\n");
    }

}
