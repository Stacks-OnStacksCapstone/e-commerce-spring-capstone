package com.revature.steps;

import com.revature.runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPositiveSteps {

    ChromeDriver driver = Runner.driver;
    @Given("a user is on the login page.")
    public void a_user_is_on_the_login_page() {
    driver.get("http://localhost:3000/");
        WebDriverWait wait = new WebDriverWait(driver,(7));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/strong")));
        WebElement signIn = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/strong"));
        signIn.click();
    }
    @When("a user enters the correct username")
    public void a_user_enters_the_correct_username() {
     WebDriverWait wait = new WebDriverWait(driver, 5);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/main/div/form/div[1]/div/input")));
    WebElement username = driver.findElement(By.xpath("/html/body/div/main/div/form/div[1]/div/input"));
    username.sendKeys("team1@testing.com");

    }
    @When("a user enters the correct password")
    public void a_user_enters_the_correct_password() {
        WebElement password = driver.findElement(By.xpath("/html/body/div/main/div/form/div[2]/div/input"));
        password.sendKeys("team1");
    }
    @When("a user clicks sign in")
    public void a_user_clicks_login() {
    WebElement signInButton = driver.findElement(By.xpath("/html/body/div/main/div/form/button"));
    signInButton.click();
    }
    @Then("a User should be on the homepage and see a LOGOUT button")
    public void a_user_should_be_on_the_homepage() {
        WebDriverWait wait = new WebDriverWait(driver,7);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[1]/div/div[2]/li/strong")));
        WebElement logout = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/li/strong"));
        Assertions.assertNotNull(logout);
        logout.click();
    }


}
