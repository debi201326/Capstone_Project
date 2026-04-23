@ui
Feature: Login Test

Scenario: Login with testuser1485@example.com
Given user is on login page
When user enters username "testuser1485@example.com" and password "Test@123"
Then login should be "success"

Scenario: Login with wronguser@mail.com
Given user is on login page
When user enters username "wronguser@mail.com" and password "wrong123"
Then login should be "failure"

Scenario: Login with user1@automation.com
Given user is on login page
When user enters username "user1@automation.com" and password "password1"
Then login should be "failure"

Scenario: Login with usertest1485@example.com
Given user is on login page
When user enters username "usertest1485@example.com" and password "Test@123"
Then login should be "success"

