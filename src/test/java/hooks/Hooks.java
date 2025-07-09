package hooks;

import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    @Before
    public void setup() {
        System.out.println("==== Starting Scenario ====");
        String baseUrl = ConfigReader.getProperty("baseUrl");
        System.out.println("Base URL: " + baseUrl);
        RestAssured.baseURI = baseUrl;
    }

    @After
    public void tearDown() {
        System.out.println("==== Ending Scenario ====");
    }
}
