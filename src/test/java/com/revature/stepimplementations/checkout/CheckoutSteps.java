package com.revature.stepimplementations.checkout;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.revature.stepimplementations.hooks.Hooks.*;
import static java.awt.Color.red;

public class CheckoutSteps {

    // LOGGED IN CHECKOUT

    @Given("User is logged in on the front page")
    public void user_is_logged_in_on_the_front_page() {
        driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("mark@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));
        Hooks.loginPage.loginButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/", actualUrl);
    }

    @When("User adds an item to the cart and clicks the cart icon")
    public void user_adds_an_item_to_the_cart_and_clicks_the_cart_icon() {
        actions.moveToElement(frontPage.headphonesAddButton).click().build().perform();
    }

    @When("User navigates to the cart")
    public void user_navigates_to_the_cart() {
        wait.until(ExpectedConditions.elementToBeClickable(generalPage.cartButton));
        generalPage.cartButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/cart"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/cart", actualUrl);
    }

    @When("User clicks the checkout button")
    public void user_clicks_the_checkout_button() {
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.checkoutButton));
        cartPage.checkoutButton.click();
    }

    @When("User enters a valid shipping address and clicks next")
    public void user_enters_a_valid_shipping_address_and_clicks_next() {
        Hooks.checkoutPage.firstNameInput.sendKeys("Mark");
        Hooks.checkoutPage.lastNameInput.sendKeys("Man");
        Hooks.checkoutPage.addressInput.sendKeys("500 Nowhere Rd");
        Hooks.checkoutPage.cityInput.sendKeys("Dallas");
        Hooks.checkoutPage.stateInput.sendKeys("TX");
        Hooks.checkoutPage.zipInput.sendKeys("50000");
        Hooks.checkoutPage.countryInput.sendKeys("USA");
        Hooks.checkoutPage.nextButton.click();
    }

    @When("User selects a payment method and clicks submit")
    public void user_selects_a_payment_method_and_clicks_submit() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutPage.paymentRadioButton));
        } catch (TimeoutException e) {
            Assertions.fail("No payment methods available to select from.");
        }
        actions.moveToElement(checkoutPage.paymentRadioButton).click().build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.submitPaymentButton));
        checkoutPage.submitPaymentButton.click();
    }

    @When("User clicks place order")
    public void user_clicks_place_order() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.placeOrderButton));
        checkoutPage.placeOrderButton.click();
    }

    @Then("A checkout message is displayed")
    public void a_checkout_message_is_displayed() {
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.thankYouMessage));
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.checkoutMessage));
        String actualThankYouMessage = checkoutPage.thankYouMessage.getText();
        String actualCheckoutMessage = checkoutPage.checkoutMessage.getText();
        Assert.assertEquals("Thank you for your order.", actualThankYouMessage);
        Assert.assertTrue(actualCheckoutMessage.contains("order number"));
    }

    // NOT LOGGED IN CHECKOUT

    @Given("User is not logged in on the front page")
    public void user_is_not_logged_in_on_the_front_page() {
        Hooks.driver.get("http://localhost:3000/");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/", actualUrl);
    }

    // WITHOUT PAYMENT
    @When("User does not select a payment method and clicks submit")
    public void user_does_not_select_a_payment_method_and_clicks_submit() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.submitPaymentButton));
        checkoutPage.submitPaymentButton.click();
    }

    @Then("User remains on the payment method page")
    public void user_remains_on_the_payment_method_page() {
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.checkoutStepTitle));
        String actualText = checkoutPage.checkoutStepTitle.getText();
        Assert.assertEquals("Payment method", actualText);
    }

    // EMPTY FIELDS AND SPECIAL CHARACTERS

    @When("User enters {string} to first name input")
    public void user_enters_to_first_name_input(String firstname) {
        Hooks.checkoutPage.firstNameInput.sendKeys(firstname);
    }

    @When("User enters {string} to last name input")
    public void user_enters_to_last_name_input(String string) {
        Hooks.checkoutPage.lastNameInput.sendKeys(string);
    }

    @When("User enters {string} to address input")
    public void user_enters_to_address_input(String string) {
        Hooks.checkoutPage.addressInput.sendKeys(string);
    }

    @When("User enters {string} to city input")
    public void user_enters_to_city_input(String string) {
        Hooks.checkoutPage.cityInput.sendKeys(string);
    }

    @When("Users enters TX to state input")
    public void users_enters_tx_to_state_input() {
        Hooks.checkoutPage.stateInput.sendKeys("TX");
    }

    @When("User enters {string} to zip input")
    public void user_enters_to_zip_input(String string) {
        Hooks.checkoutPage.zipInput.sendKeys(string);
    }

    @When("User enters {string} to country input")
    public void user_enters_to_country_input(String string) {
        Hooks.checkoutPage.countryInput.sendKeys(string);
    }

    @Then("The invalid field turns red and displays {string}")
    public void the_invalid_field_turns_red_and_displays(String string) {
        actions.sendKeys(Keys.TAB).build().perform();
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutPage.shipAddressErrorMessage));
        } catch (TimeoutException e) {
            Assertions.fail("An error message did not appear for this input field.");
        }
        String actualText = checkoutPage.shipAddressErrorMessage.getText();
        String fontColor = checkoutPage.shipAddressErrorMessage.getCssValue("color");
        Assert.assertEquals(string, actualText);
        Assert.assertEquals("rgba(211, 47, 47, 1)", fontColor);
    }

    @And("User clicks next")
    public void user_clicks_next() {
        Hooks.checkoutPage.nextButton.click();
    }

    @Then("User remains on the shipping address page")
    public void user_remains_on_the_shipping_address_page() {
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.checkoutStepTitle));
        String actualText = checkoutPage.checkoutStepTitle.getText();
        Assert.assertEquals("Shipping address", actualText);
    }
}
