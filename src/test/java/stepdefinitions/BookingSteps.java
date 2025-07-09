package stepdefinitions;

import io.cucumber.java.en.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ApiUtils;
import utils.ExcelUtils;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingSteps {
    Response response;
    RequestSpecification request;
    static String Id;

    @When("I create bookings for all users in Excel")
    public void createBookingsForAllUsers() {

        List<String[]> allData = ExcelUtils.getCellData("Sheet1");

        for (String[] row : allData) {
            String firstname = row[0];
            String lastname = row[1];

            RequestSpecification request = ApiUtils.getRequestSpec();

            Map<String, Object> payload = new HashMap<>();
            payload.put("firstname", firstname);
            payload.put("lastname", lastname);
            payload.put("totalprice", 90000);
            payload.put("depositpaid", true);
            Map<String, String> dates = new HashMap<>();
            dates.put("checkin", "2025-01-01");
            dates.put("checkout", "2025-01-02");
            payload.put("bookingdates", dates);
            payload.put("additionalneeds", "Jevan");
            payload.put("message","Your booking is confirmed");

            response = request.body(payload).post("/booking");
            int statusCode = response.getStatusCode();
            assertEquals(statusCode, 201, "Booking failed for user: " + firstname + " " + lastname);


        }
    }
 @When("I get all booking")
 public void getAllBookings(){
     response = ApiUtils.getRequestSpec()
             .get("/booking");
     Id = response.jsonPath().getString("id[0]");
     System.out.println("booking ID: " + Id +"used for update and delete");
 }
    @When("I get booking by id")
    public void getBooking() {
        response = ApiUtils.getRequestSpec()
                .get("/booking/" + Id);
    }

    @When("I update booking by id")
    public void updateBooking() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "UpdatedName");
        payload.put("lastname", "UpdatedLast");
        payload.put("totalprice", 456);
        payload.put("depositpaid", false);
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2023-02-01");
        dates.put("checkout", "2023-02-02");
        payload.put("bookingdates", dates);
        payload.put("additionalneeds", "khana");

        response = ApiUtils.getRequestSpec()
                .auth().preemptive().basic("admin", "password123")
                .body(payload)
                .put("/booking/" + Id);
    }

    @When("I delete booking by id")
    public void deleteBooking() {
        response = ApiUtils.getRequestSpec()
                .auth().preemptive().basic("admin", "password123")
                .delete("/booking/" + Id);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code!");
    }
}
