package com.revature.stepsImplementation.orderHistory;

import com.revature.pages.FrontPage;
import com.revature.pages.LoginPage;
import com.revature.pages.OrdersPage;
import com.revature.pages.ProductDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderHistoryHooks {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static LoginPage loginPage;
    public static FrontPage frontPage;
    public static OrdersPage orderPage;
    public static ProductDetailsPage productDetailsPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        loginPage = new LoginPage(driver);
        frontPage = new FrontPage(driver);
        orderPage = new OrdersPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
