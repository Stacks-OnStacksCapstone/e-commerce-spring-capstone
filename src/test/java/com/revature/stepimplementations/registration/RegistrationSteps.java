package com.revature.stepimplementations.registration;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationSteps {
    @When("the user enters a {string} into the first name input")
    public void the_user_enters_into_the_first_name_input(String firstname) {
        Hooks.registrationPage.firstnameInput.sendKeys(firstname);

    }
    @When("the user enters a {string} into the last name input")
    public void the_user_enters_into_the_last_name_input(String lastname) {
        Hooks.registrationPage.lastnameInput.sendKeys(lastname);

    }
    @When("the user enters a {string} into the email input")
    public void the_user_enters_into_the_email_input(String email) {
        Hooks.registrationPage.emailInput.sendKeys(email);

    }
    @When("the user enters a {string} into the password input")
    public void the_user_enters_into_the_password_input(String password) {
        Hooks.registrationPage.passwordInput.sendKeys(password);
        // Need to tab out of password field to get the error message to display
        Hooks.actions.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();

    }
    @When("the user clicks the sign up button")
    public void the_user_clicks_the_sign_up_button() {
        Hooks.registrationPage.signupButton.click();

    }

    @Then("the user should see an error message under the {string} field saying {string}")
    public void the_user_should_see_an_error_message_under_the_field(String field, String expectedMessage) {
        String actualMessage = null;
        try {
            switch (field) {
                case "firstname":
                    Hooks.wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.registrationPage.firstnameHelperText));
                    actualMessage = Hooks.registrationPage.firstnameHelperText.getText();
                    break;
                case "lastname":
                    Hooks.wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.registrationPage.lastnameHelperText));
                    actualMessage = Hooks.registrationPage.lastnameHelperText.getText();
                    break;
                case "email":
                    Hooks.wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.registrationPage.emailHelperText));
                    actualMessage = Hooks.registrationPage.emailHelperText.getText();
                    break;
                case "password":
                    Hooks.wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.registrationPage.passwordHelperText));
                    actualMessage = Hooks.registrationPage.passwordHelperText.getText();
                    break;
            }
        } catch (TimeoutException e) {
            Assertions.fail("No error message was displayed for the " + field + " field.");
        }
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
