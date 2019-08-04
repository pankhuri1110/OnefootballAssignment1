Feature: Create New Product
  Create New Product Using Post Request

  Scenario Outline: New Product Creation using Post Request
    Given User is having details <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image> to create new product
    When User create new product using API
    Then User should get <statusCode> and validate response body fields with <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image>

    Examples:
      | name       | type       | price  | shipping | upc            | description                                         | manufacturer | model       | url                                                | image                                        | statusCode |
      | "Duracell" | "HardGood" | 100.56 | 0.01     | "041333440019" | "Compatible with select electronic devices; C size" | "Duracell"   | "MN1400R4Z" | "http://www.bestbuy.com/site/duracell-c-batteries" | "http://img.bbystatic.com/BestBuy_US/images" | 201        |


  Scenario Outline: New Product Creation using Post Request with Invalid Parameters
    Given User is having details <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image> to create new product
    When User create new product using API
    Then User should get <statusCode> and validate response body fields with <errorName>, <message>, <code>, <className>, <errors>

    Examples:
      | name       | type       | price  | shipping | upc                | description                                         | manufacturer | model       | url                                           | image                                 | statusCode | errorName    | message              | code | className     | errors                                          |
      | "Duracell" | "HardGood" | 100.56 | 0.01     | "0413334400191234" | "Compatible with select electronic devices; C size" | "Duracell"   | "MN1400R4Z" | "http://www.bestbuy.com/duracell-c-batteries" | "http://img.bbystatic.com/BestBuy_US" | 400        | "BadRequest" | "Invalid Parameters" | 400  | "bad-request" | "'upc' should NOT be longer than 15 characters" |
