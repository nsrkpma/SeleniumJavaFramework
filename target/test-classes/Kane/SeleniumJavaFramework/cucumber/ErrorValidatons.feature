Feature: Error Validation on Login page
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Posiitive Test of Sub,ititng the order
  	Given I landed on ecommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." messgae is displayed

     Examples: 
      | name  				 | password |
      | Kane@gmail.com |Kane@2211213 |
