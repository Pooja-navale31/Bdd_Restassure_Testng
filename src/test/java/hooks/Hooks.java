package hooks;

import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import utils.ExtentReportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Hooks {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> scenarioThread = new ThreadLocal<>();

    @Before
    public void setup(Scenario scenario) {
        System.out.println("==== Starting Scenario ====");

        String baseUrl = ConfigReader.getProperty("baseUrl");
        System.out.println("Base URL: " + baseUrl);
        RestAssured.baseURI = baseUrl;

        // Initialize ExtentReports once
        if (extent == null) {
            extent = ExtentReportManager.getInstance();
        }

        // Create test for this scenario
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioThread.set(test);

        test.info("Scenario started: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest test = scenarioThread.get();

        if (scenario.isFailed()) {
            test.fail("Scenario Failed: " + scenario.getName());
        } else {
            test.pass("Scenario Passed: " + scenario.getName());
        }

        extent.flush();

        System.out.println("==== Ending Scenario ====");
    }

    public static ExtentTest getScenarioTest() {
        return scenarioThread.get();
    }
}
