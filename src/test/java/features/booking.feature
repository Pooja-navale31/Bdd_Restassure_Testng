Feature: Booking API CRUD operations
  Scenario: Get all booking
    When I get all booking
    Then the response status code should be 200

  Scenario: Get a booking by id
    When I get booking by id
    Then the response status code should be 200

  Scenario: Update a booking by id
    When I update booking by id
    Then the response status code should be 200

  Scenario: Delete a booking by id
    When I delete booking by id
    Then the response status code should be 200


  Scenario: Create bookings for all users
    When I create bookings for all users in Excel
    Then the response status code should be 201