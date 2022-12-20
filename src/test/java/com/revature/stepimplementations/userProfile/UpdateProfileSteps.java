package com.revature.stepimplementations.userProfile;

import com.revature.OperatingSystem;
import com.revature.stepimplementations.hooks.Hooks;
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
        Hooks.driver.get("http://localhost:3000/");
        Hooks.userProfilePage.signInLink.click();
        Hooks.userProfilePage.loginEmailInputField.sendKeys("jane@gmail.com");
        Hooks.userProfilePage.loginPasswordInputField.sendKeys("password");
        Hooks.userProfilePage.signInButton.click();
    }
    @When("User clicks on the Profile link")
    public void user_clicks_on_the_profile_link() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.userProfilePage.profileLink));
        Hooks.userProfilePage.profileLink.click();
    }
    @Then("The user should be navigated to the Profile page")
    public void the_user_should_be_navigated_to_the_profile_page() {
        String expectedUrl = "http://localhost:3000/userProfile";
        String actualUrl = Hooks.driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }
    @Then("The user should be able to see two MUI boxes {string} and {string}")
    public void the_user_should_be_able_to_see_two_mui_boxes_and(String updateMUIHeader, String deactivateMUIHeader) {
        String expectedHeaders = updateMUIHeader + " " + deactivateMUIHeader;
        String aHone = Hooks.userProfilePage.updateProfileMUIBoxHeader.getText();
        String aHtwo = Hooks.userProfilePage.deactivateAccountMUIBoxHeader.getText();
        String actualHeaders = aHone + " " + aHtwo;
        Assertions.assertEquals(expectedHeaders, actualHeaders);
    }
    @When("User types in {string} in the first name input field")
    public void user_types_in_in_the_first_name_input_field(String firstname) {
        OperatingSystem.OSType detectedOS = OperatingSystem.getOperatingSystemType();
        WebElement el = Hooks.userProfilePage.muiFirstnameInputField;
        Action seriesOfActions;
        if (detectedOS == OperatingSystem.OSType.MacOS) {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.COMMAND)
                    .sendKeys("a", Keys.DELETE).keyUp(Keys.COMMAND)
                    .build();
        } else {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.CONTROL)
                    .sendKeys("a", Keys.BACK_SPACE).keyUp(Keys.CONTROL)
                    .build();
        }
        seriesOfActions.perform();
        el.sendKeys(firstname);
    }
    @When("types in {string} in the last name input field")
    public void types_in_in_the_last_name_input_field(String lastname) {
        OperatingSystem.OSType detectedOS = OperatingSystem.getOperatingSystemType();
        WebElement el = Hooks.userProfilePage.muiLastnameInputField;
        Action seriesOfActions;
        if (detectedOS == OperatingSystem.OSType.MacOS) {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.COMMAND)
                    .sendKeys("a", Keys.DELETE).keyUp(Keys.COMMAND)
                    .build();
        } else {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.CONTROL)
                    .sendKeys("a", Keys.BACK_SPACE).keyUp(Keys.CONTROL)
                    .build();
        }
        seriesOfActions.perform();
        el.sendKeys(lastname);
    }
    @When("types in {string} in the password input field")
    public void types_in_in_the_password_input_field(String password) {
        OperatingSystem.OSType detectedOS = OperatingSystem.getOperatingSystemType();
        WebElement el = Hooks.userProfilePage.muiPasswordInputField;
        Action seriesOfActions;
        if (detectedOS == OperatingSystem.OSType.MacOS) {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.COMMAND)
                    .sendKeys("a", Keys.DELETE).keyUp(Keys.COMMAND)
                    .build();
        } else {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.CONTROL)
                    .sendKeys("a", Keys.BACK_SPACE).keyUp(Keys.CONTROL)
                    .build();
        }
        seriesOfActions.perform();
        el.sendKeys(password);
    }
    @When("clicks on the Update button")
    public void clicks_on_the_update_button() {
        Hooks.userProfilePage.muiUpdateButton.click();
    }
    @Then("An Alert for successfully updated profile says {string}")
    public void an_alert_for_successfully_updated_profile_says(String expectedAlert) {
        String actualAlert =
                Hooks.wait.ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.visibilityOf(Hooks.userProfilePage.uAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

    // scenario 2
    @When("User clears the first name input field")
    public void user_clears_the_first_name_input_field() {
        OperatingSystem.OSType detectedOS = OperatingSystem.getOperatingSystemType();
        WebElement el = Hooks.userProfilePage.muiFirstnameInputField;
        Action seriesOfActions;
        if (detectedOS == OperatingSystem.OSType.MacOS) {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.COMMAND)
                    .sendKeys("a", Keys.DELETE).keyUp(Keys.COMMAND)
                    .build();
        } else {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.CONTROL)
                    .sendKeys("a", Keys.BACK_SPACE).keyUp(Keys.CONTROL)
                    .build();
        }
        seriesOfActions.perform();
    }
    @Then("An Alert says {string}")
    public void an_alert_says(String expectedAlert) {
        String actualAlert = Hooks.wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(Hooks.userProfilePage.uAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

    // scenario 3
    @When("User clears the last name input field")
    public void user_clears_the_last_name_input_field() {
        OperatingSystem.OSType detectedOS = OperatingSystem.getOperatingSystemType();
        WebElement el = Hooks.userProfilePage.muiLastnameInputField;
        Action seriesOfActions;
        if (detectedOS == OperatingSystem.OSType.MacOS) {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.COMMAND)
                    .sendKeys("a", Keys.DELETE).keyUp(Keys.COMMAND)
                    .build();
        } else {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.CONTROL)
                    .sendKeys("a", Keys.BACK_SPACE).keyUp(Keys.CONTROL)
                    .build();
        }
        seriesOfActions.perform();
    }

    // scenario 4
    @When("leaves the password input field empty")
    public void leaves_the_password_input_field_empty() {
        OperatingSystem.OSType detectedOS = OperatingSystem.getOperatingSystemType();
        WebElement el = Hooks.userProfilePage.muiPasswordInputField;
        Action seriesOfActions;
        if (detectedOS == OperatingSystem.OSType.MacOS) {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.COMMAND)
                    .sendKeys("a", Keys.DELETE).keyUp(Keys.COMMAND)
                    .build();
        } else {
            seriesOfActions = Hooks.actions
                    .moveToElement(el).click().keyDown(Keys.CONTROL)
                    .sendKeys("a", Keys.BACK_SPACE).keyUp(Keys.CONTROL)
                    .build();
        }
        seriesOfActions.perform();
    }

    // scenario 5
    @When("User clears all input fields")
    public void user_clears_all_input_fields() {
        OperatingSystem.OSType detectedOS = OperatingSystem.getOperatingSystemType();
        List<WebElement> els = new ArrayList<>(Hooks.userProfilePage.uInputFields);
        for (WebElement el: els) {
            Action seriesOfActions;
            if (detectedOS == OperatingSystem.OSType.MacOS) {
                seriesOfActions = Hooks.actions
                        .moveToElement(el).click().keyDown(Keys.COMMAND)
                        .sendKeys("a", Keys.DELETE).keyUp(Keys.COMMAND)
                        .build();
            } else {
                seriesOfActions = Hooks.actions
                        .moveToElement(el).click().keyDown(Keys.CONTROL)
                        .sendKeys("a", Keys.BACK_SPACE).keyUp(Keys.CONTROL)
                        .build();
            }
            seriesOfActions.perform();
        }
    }
    @Then("An Alert for no input fields says {string}")
    public void an_alert_for_no_input_fields_says(String expectedAlert) {
        String actualAlert = Hooks.wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(Hooks.userProfilePage.uAlert)).getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

}