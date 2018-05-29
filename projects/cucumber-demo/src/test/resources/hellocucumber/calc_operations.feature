Feature: Math operations with calculator
  Everybody wants to do calculations.

  Scenario: Sum of numbers
    Given clean calculator
    When I sum 1 and 2
    Then result should be 3


  Scenario: Multiply numbers
    Given clean calculator
    When I multiply 2 and 2
    Then result should be 4