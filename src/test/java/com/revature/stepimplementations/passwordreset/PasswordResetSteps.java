package com.revature.stepimplementations.passwordreset;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class PasswordResetSteps {

    @When("the user enters {string} as the email")
    public void the_user_enters_as_the_email(String email) {
        Hooks.passwordResetPage.emailInput.sendKeys(email);
    }

    @When("the user clicks on the send password reset link button")
    public void the_user_clicks_on_the_send_password_reset_link_button() {
        Hooks.passwordResetPage.passwordResetLinkButton.click();
    }

    @Then("the user should see a message saying {string}")
    public void the_user_should_see_a_message_saying(String expectedMessage) {
        String actualMessage = Hooks.passwordResetPage.message.getText();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @When("the user goes to the reset link")
    public void the_user_goes_to_the_reset_link() {
        //TODO: call mailtrap API to get token and navigate to the link given
    }

    @When("the user enters {string} as their new password")
    public void the_user_enters_as_their_new_password(String password) {
        Hooks.passwordResetPage.passwordInput.sendKeys(password);
    }

    @When("the user clicks on the send password reset link button")
    public void the_user_clicks_on_the_reset_password_button() {
        Hooks.passwordResetPage.passwordResetButton.click();
    }

}
