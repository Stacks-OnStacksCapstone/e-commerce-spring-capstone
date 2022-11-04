package com.revature.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/revature/features", glue = "com.revature.steps")
public class Runner {

    public static ChromeDriver driver;

    @BeforeClass
    public static void setup() {
        File chrome = new File("src/test/resources/Drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterClass
    public static void teardown() {
        driver.quit();
    }



}

