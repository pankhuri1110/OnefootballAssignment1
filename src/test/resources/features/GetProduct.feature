Feature: Get Product Details
  Get Product Details using Get Request

  Scenario Outline: Product details fetching using Get Request
    Given User is having details <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image> to create new product
    When User create new product using API
    Then User should get <statusCode> and validate response body fields with <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image>
    When User fetches product details for Id using Get API
    Then User should get <getProductstatusCode> and validate response body fields with <total>, <limit>, <skip>, <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image>

    Examples:
      | name       | type       | price  | shipping | upc            | description                                         | manufacturer | model       | url                                                | image                                        | total | limit | skip | statusCode | getProductstatusCode |
      | "Duracell" | "HardGood" | 100.56 | 0.01     | "041333440019" | "Compatible with select electronic devices; C size" | "Duracell"   | "MN1400R4Z" | "http://www.bestbuy.com/site/duracell-c-batteries" | "http://img.bbystatic.com/BestBuy_US/images" | 1     | 10    | 0    | 201        | 200                  |