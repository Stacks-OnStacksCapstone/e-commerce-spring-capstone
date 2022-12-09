package com.revature.stepsImplementation.userProfile;

import com.revature.pages.ProductDetailsViewPage;
import com.revature.pages.UserProfilePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileHooks {

    public static WebDriver driver;
    public static Actions actions;
    public static WebDriverWait wait;
    public static UserProfilePage userProfilePage;
    public static ProductDetailsViewPage productDetailsViewPage;
    public static boolean boolRes;

    @Before
    public void setUp() {
        driver = new SafariDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 20);
        userProfilePage = new UserProfilePage(driver);
        productDetailsViewPage = new ProductDetailsViewPage(driver);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
