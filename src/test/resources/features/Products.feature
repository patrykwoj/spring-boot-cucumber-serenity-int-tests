Feature: As shop user I want to be able to view all products

Scenario Outline: Get all products from specific store
	Given that I am logged in as client
	When I get list of products from <storeNr> store
	Then I receive response status 200
	And response body is not empty
	And response contains <numberOfProducts> products
	And response contains product name "<productName>"
	Examples:
	| storeNr |numberOfProducts | productName |
	|    1A   |       1         |    Prod 3   |
	|    1B   |       2         |    Prod 1   |
	
Scenario: Get existing product with specific identifier
	Given that I am logged in as client
	When I get product with id 1
	Then I receive response status 200
	And response body is not empty
	And response contains product name "Prod 1"


Scenario: Get non existing product with specific identifier
	Given that I am logged in as client
	When I get product with id 4
	Then I receive response status 400
	And response body is empty