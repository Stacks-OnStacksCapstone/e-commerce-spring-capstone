package com.revature.runners;

import com.revature.pages.UserProfilePage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@CucumberOptions(features = "classpath:features", glue = "com.revature.stepsImplementation")
public class Runner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static UserProfilePage userProfilePage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, 20);
        userProfilePage = new UserProfilePage(driver);
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

}
