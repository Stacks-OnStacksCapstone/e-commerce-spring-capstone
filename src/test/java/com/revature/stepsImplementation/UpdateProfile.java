package com.revature.stepsImplementation;

import com.revature.runners.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class UpdateProfile {

    @Given("User is logged in")
    public void user_is_logged_in() {
        Runner.driver.get("http://localhost:3000/");
        Runner.userProfilePage.signInLink.click();
        Runner.userProfilePage.loginEmailInputField.sendKeys("jane@gmail.com");
        Runner.userProfilePage.loginPasswordInputField.sendKeys("password");
        Runner.userProfilePage.signInButton.click();
    }
    @When("User clicks on the Profile link")
    public void user_clicks_on_the_profile_link() {
        Runner.userProfilePage.profileLink.click();
    }
    @Then("User is navigated to the Profile page")
    public void user_is_navigated_to_the_profile_page() {
        String actualUrl = Runner.driver.getCurrentUrl();
        String expectedUrl = "http://localhost:3000/userProfile";
        Assert.assertEquals(actualUrl, expectedUrl);
    }
    @Then("User is able to see two MUI boxes {string} and {string}")
    public void user_is_able_to_see_two_mui_boxes_and(String updateMUIHeader, String deactivateMUIHeader) {
        String[] expectedHeaders = {updateMUIHeader, deactivateMUIHeader};
        String[] actualHeaders = {String.valueOf(Runner.userProfilePage.updateProfileMUIBoxHeader), String.valueOf(Runner.userProfilePage.deactivateAccountMUIBoxHeader)};
        Assert.assertEquals(actualHeaders, expectedHeaders);
    }
    @When("User types in {string} in the first name input field")
    public void user_types_in_in_the_first_name_input_field(String firstname) {
        Runner.userProfilePage.muiFirstnameInputField.clear();
        Runner.userProfilePage.muiFirstnameInputField.sendKeys(firstname);
    }
    @When("types in {string} in the last name input field")
    public void types_in_in_the_last_name_input_field(String lastname) {
        Runner.userProfilePage.muiLastnameInputField.clear();
        Runner.userProfilePage.muiLastnameInputField.sendKeys(lastname);
    }
    @When("types in {string} in the password input field")
    public void types_in_in_the_password_input_field(String password) {
        Runner.userProfilePage.muiPasswordInputField.clear();
        Runner.userProfilePage.muiPasswordInputField.sendKeys(password);
    }
    @When("clicks on the Update button")
    public void clicks_on_the_update_button() {
        Runner.userProfilePage.muiUpdateButton.click();
    }
    @Then("An Alert for successfully updated profile says {string}")
    public void an_alert_for_successfully_updated_profile_says(String expectedAlert) {
        String actualAlert = Runner.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlert, expectedAlert);
    }

    // scenario 2
    @When("User clears the first name input field")
    public void user_clears_the_first_name_input_field() {
        Runner.userProfilePage.muiFirstnameInputField.clear();
    }

    @Then("An Alert says {string}")
    public void an_alert_says(String expectedAlert) {
        String actualAlert = Runner.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlert, expectedAlert);
    }

    // scenario 3
    @When("User clears the last name input field")
    public void user_clears_the_last_name_input_field() {
        Runner.userProfilePage.muiLastnameInputField.clear();
    }

    // scenario 4
    @When("leaves the password input field empty")
    public void leaves_the_password_input_field_empty() {
        Runner.userProfilePage.muiPasswordInputField.clear();
    }

    // scenario 5
    @When("User clears all input fields")
    public void user_clears_all_input_fields() {
        Runner.userProfilePage.muiFirstnameInputField.clear();
        Runner.userProfilePage.muiLastnameInputField.clear();
        Runner.userProfilePage.muiPasswordInputField.clear();
    }

    @Then("An Alert for no input fields says {string}")
    public void an_alert_for_no_input_fields_says(String expectedAlert) {
        String actualAlert = Runner.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlert, expectedAlert);
    }

}
