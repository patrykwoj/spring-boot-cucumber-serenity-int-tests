package com.example.demo.integration.definitions;

import com.example.demo.integration.definitions.steps.OrderApiSteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
	
	@Given("^have an existing order prefilled with some products$")
	public void have_an_existing_order_prefilled_with_some_products() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I select first product from the list$")
	public void i_select_first_product_from_the_list() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^summary has orderId not changed$")
	public void summary_has_orderId_not_changed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^summary contains prefilled products$")
	public void summary_contains_prefilled_products() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
