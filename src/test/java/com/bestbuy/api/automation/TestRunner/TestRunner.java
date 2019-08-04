package com.bestbuy.api.automation.TestRunner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(features = "src/test/resources/features/",
        glue = {"com.bestbuy.api.automation.steps"})

public class TestRunner {
}

