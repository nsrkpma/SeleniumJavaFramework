
@tag
Feature: Place an order from ecommer site
  I want to use this template for my feature file


	Background:
	Given I landed on ecommerce page
	
  @tag2
  Scenario Outline: Posiitive Test of Sub,ititng the order
    Given Logged in with username <name> and password <password>
    When I add the product <productname> to the cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." messgae is displayed on cinfirmationPage
    
   
    Examples: 
      | name  				 | password |  productname |
      | Kane@gmail.com |Kane@1213 | ZARA COAT 3  |
     