//package com.revature.stepimplementations.hooks;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class Products {
//    public static WebDriver driver;
//    public static WebDriverWait wait;
//
//    @Before
//    public static void Setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//    }
//
//    @After
//    public static void teardown() {
//        driver.quit();
//    }
//}
