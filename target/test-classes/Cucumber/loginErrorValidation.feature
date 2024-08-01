
@tag
Feature: Title of your feature

  @Error
  Scenario Outline: Login Error Valivation
    Given I landed on Eccomorce Page
    When Login with <username> and <password>
    Then "Incorrect email or password." meassage is displayed on login page

    Examples: 
      | username                |password   | 
      | anjalirkakade@gmail.com |Anjali@12323 | 