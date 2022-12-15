package com.revature.runners;
import io.cucumber.junit.CucumberOptions;


@CucumberOptions(features = "classpath:features", glue = "com.revature.stepimplementations", tags = "@checkout")
public class Runner {}


