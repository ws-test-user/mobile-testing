Feature: Calculator math operations
  Everybody wants to sum/divide/multiply/subtract numbers.

  Scenario: Sum of numbers
    Given clean calculator
    When I sum 1 and 2
    Then result should be 3

  Scenario: Multiply numbers
    Given clean calculator
    When I multiple 2 and 2
    Then result should be 4