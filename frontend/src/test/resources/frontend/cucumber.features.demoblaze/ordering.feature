Feature: Ordering of products on Demoblaze e-commerce

  Scenario: Order a product
    Given I visit Demoblaze
    When I click on computers section
    And I click on Sony vaio i5 laptop
    And I click on Add to cart button
    And I accept pop up confirmation
    And I navigate to laptops section
    And Now I click on Dell i7 8gb laptop
    And I click on Add to cart
    And I navigate to cart
    And I delete Dell i7 8gb laptop from cart
    And I click on Place Order
    And I fill in all web form fields
    And I click on Purchase
    And I capture and log purchase Id and Amount
    And I assert purchase amount equals expected
    Then I have ordered the product correctly


