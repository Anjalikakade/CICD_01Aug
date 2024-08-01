
@tag
Feature: Submit Order on Ecomorce link
Background:
Given I landed on Eccomorce Page


  @tag2
  Scenario Outline: Add a Item and Verify Product Added Successfully
    Given Login with <username> and <password>
    When I Add product <ProductName> on cart list
    And checkout <ProductName> and Submit the Order
    Then "THANKYOU FOR THE ORDER." meassage is displayed on confirmation page

    Examples: 
      | username                |password   | ProductName    |
      | anjalirkakade@gmail.com |Anjali@123 | IPHONE 13 PRO  |
      |kakade@gmail.com         |Anjali@123 | ADIDAS ORIGINAL|
      
