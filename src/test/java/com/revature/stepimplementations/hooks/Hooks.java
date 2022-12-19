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
    public static GeneralPage generalPage;
    public static LoginPage loginPage;
    public static RegistrationPage registrationPage;
    public static PasswordResetPage passwordResetPage;
    public static FrontPage frontPage;
    public static OrdersPage ordersPage;
    public static ProfilePage profilePage;
    public static CartPage cartPage;
    public static Actions actions;
    public static UserProfilePage userProfilePage;
    public static ProductDetailsViewPage productDetailsViewPage;
    public static NotificationsPage notificationsPage;
    public static CheckoutPage checkoutPage;
    public static ProductsDisplayPage ProductsDisplayPage;
    public static EditProductPage EditProductPage;
    public static boolean boolRes;
    public static String homeURL = "http://localhost:3000/";
    public static String loginURL = "http://localhost:3000/login";
    public static String registrationURL = "http://localhost:3000/register";
    public static String passwordResetURL = "http://localhost:3000/forgot-password";
    public static  String profileURL = "http://localhost:3000/userProfile";


    @BeforeAll(order = 1)
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before(order = 2)
    public void setUp() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        generalPage = new GeneralPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordResetPage = new PasswordResetPage(driver);
        frontPage = new FrontPage(driver);
        ordersPage = new OrdersPage(driver);
        profilePage = new ProfilePage(driver);
        cartPage= new CartPage(driver);
        userProfilePage = new UserProfilePage(driver);
        productDetailsViewPage = new ProductDetailsViewPage(driver);
        notificationsPage = new NotificationsPage(driver);
        checkoutPage = new CheckoutPage(driver);
        ProductsDisplayPage = new ProductsDisplayPage(driver);
        EditProductPage = new EditProductPage(driver);

    }

    @After
    public void cleanup() {
        driver.quit();
    }


}
