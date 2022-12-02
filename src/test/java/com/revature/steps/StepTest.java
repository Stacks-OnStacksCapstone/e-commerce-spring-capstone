package com.revature.steps;

import com.revature.ECommerceApplication;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = ECommerceApplication.class)
public class StepTest {

    private static WebDriver driver;

    @BeforeAll
    public static void initialize() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void cleanup() {
        driver.quit();
    }

    @Given("I am testing")
    public void i_am_testing() throws InterruptedException {
        driver.get("http://localhost:4200");
        Thread.sleep(5000);
    }
}
