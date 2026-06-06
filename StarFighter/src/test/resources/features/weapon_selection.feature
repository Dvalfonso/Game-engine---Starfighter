Feature: Equipment selection

    Scenario: Valid weapon selection
        Given I am on the weapon setup stage
        When I select weapon 3
        Then the selected weapon is option 3

    Scenario: Invalid weapon selection resets weapon to default
        Given I am on the weapon setup stage
        When I select weapon option 99
        Then the selected weapon is option 1
