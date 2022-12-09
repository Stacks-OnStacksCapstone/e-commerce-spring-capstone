package com.revature.stepsImplementation.checkout;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutSteps {

    // BACKGROUND

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        CheckoutHooks.driver.get("http://localhost:3000/login");
    }
    @When("User enters valid credentials")
    public void user_enters_valid_credentials() {
        CheckoutHooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form//input[@id='email']")));
        CheckoutHooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        CheckoutHooks.loginPage.passwordInput.sendKeys("password");
        CheckoutHooks.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form//button[contains(text(), 'Sign In')]")));
        CheckoutHooks.loginPage.loginButton.click();
    }
    @Then("User logs in to the front page")
    public void user_logs_in_to_the_front_page() {
        CheckoutHooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        String actualUrl = CheckoutHooks.driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/", actualUrl);
    }

    @Given("User is on the front page")
    public void user_is_on_the_front_page() {

    }
    @When("User adds items to the cart and clicks the cart icon")
    public void user_adds_items_to_the_cart_and_clicks_the_cart_icon() {

    }
    @When("User navigates to the cart and sees their items")
    public void user_navigates_to_the_cart_and_sees_their_items() {

    }
    @When("User clicks the checkout button")
    public void user_clicks_the_checkout_button() {

    }
    @When("User enters a valid shipping address and clicks next")
    public void user_enters_a_valid_shipping_address_and_clicks_next() {

    }

    @And("User selects a payment method and clicks submit")
    public void user_selects_a_payment__method_and_clicks_submit() {
        //CheckoutHooks.actions.moveToElement(checkoutPage.paymentRadioButton).sendKeys(Keys.PAUSE.ENTER).build().perform();
    }

    @And("User views the order summary and clicks place order")
    public void user_views_the_order_summary_and_clicks_place_order() {

    }

    @Then("A confirmation message appears with the order number and other information")
    public void a_confirmation_message_appears_with_the_order_number_and_other_information() {

    }

    // WITHOUT PAYMENT

    @When("User does not select a payment method and clicks submit")
    public void user_does_not_select_a_payment_method_and_clicks_submit() {

    }

    @Then("User will not proceed to the final step to view their order summary")
    public void user_will_not_proceed_to_the_final_step_to_view_their_order_summary() {

    }
}
