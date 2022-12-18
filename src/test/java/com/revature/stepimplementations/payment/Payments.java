package com.revature.stepimplementations.payment;


import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Payments {

    @When("the user log in")
    public void the_user_log_in() {
        Hooks.profilePage.signInLink.click();
        Hooks.profilePage.loginEmailInput.sendKeys("jane@gmail.com");
        Hooks.profilePage.loginPasswordInput.sendKeys("password");
        Hooks.profilePage.signInButton.click();
    }

    @When("the user clicks on the Profile link")
    public void the_user_clicks_on_the_profile_link() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.profilePage.profileLink));
        Hooks.profilePage.profileLink.click();
    }

    @Then("the user is navigated to the Profile page")
    public void the_user_is_navigated_to_the_profile_page() {
        String actualUrl = Hooks.driver.getCurrentUrl();
        String expectedUrl = "http://localhost:3000/userProfile";
        Assertions.assertEquals(actualUrl, expectedUrl);

    }

    @When("the user enters {string} as cardNumber")
    public void the_user_enters_as_card_number(String cardNumber) {
        Hooks.profilePage.cardNumberInput.sendKeys(cardNumber);

    }

    @When("the user enters {string} as expDate")
    public void the_user_enters_as_exp_date(String expDate) {

        Hooks.profilePage.expDateInput.sendKeys(Keys.LEFT);
        Hooks.profilePage.expDateInput.sendKeys(Keys.LEFT);
        Hooks.profilePage.expDateInput.sendKeys(expDate);
    }

    @When("the user enters {string} as ccv")
    public void the_user_enters_as_ccv(String ccv) {
        Hooks.profilePage.ccvInput.sendKeys(ccv);
    }

//    @When("the user clicks on the add payment button")
//    public void the_user_clicks_on_the_add_payment_button() {
//        Hooks.profilePage.addPaymentButton.click();
//    }

    @Then("the user should see alert message {string} on the profile page")
    public void the_user_should_see_alert_message(String alert) {


        String actualAlert =
                Hooks.wait.ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.visibilityOf(Hooks.profilePage.alert)).getText();
        Assertions.assertEquals(alert, actualAlert);

    }

    @When("the user clicks on the {string} payment button")
    public void the_user_clicks_on_the_payment_button(String button) {
        switch (button) {
            case "add":
                Hooks.profilePage.addPaymentButton.click();
                break;
            case "delete":
                Hooks.profilePage.deletePaymentButton.click();
                break;
            case "update":
                try{
                    Hooks.profilePage.updatePaymentButton.click();

                }catch (NoSuchElementException e){
                    Assertions.fail("Update payment method not available");
                }
                break;}
    }

    @Then("the user should see an alert message saying {string}")
    public void the_user_should_see_an_alert_message_saying(String alert) {
        String actualAlert =
                Hooks.wait.ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.visibilityOf(Hooks.profilePage.alert)).getText();
        Assertions.assertEquals(alert, actualAlert);
    }
}