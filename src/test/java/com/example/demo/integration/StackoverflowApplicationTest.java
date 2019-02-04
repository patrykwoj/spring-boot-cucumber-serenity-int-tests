package com.example.demo.integration;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features/RetrievalOfProducts.feature"})
public class StackoverflowApplicationTest {

}

