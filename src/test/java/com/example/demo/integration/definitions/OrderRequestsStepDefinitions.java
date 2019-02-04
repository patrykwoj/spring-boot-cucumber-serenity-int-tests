package com.example.demo.integration.definitions;

import com.example.demo.integration.definitions.steps.OrderApiSteps;

import net.thucydides.core.annotations.Steps;

public class OrderRequestsStepDefinitions extends CucumberRoot {

	@Steps
	OrderApiSteps shopApi;
}
