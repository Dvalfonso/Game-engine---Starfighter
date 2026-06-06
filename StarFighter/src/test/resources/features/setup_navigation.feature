Feature: Setup Navigation

  Scenario: Advance one state with play
    Given the game is in not-started state
    When I execute play
    Then the state is weapon setup

  Scenario: setupNext advances multiple states
    Given I am in weapon setup
    When I execute setupNext(2)
    Then the state is engine setup

  Scenario: setupBack goes back multiple states and can exit setup
    Given I am in armour setup
    When I execute setupBack(2)
    Then the state is not-started setup

  Scenario: setupBack cannot go before not-started
    Given the game is in not-started state
    When I execute setupBack(3)
    Then the state is not-started setup