Feature: Updating a pet

  Background:
    When the user adds a new pet with "Ralf" name, "Dog", and "available" status
    Then the pet name is "Ralf"

  Scenario: User updates a pet
    When the user updates Ralf status to "sold"
    Then Ralf status is "sold"

    Scenario: User deletes a pet
      When the user deletes Ralf
      Then Ralf is deleted
