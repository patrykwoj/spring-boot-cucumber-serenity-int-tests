Feature: As a shop user I want to be able to add selected products to my order

Scenario: Add product from product list to empty order
	Given that I am logged in as client
	And I get list of products from 1A store
	When I select first product from response products list
	And I add 1 pieces of selected product to order
	Then I receive response status 201
	And response body is not empty
	And summary has orderId set
	And summary contains added product
	And total price is correctly calculated
	
Scenario: Add several products from product list to existing order
	Given that I am logged in as client
	And have an existing order prefilled with some products
	And I get list of products from 1B store
	When I select first product from the list
	And I add 2 pieces of selected product to order
	Then I receive response status 201
	And response body is not empty
	And summary has orderId not changed
	And summary contains prefilled products
	And summary contains added product
	And total price is correctly calculated