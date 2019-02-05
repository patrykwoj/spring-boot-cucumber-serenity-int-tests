package com.example.demo.integration.definitions;

import com.example.demo.integration.definitions.steps.CommonSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class CommonStepDefinitions extends CucumberRoot {

	@Steps
	CommonSteps commonSteps;
	
	@Given("^that I am logged in as (.*)$")
	public void user_logs_in(String user) {
		commonSteps.login(user);
	}
	
	@Then("^I receive empty response$")
	public void user_receives_empty_response() {
		commonSteps.responseIsEmpty();
	}

	@Then("^I receive not empty response$")
	public void user_receives_not_empty_response() {
		commonSteps.responseIsNotEmpty();
	}
	
	@And("^response status is (\\d+)$")
	public void response_has_http_status(Integer httpStatus) {
		commonSteps.responseHttpStatusEquals(httpStatus);
	}
	
}
