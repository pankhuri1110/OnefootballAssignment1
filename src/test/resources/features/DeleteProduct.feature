Feature: Delete Product Details
  Delete Product Details using Delete Request

  Scenario Outline: Product details delete using Delete Request
    Given User is having details <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image> to create new product
    When User create new product using API
    Then User should get <statusCode> and validate response body fields with <name>, <type>, <price>, <shipping>, <upc>, <description>, <manufacturer>, <model>, <url>, <image>
    When User delete product details for Id using Get API
    Then User should get <getProductDeleteStatusCode> and validate response is empty

    Examples:
      | name       | type       | price  | shipping | upc            | description                                         | manufacturer | model       | url                                                | image                                        | total | limit | skip | statusCode | getProductDeleteStatusCode |
      | "Duracell" | "HardGood" | 100.56 | 0.01     | "041333440019" | "Compatible with select electronic devices; C size" | "Duracell"   | "MN1400R4Z" | "http://www.bestbuy.com/site/duracell-c-batteries" | "http://img.bbystatic.com/BestBuy_US/images" | 1     | 10    | 0    | 201        | 200                        |


  Scenario Outline: Product details fetching using Get Request after Delete Product
    Given User already deleted product and having Id of the product
    When User fetches product details for Id using Get API
    Then User should get <getProductstatusCode> and validate response body fields with <total>, <limit>, <skip> and data list should be empty

    Examples:
      | total | limit | skip | getProductstatusCode |
      | 0     | 10    | 0    | 200                  |