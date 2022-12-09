package com.revature.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "com.revature.stepsImplementation",tags = "@payment" )

public class Runner {}

//tags = "@productCart"
//"@payment"