@api
Feature: API Login

Scenario: Get token from API
  Given API signup is triggered
  Then API response should be success
  Then performance test is executed with threshold 2500 ms