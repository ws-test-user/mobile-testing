Feature: Math operations with calculator

  Scenario: Sum of numbers
    Given clean calculator
    When I sum 1 and 2
    Then result should be 3

  Scenario Outline: Multiply numbers
    Given clean calculator
    When multiply <num1> and <num2>
    Then result should be <result>
    Examples:
      | num1 | num2 | result |
      | 2    | 3    | 6      |
      | 3    | 3    | 9      |
