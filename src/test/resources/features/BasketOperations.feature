Feature: As a shop user I want to be able to add selected products to basket

Scenario: Add product from product list to empty basket
	Given that I am logged in as client
	And I get list of products from 1A store
	When I select first product from a list
	And I add 1 pieces of selected product to the basket
	Then I receive a basket summary
	And basket summary has basketId set
	And basket contains added product
	And total price is calculated
	And response status is 201
	
Scenario: Add several products from product list to basket
	Given that I am logged in as client
	And have a basket prefilled with products
	And I get list of products from 1B store
	When I select first product from the list
	And I add 2 pieces of selected product to the basket
	Then I receive a basket summary
	And basket summary has baskedId not changed
	And basket contains prefilled products
	And basket contains added product
	And total price is calculated
	And response status is 201