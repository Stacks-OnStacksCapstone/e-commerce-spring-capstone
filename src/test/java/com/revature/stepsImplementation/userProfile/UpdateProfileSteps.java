
package com.revature.stepsImplementation.userProfile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class UpdateProfileSteps {

    @Given("User is logged in")
    public void user_is_logged_in() {
        ProfileHooks.driver.get("http://localhost:3000/");
        ProfileHooks.userProfilePage.signInLink.click();
        ProfileHooks.userProfilePage.loginEmailInputField.sendKeys("jane@gmail.com");
        ProfileHooks.userProfilePage.loginPasswordInputField.sendKeys("password");
        ProfileHooks.userProfilePage.signInButton.click();
    }
    @When("User clicks on the Profile link")
    public void user_clicks_on_the_profile_link() {
        ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.userProfilePage.profileLink));
        ProfileHooks.userProfilePage.profileLink.click();
    }
    @Then("User is navigated to the Profile page")
    public void user_is_navigated_to_the_profile_page() {
        String expectedUrl = "http://localhost:3000/userProfile";
        String actualUrl = ProfileHooks.driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }
    @Then("User is able to see two MUI boxes {string} and {string}")
    public void user_is_able_to_see_two_mui_boxes_and(String updateMUIHeader, String deactivateMUIHeader) {
        String expectedHeaders = updateMUIHeader + " " + deactivateMUIHeader;
        String aHone = ProfileHooks.userProfilePage.updateProfileMUIBoxHeader.getText();
        String aHtwo = ProfileHooks.userProfilePage.deactivateAccountMUIBoxHeader.getText();
        String actualHeaders = aHone + " " + aHtwo;
        Assertions.assertEquals(expectedHeaders, actualHeaders);
    }
    @When("User types in {string} in the first name input field")
    public void user_types_in_in_the_first_name_input_field(String firstname) {
        WebElement el = ProfileHooks.userProfilePage.muiFirstnameInputField;
        Action seriesOfActions = ProfileHooks.actions
                .moveToElement(el).click().pause(500).keyDown(Keys.COMMAND)
                .sendKeys("a",Keys.DELETE).keyUp(Keys.COMMAND)
                .build();
        seriesOfActions.perform();
        el.sendKeys(firstname);
    }
    @When("types in {string} in the last name input field")
    public void types_in_in_the_last_name_input_field(String lastname) {
        WebElement el = ProfileHooks.userProfilePage.muiLastnameInputField;
        Action seriesOfActions = ProfileHooks.actions
                .moveToElement(el).click().keyDown(Keys.COMMAND)
                .sendKeys("a",Keys.DELETE).keyUp(Keys.COMMAND)
                .build();
        seriesOfActions.perform();
        el.sendKeys(lastname);
    }
    @When("types in {string} in the password input field")
    public void types_in_in_the_password_input_field(String password) {
        WebElement el = ProfileHooks.userProfilePage.muiPasswordInputField;
        Action seriesOfActions = ProfileHooks.actions
                .moveToElement(el).click().keyDown(Keys.COMMAND)
                .sendKeys("a",Keys.DELETE).keyUp(Keys.COMMAND)
                .build();
        seriesOfActions.perform();
        el.sendKeys(password);
    }
    @When("clicks on the Update button")
    public void clicks_on_the_update_button() {
        ProfileHooks.userProfilePage.muiUpdateButton.click();
    }
    @Then("An Alert for successfully updated profile says {string}")
    public void an_alert_for_successfully_updated_profile_says(String expectedAlert) {
        String actualAlert =
                ProfileHooks.wait.ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.visibilityOf(ProfileHooks.userProfilePage.uAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

    // scenario 2
    @When("User clears the first name input field")
    public void user_clears_the_first_name_input_field() {
        WebElement el = ProfileHooks.userProfilePage.muiFirstnameInputField;
        Action seriesOfActions = ProfileHooks.actions
                .moveToElement(el).click().keyDown(Keys.COMMAND)
                .sendKeys("a",Keys.DELETE).keyUp(Keys.COMMAND)
                .build();
        seriesOfActions.perform();
    }
    @Then("An Alert says {string}")
    public void an_alert_says(String expectedAlert) {
        String actualAlert = ProfileHooks.wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(ProfileHooks.userProfilePage.uAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

    // scenario 3
    @When("User clears the last name input field")
    public void user_clears_the_last_name_input_field() {
        WebElement el = ProfileHooks.userProfilePage.muiLastnameInputField;
        Action seriesOfActions = ProfileHooks.actions
                .moveToElement(el).click().keyDown(Keys.COMMAND)
                .sendKeys("a",Keys.DELETE).keyUp(Keys.COMMAND)
                .build();
        seriesOfActions.perform();
    }

    // scenario 4
    @When("leaves the password input field empty")
    public void leaves_the_password_input_field_empty() {
        WebElement el = ProfileHooks.userProfilePage.muiPasswordInputField;
        Action seriesOfActions = ProfileHooks.actions
                .moveToElement(el).click().keyDown(Keys.COMMAND)
                .sendKeys("a",Keys.DELETE).keyUp(Keys.COMMAND)
                .build();
        seriesOfActions.perform();
    }

    // scenario 5
    @When("User clears all input fields")
    public void user_clears_all_input_fields() {
        List<WebElement> els = new ArrayList<>(ProfileHooks.userProfilePage.uInputFields);
        for (WebElement el: els) {
            Action seriesOfActions = ProfileHooks.actions
                    .moveToElement(el).click().keyDown(Keys.COMMAND)
                    .sendKeys("a",Keys.DELETE).keyUp(Keys.COMMAND).build();
            seriesOfActions.perform();
        }
    }
    @Then("An Alert for no input fields says {string}")
    public void an_alert_for_no_input_fields_says(String expectedAlert) {
        String actualAlert = ProfileHooks.wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(ProfileHooks.userProfilePage.uAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

}

