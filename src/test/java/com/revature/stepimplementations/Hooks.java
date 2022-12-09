package com.revature.stepimplementations;

import com.revature.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    public static WebDriver driver;
    public static LoginPage loginPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @After
    public void cleanup() {
        driver.quit();
    }
}
