package com.revature.stepimplementations.products;

import com.revature.pages.EditProductPage;
import com.revature.pages.ProductsDisplayPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Products {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static ProductsDisplayPage ProductsDisplayPage;
    public static EditProductPage EditProductPage;

    @Before
    public static void Setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ProductsDisplayPage = new ProductsDisplayPage(driver);
        EditProductPage = new EditProductPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @After
    public static void teardown() {
        driver.quit();
    }
}
