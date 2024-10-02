@tag
Feature: To make a shopping for men's belts and acessories

  Background: Browser launch condition
    Given I want to launch the "browser"
    Given I want to hit the "qa_url"

  @alpha @smoke
  Scenario Outline: User want to purchase a casual Belt for men
    And User close the popup
    When User mouseover to the mens belt section and selects "<product>"
    And user select the model as "<product_Specific>"
    And User select the colour as "<Colour>" and size as "<size>"

    Examples: 
      | product      | product_Specific                 | Colour | size |
      | Formal Belts | MILLER MEN'S LEATHER FORMAL BELT | Brown  | L    |

  #| Casual Belts | ELLISON MEN'S CASUAL LEATHER BELT  | Brown    | L    |
  #| Casual Belts | WEYBOURNE 39mm CASUAL LEATHER BELT | Dark Tan | L    |
  #| Casual Belts | WEYBOURNE 39mm CASUAL LEATHER BELT | Brown    | L    |
 
  @error_validation @regression
  Scenario Outline: User want to enter the invalid detail in the seach box and needed to validate the message
    And User close the popup
    When user enters the data in the search box "<data>"
    And user validates the error messages from "<filename>" with key as "<Key>"

    Examples: 
      | product | data   | filename | Key    |
      | null    | Harriet | brits    | search |
| null    | Baguet | brits    | search |
      #| null    | gayu   | brits    | search |
      #| null    | netaji | brits    | search |
      #| null    | aldi   | brits    | search |
      #| null    | asda   | brits    | search |

