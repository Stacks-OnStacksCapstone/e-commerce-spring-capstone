package com.revature.stepsImplementation;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Payments {
    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        PaymentHooks.driver.get("http://localhost:3000/");
        PaymentHooks.profilePage.signInLink.click();
        PaymentHooks.profilePage.loginEmailInput.sendKeys("jane@gmail.com");
        PaymentHooks.profilePage.loginPasswordInput.sendKeys("password");
        PaymentHooks.profilePage.signInButton.click();
    }

    @When("the user clicks on the Profile link")
    public void the_user_clicks_on_the_profile_link() {
        PaymentHooks.wait.until(ExpectedConditions.visibilityOf(PaymentHooks.profilePage.profileLink));
        PaymentHooks.profilePage.profileLink.click();
    }

    @Then("the user is navigated to the Profile page")
    public void the_user_is_navigated_to_the_profile_page() {
        String actualUrl = PaymentHooks.driver.getCurrentUrl();
        String expectedUrl = "http://localhost:3000/userProfile";
        Assertions.assertEquals(actualUrl, expectedUrl);

    }

    @When("the user enters {string} as cardNumber")
    public void the_user_enters_as_card_number(String cardNumber) {
        PaymentHooks.profilePage.cardNumberInput.sendKeys(cardNumber);

    }

    @When("the user enters {string} as expDate")
    public void the_user_enters_as_exp_date(String expDate) {

        PaymentHooks.profilePage.expDateInput.sendKeys(Keys.LEFT);
        PaymentHooks.profilePage.expDateInput.sendKeys(Keys.LEFT);
        PaymentHooks.profilePage.expDateInput.sendKeys(expDate);
    }

    @When("the user enters {string} as ccv")
    public void the_user_enters_as_ccv(String ccv) {
        PaymentHooks.profilePage.ccvInput.sendKeys(ccv);
    }

    @When("the user clicks on the add payment button")
    public void the_user_clicks_on_the_add_payment_button() {
        PaymentHooks.profilePage.addPaymentButton.click();
    }

    @Then("the user should see alert message {string}")
    public void the_user_should_see_alert_message(String message) {


        String actualAlert =
                PaymentHooks.wait.ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.visibilityOf(PaymentHooks.profilePage.alert)).getText();
        Assertions.assertEquals(message, actualAlert);

    }

    //Delete payment
    @When("the user clicks on the delete payment button")
    public void the_user_clicks_on_the_delete_payment_button() {
        PaymentHooks.profilePage.deletePaymentButton.click();
    }

    //Update payment
    @When("the user clicks on the update payment button")
    public void the_user_clicks_on_the_update_payment_button() {

         try{
        PaymentHooks.profilePage.updatePaymentButton.click();

         }catch (NoSuchElementException e){
             Assertions.fail("Update payment method not available");
         }

    }
}