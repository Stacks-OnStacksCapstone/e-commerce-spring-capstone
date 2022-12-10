package com.revature.stepsImplementation.userProfile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeactivateAccountSteps {
    @Given("User is logged in using {string} and {string}")
    public void user_is_logged_in_using_and(String email, String password) {
        ProfileHooks.driver.get("http://localhost:3000/");
        ProfileHooks.userProfilePage.signInLink.click();
        ProfileHooks.userProfilePage.loginEmailInputField.sendKeys(email);
        ProfileHooks.userProfilePage.loginPasswordInputField.sendKeys(password);
        ProfileHooks.userProfilePage.signInButton.click();
    }
    @When("User types nothing in the input field")
    public void user_types_nothing_in_the_input_field() {
        ProfileHooks.userProfilePage.muiDeactivateInputField.click();
    }
    @When("attempt to click on the Deactivate button")
    public void attempt_to_click_on_the_deactivate_button() {
        try {
            ProfileHooks.wait.until(ExpectedConditions.elementToBeClickable(ProfileHooks.userProfilePage.muiDeactivateButton));
            ProfileHooks.boolRes = true;
        } catch (Exception e) {
            ProfileHooks.boolRes = false;
        }
    }
    @Then("the deactivate button is unclickable")
    public void the_deactivate_button_is_unclickable() {
        Assertions.assertEquals(ProfileHooks.boolRes, false);
    }
    @Then("Account is not deactivated and User stays on the Profile page")
    public void account_is_not_deactivated_and_user_stays_on_the_profile_page() {
        String expectedPag = "http://localhost:3000/userProfile";
        String actualPage = ProfileHooks.driver.getCurrentUrl();
        Assertions.assertEquals(expectedPag, actualPage);
    }

    // scenario 2
    @When("User types in {string} in the input field")
    public void user_types_in_in_the_input_field(String deactivate) {
        ProfileHooks.userProfilePage.muiDeactivateInputField.sendKeys(deactivate);
    }
    @When("clicks on the Deactivate button")
    public void clicks_on_the_deactivate_button() {
        ProfileHooks.userProfilePage.muiDeactivateButton.click();
    }
    @Then("The user is logged out, navigated to the Login page")
    public void the_user_is_logged_out_navigated_to_the_login_page() {
        String expectedPge = "http://localhost:3000/login";
        ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.userProfilePage.loginEmailInputField));
        String actualPage = ProfileHooks.driver.getCurrentUrl();
        Assertions.assertEquals(expectedPge, actualPage);
    }
    @When("User types in again their email and password to login")
    public void user_types_in_again_their_email_and_password_to_login() {
        ProfileHooks.userProfilePage.loginEmailInputField.sendKeys("mark@gmail.com");
        ProfileHooks.userProfilePage.loginPasswordInputField.sendKeys("password");
        ProfileHooks.userProfilePage.signInButton.click();
    }
    @Then("An Alert for re-attempting to login says {string}")
    public void an_alert_for_re_attempting_to_login_says(String expectedAlert) {
        String actualAlert = ProfileHooks.wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(ProfileHooks.userProfilePage.deactivateAccountAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }
}