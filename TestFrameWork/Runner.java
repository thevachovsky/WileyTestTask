package TestFrameWork;


import TestFrameWork.ApiTests.*;
import TestFrameWork.WebTests.*;
import TestFrameWork.Commons.LoggerFabric;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Runner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(
                WhoWeServeFeature.class,
                SearchFeature.class,
                EducationFeature.class,
                HttpBinDelayIntegerTests.class,
                HttpBinDelayOtherTests.class,
                HttpBinGetPngTests.class,
                HttpBinDelayDoubleTests.class
                );
        LoggerFabric.getLogger().info("Failed tests:");
        for (Failure failure : result.getFailures()) {

            LoggerFabric.getLogger().info(String.format("%s\n", failure.toString()));

        }
    }
}