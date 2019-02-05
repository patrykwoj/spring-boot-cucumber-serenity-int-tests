package com.example.demo.integration.definitions;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.integration.CucumberSerenityDemoTestConfiguration;

@SpringBootTest(classes = CucumberSerenityDemoTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberRoot {

}
