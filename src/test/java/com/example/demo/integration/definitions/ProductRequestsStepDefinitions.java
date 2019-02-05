package com.example.demo.integration.definitions;

import com.example.demo.integration.definitions.steps.ProductApiSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ProductRequestsStepDefinitions extends CucumberRoot {

	@Steps
	ProductApiSteps shopApi;

	@When("^I get list of products from (.*) store$")
	public void user_requests_product_list_from_store(String store) {
		shopApi.getProductsByStoreId(store);
	}

	@When("^I get product with id (\\d+)$")
	public void user_requests_product_by_id(Integer productId) {
		shopApi.getProductById(productId);
	}

	@And("^response contains (\\d+) products$")
	public void response_contains_number_of_products(Integer numberOfProducts) {
		shopApi.responseContainsNumberOfProducts(numberOfProducts);
	}

	@And("^response contains product name \"(.*)\"$")
	public void response_contains_product_named(String productName) {
		shopApi.responseContainsProductNamed(productName);
	}
}
