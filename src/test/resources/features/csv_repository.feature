Feature: CSV Repository

  A CSV class that manages given CSV file.

  Scenario:
    Given CSV Repository
    And a list of rows
      | NAME                                     | PRICE  | RATING |
      | Smartwatch Samsung Galaxy Watch Active 2 | 119,00 | 5,5    |
      | Smartwatch Huawei Watch GT 2 Pro czarny  | 200,00 | 5,0    |
      | Smartwatch Xiaomi Mi Watch Navy          | 300,00 | 2,5    |
    When CSV Repository saves data to a file
    Then the contents inside the file are the same as the list of rows
