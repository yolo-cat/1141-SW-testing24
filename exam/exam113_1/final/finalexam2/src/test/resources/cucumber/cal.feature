Feature:Feecalculate
  Scenario:normal,before,20,N,
    Given I am on the Gymfee calculator page
    When I select "Monday"
    And I select before 7
    And I send age "20"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $160.00."
  Scenario:weekend,before,20,N,
    Given I am on the Gymfee calculator page
    When I select "Sunday"
    And I select before 7
    And I send age "20"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $200.00."
  Scenario:normal,after,20,N,
    Given I am on the Gymfee calculator page
    When I select "Tuesday"
    And I select after 7
    And I send age "20"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $200.00."
  Scenario:weekend,after,20,N,
    Given I am on the Gymfee calculator page
    When I select "Sunday"
    And I select after 7
    And I send age "20"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $250.00."
  Scenario:normal,before,11,N,
    Given I am on the Gymfee calculator page
    When I select "Monday"
    And I select before 7
    And I send age "11"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $160.00."
  Scenario:normal,before,65,N,
    Given I am on the Gymfee calculator page
    When I select "Monday"
    And I select before 7
    And I send age "65"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $160.00."
  Scenario:weekend,after,11,N,
    Given I am on the Gymfee calculator page
    When I select "Sunday"
    And I select after 7
    And I send age "11"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $200.00."
  Scenario:weekend,after,65,N,
    Given I am on the Gymfee calculator page
    When I select "Sunday"
    And I select after 7
    And I send age "65"
    And I select not member
    And I click sendButton
    Then I should see the output is "費用為 $200.00."

  Scenario:normal,after,20,Y,
    Given I am on the Gymfee calculator page
    When I select "Monday"
    And I select after 7
    And I send age "20"
    And I select member
    And I send memberid "IECS-123"
    And I click sendButton
    Then I should see the output is "費用為 $100.00."
  Scenario:weekend,after,20,Y,
    Given I am on the Gymfee calculator page
    When I select "Sunday"
    And I select after 7
    And I send age "20"
    And I select member
    And I send memberid "IECS-123"
    And I click sendButton
    Then I should see the output is "費用為 $125.00."