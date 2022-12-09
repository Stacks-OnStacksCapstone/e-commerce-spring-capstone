package com.revature.stepimplementations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {

    public static String loginURL = "http://localhost:3000/login";
    public static String homeURL = "http://localhost:3000/";


    @Given("the user is on the {string} page")
    public void theUserIsOnTheLoginPage(String page) {
        Hooks.driver.get(loginURL);
        Assertions.assertEquals(loginURL, Hooks.driver.getCurrentUrl());
    }

    @When("the user enters {string} as username")
    public void theUserEntersAsUsername(String username) {
        Hooks.loginPage.username.sendKeys(username);
    }

    @And("the user enters {string} as password")
    public void theUserEntersAsPassword(String password) {
        Hooks.loginPage.password.sendKeys(password);
    }

    @And("the user clicks on the login button")
    public void theUserClicksOnTheLoginButton() {
        Hooks.loginPage.loginButton.click();
    }

    @Then("the user should see alert message {string}")
    public void theUserShouldSee(String expectedMessage) {
        // wait for the alert to be visible
        System.out.println("Break");
        new WebDriverWait(Hooks.driver, Duration.ofSeconds(10)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.loginPage.message));
        String message = Hooks.loginPage.message.getText();
        Assertions.assertEquals(expectedMessage, message);
    }

    @Then("the user should be redirected to the {string} page")
    public void theUserShouldBeRedirectedToThePage(String page) {
        new WebDriverWait(Hooks.driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe(homeURL));
        Assertions.assertEquals(homeURL, Hooks.driver.getCurrentUrl());
    }
}
