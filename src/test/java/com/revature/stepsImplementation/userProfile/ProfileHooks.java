package com.revature.stepsImplementation.userProfile;

import com.revature.pages.NotificationsPage;
import com.revature.pages.ProductDetailsViewPage;
import com.revature.pages.UserProfilePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileHooks {

    public static WebDriver driver;
    public static Actions actions;
    public static WebDriverWait wait;
    public static UserProfilePage userProfilePage;
    public static ProductDetailsViewPage productDetailsViewPage;
    public static NotificationsPage notificationsPage;
    public static boolean boolRes;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new SafariDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        userProfilePage = new UserProfilePage(driver);
        productDetailsViewPage = new ProductDetailsViewPage(driver);
        notificationsPage = new NotificationsPage(driver);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
