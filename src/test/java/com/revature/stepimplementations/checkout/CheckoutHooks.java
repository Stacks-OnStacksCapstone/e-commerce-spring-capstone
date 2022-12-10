package com.revature.stepimplementations.checkout;

import com.revature.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutHooks {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static LoginPage loginPage;
    public static FrontPage frontPage;
    public static CartPage cartPage;
    public static CheckoutPage checkoutPage;


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        loginPage = new LoginPage(driver);
        frontPage = new FrontPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
