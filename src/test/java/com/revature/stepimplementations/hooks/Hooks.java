package com.revature.stepimplementations.hooks;

import com.revature.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static LoginPage loginPage;
    public static FrontPage frontPage;
    public static OrdersPage orderPage;
    public static ProductDetailsPage productDetailsPage;
    public static ProfilePage profilePage;
    public static CartPage cartPage;
    public static Actions actions;
    public static UserProfilePage userProfilePage;
    public static ProductDetailsViewPage productDetailsViewPage;
    public static NotificationsPage notificationsPage;
    public static boolean boolRes;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        frontPage = new FrontPage(driver);
        orderPage = new OrdersPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        profilePage = new ProfilePage(driver);
        cartPage= new CartPage(driver);
        userProfilePage = new UserProfilePage(driver);
        productDetailsViewPage = new ProductDetailsViewPage(driver);
        notificationsPage = new NotificationsPage(driver);
    }

    @After
    public void cleanup() {
        driver.quit();
    }


}