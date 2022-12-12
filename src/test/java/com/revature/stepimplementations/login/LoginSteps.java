package com.revature.stepimplementations.login;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginSteps {



    @Given("the user is on the {string} page")
    public void theUserIsOnTheLoginPage(String page) {
        String expectedURL = null;
        switch (page) {
            case "Login":
                expectedURL = Hooks.loginURL;
                break;
            case "Home":
                expectedURL = Hooks.homeURL;
                break;
            case "Registration":
                expectedURL = Hooks.registrationURL;
                break;
        }
        Hooks.driver.get(expectedURL);
        Assertions.assertEquals(expectedURL, Hooks.driver.getCurrentUrl());
    }

    @When("the user enters {string} as email")
    public void theUserEntersAsUsername(String email) {
        Hooks.loginPage.emailInput.sendKeys(email);
    }

    @And("the user enters {string} as password")
    public void theUserEntersAsPassword(String password) {
        Hooks.loginPage.passwordInput.sendKeys(password);
    }

    @And("the user clicks on the login button")
    public void theUserClicksOnTheLoginButton() {
        Hooks.loginPage.loginButton.click();
    }

    @Then("the user should see alert message {string}")
    public void theUserShouldSee(String expectedMessage) {
        // wait for the alert to be visible
        Hooks.wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.loginPage.message));
        String message = Hooks.loginPage.message.getText();
        Assertions.assertEquals(expectedMessage, message);
    }

    @Then("the user should be redirected to the {string} page")
    public void theUserShouldBeRedirectedToThePage(String page) {
        String expectedURL = null;
        switch (page) {
            case "Login":
                expectedURL = Hooks.loginURL;
                break;
            case "Home":
                expectedURL = Hooks.homeURL;
                break;
            case "Registraion":
                expectedURL = Hooks.registrationURL;
                break;
        }
        Hooks.wait.until(ExpectedConditions.urlToBe(expectedURL));
        Assertions.assertEquals(expectedURL, Hooks.driver.getCurrentUrl());
    }
}
