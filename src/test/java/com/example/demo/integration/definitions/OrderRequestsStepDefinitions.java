package com.example.demo.integration.definitions;

import com.example.demo.integration.definitions.steps.OrderApiSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OrderRequestsStepDefinitions extends CucumberRoot {

	@Steps
	OrderApiSteps orderApi;

	@When("^I select first product from response products list$")
	public void user_selects_first_product_from_products_list() {
		orderApi.getFirstProductFromProductApiResponse();
	}

	@And("^I add (\\d+) pieces of selected product to order$")
	public void user_add_product_to_basket(int pieces) {
		orderApi.addSelectedProductToOrder(pieces);
	}
	
	@And("^summary has orderId set$")
	public void users_order_has_orderId() {
		orderApi.orderHasOrderId();
	}
	
	@And("^summary contains added product$")
	public void users_order_contains_added_product() {
		orderApi.orderContainsAddedProduct();
	}
	
	@And("^total price is correctly calculated$")
	public void total_order_price_is_correctly_calculated() {
		orderApi.orderTotalPriceIsCorrect();
	}
	
}
