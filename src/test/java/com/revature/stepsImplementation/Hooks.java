package com.revature.stepsImplementation;

import com.revature.pages.ProfilePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Hooks {
    public static WebDriver driver;
    public static ProfilePage profilePage;
    public static Actions actions;

    public static WebDriverWait wait;



    @Before
    public static void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
        profilePage = new ProfilePage(driver);
        wait = new WebDriverWait(driver, 20);

//        driver = new SafariDriver();
//        profilePage = new ProfilePage(driver);
//        wait = new WebDriverWait(driver, 40);
    }

    @After
    public static void teardown() {
        driver.quit();
    }


}