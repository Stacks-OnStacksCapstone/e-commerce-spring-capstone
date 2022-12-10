//package com.revature.stepimplementations.hooks;
//
//import com.revature.pages.CartPage;
//import com.revature.pages.ProfilePage;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//
//public class PaymentHooks {
//    public static WebDriver driver;
//    public static ProfilePage profilePage;
//    public static CartPage cartPage;
//    public static Actions actions;
//
//    public static WebDriverWait wait;
//
//
//
//    @Before
//    public static void setup() {
//
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        actions = new Actions(driver);
//        profilePage = new ProfilePage(driver);
//        cartPage= new CartPage(driver);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//
////        driver = new SafariDriver();
////        profilePage = new ProfilePage(driver);
////        wait = new WebDriverWait(driver, 40);
//    }
//
//    @After
//    public static void teardown() {
//        driver.quit();
//    }
//
//
//}