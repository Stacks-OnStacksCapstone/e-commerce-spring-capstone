package com.revature.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/ProductFeatures/DisplayProducts.feature"},
        glue = {"com.revature.stepsImplementation.products"})
public class Runner  {

}