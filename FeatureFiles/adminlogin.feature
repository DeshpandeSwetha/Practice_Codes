@tag
Feature: Admin Login
  I want to use this template to check admin login

  @tag1
  Scenario: Check Admin Login with valid credentials
    Given i open browser with url = "http://orangehrm.qedgetech.com"
    Then i should see login page
    When i enter username as "Admin"
    And i enter password as "Qedge123!@#"
    And i click login
    Then i should see admin module
    When i click logout   
    Then i should see login page
    When i close browser
  
  @tag2  
  Scenario Outline: Check AdminLogin with invalid credentials
    Given i open browser with url = "http://orangehrm.qedgetech.com"
    Then i should see login page
    When i enter username as "<username>"
    And i enter password as "<password>"
    And i click login
    Then i should see error message
    When i close browser
    
    Examples:
    |username|password|
    |Admin|xyz|
    |xyz|Qedge123|
    |abc|xyz|