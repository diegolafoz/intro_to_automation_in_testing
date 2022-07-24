Feature: Selling a Pet


  Scenario: User adds a pet
    When the user adds a new pet with "Ralf" name, "Dog", and "available" status
    Then the pet name is "Ralf"
