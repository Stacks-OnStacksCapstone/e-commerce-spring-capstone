package com.revature.stepimplementations.userProfile;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeactivateAccountSteps {
    @Given("User is logged in using {string} and {string}")
    public void user_is_logged_in_using_and(String email, String password) {
        Hooks.driver.get("http://localhost:3000/");
        Hooks.userProfilePage.signInLink.click();
        Hooks.userProfilePage.loginEmailInputField.sendKeys(email);
        Hooks.userProfilePage.loginPasswordInputField.sendKeys(password);
        Hooks.userProfilePage.signInButton.click();
    }
    @When("User types nothing in the input field")
    public void user_types_nothing_in_the_input_field() {
        Hooks.userProfilePage.muiDeactivateInputField.click();
    }
    @When("attempt to click on the Deactivate button")
    public void attempt_to_click_on_the_deactivate_button() {
        try {
            Hooks.wait.until(ExpectedConditions.elementToBeClickable(Hooks.userProfilePage.muiDeactivateButton));
            Hooks.boolRes = true;
        } catch (Exception e) {
            Hooks.boolRes = false;
        }
    }
    @Then("the deactivate button is unclickable")
    public void the_deactivate_button_is_unclickable() {
        Assertions.assertEquals(Hooks.boolRes, false);
    }
    @Then("Account is not deactivated and User stays on the Profile page")
    public void account_is_not_deactivated_and_user_stays_on_the_profile_page() {
        String expectedPag = "http://localhost:3000/userProfile";
        String actualPage = Hooks.driver.getCurrentUrl();
        Assertions.assertEquals(expectedPag, actualPage);
    }

    // scenario 2
    @When("User types in {string} in the input field")
    public void user_types_in_in_the_input_field(String deactivate) {
        Hooks.userProfilePage.muiDeactivateInputField.sendKeys(deactivate);
    }
    @When("clicks on the Deactivate button")
    public void clicks_on_the_deactivate_button() {
        Hooks.userProfilePage.muiDeactivateButton.click();
    }
    @Then("The user is logged out, navigated to the Login page")
    public void the_user_is_logged_out_navigated_to_the_login_page() {
        String expectedPge = "http://localhost:3000/login";
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.userProfilePage.loginEmailInputField));
        String actualPage = Hooks.driver.getCurrentUrl();
        Assertions.assertEquals(expectedPge, actualPage);
    }
    @When("User types in again their email and password to login")
    public void user_types_in_again_their_email_and_password_to_login() {
        Hooks.userProfilePage.loginEmailInputField.sendKeys("mark@gmail.com");
        Hooks.userProfilePage.loginPasswordInputField.sendKeys("password");
        Hooks.userProfilePage.signInButton.click();
    }
    @Then("An Alert for re-attempting to login says {string}")
    public void an_alert_for_re_attempting_to_login_says(String expectedAlert) {
        String actualAlert = Hooks.wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(Hooks.userProfilePage.deactivateAccountAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }
}