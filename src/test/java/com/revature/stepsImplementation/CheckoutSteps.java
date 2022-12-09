package com.revature.stepsImplementation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.revature.stepsImplementation.CheckoutHooks.*;

public class CheckoutSteps {

    // WITHOUT PAYMENT
    @Given("User is on the front page")
    public void user_is_on_the_front_page() {
        CheckoutHooks.driver.get("http://localhost:3000/login");
        loginPage.emailInput.sendKeys("jane@gmail.com");
        loginPage.passwordInput.sendKeys("password");
        loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Assert.assertEquals("http://localhost:3000/", driver.getCurrentUrl());
    }
    @When("User adds items to the cart and clicks the cart icon")
    public void user_adds_items_to_the_cart_and_clicks_the_cart_icon() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User navigates to the cart and sees their items")
    public void user_navigates_to_the_cart_and_sees_their_items() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User clicks the checkout button")
    public void user_clicks_the_checkout_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User enters a valid shipping address and clicks next")
    public void user_enters_a_valid_shipping_address_and_clicks_next() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User does not select a payment method and clicks submit")
    public void user_does_not_select_a_payment_method_and_clicks_submit() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User will not proceed to the final step to view their order summary")
    public void user_will_not_proceed_to_the_final_step_to_view_their_order_summary() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
