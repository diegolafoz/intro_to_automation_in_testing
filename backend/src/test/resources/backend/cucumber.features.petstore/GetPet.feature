Feature: Getting available pets

  Scenario: User gets available pets
    When the user gets pets with available status
    Then the pets status is "available"