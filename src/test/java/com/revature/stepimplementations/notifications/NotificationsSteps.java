package com.revature.stepimplementations.notifications;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NotificationsSteps {
    @Given("A user is logged in with {string} and {string}")
    public void a_user_is_logged_in_with_and(String email, String password) {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.userProfilePage.loginEmailInputField.sendKeys(email);
        Hooks.userProfilePage.loginPasswordInputField.sendKeys(password);
        Hooks.userProfilePage.signInButton.click();
    }
    @When("User hovers over a product and clicked on the cart icon to add the product to cart")
    public void user_hovers_over_a_product_and_clicked_on_the_cart_icon_to_add_the_product_to_cart() {
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Hooks.actions.moveToElement(Hooks.notificationsPage.cartIcon).click().build().perform();
    }
    @When("User clicks on the cart icon link on the navigation bar to view cart")
    public void user_clicks_on_the_cart_icon_link_on_the_navigation_bar_to_view_cart() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.notificationsPage.cartIconLink)).click();
    }
    @When("User clicks on the Checkout Now button on the cart page")
    public void user_clicks_on_the_checkout_now_button_on_the_cart_page() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.notificationsPage.checkoutNowBtn)).click();
    }
    @When("User filled out {string} and {string} for the Shipping Address on the checkout page")
    public void user_filled_out_and_for_the_shipping_address_on_the_checkout_page(String firstname, String lastname) {
        Hooks.notificationsPage.checkoutFname.sendKeys(firstname);
        Hooks.notificationsPage.checkoutLname.sendKeys(lastname);
    }
    @When("filled out {string} line one")
    public void filled_out_line_one(String address) {
        Hooks.notificationsPage.checkoutAddressLine.sendKeys(address);
    }
    @When("filled out {string} and {string}")
    public void filled_out_and(String city, String state) {
        Hooks.notificationsPage.checkoutCity.sendKeys(city);
        Hooks.notificationsPage.checkoutState.sendKeys(state);
    }
    @When("filled out the {string} and {string}")
    public void filled_out_the_and(String zipCode, String country) {
        Hooks.notificationsPage.checkoutZipCode.sendKeys(zipCode);
        Hooks.notificationsPage.checkoutCountry.sendKeys(country);
    }
    @When("clicks on the Next button")
    public void clicks_on_the_next_button() {
        Hooks.notificationsPage.checkoutNextBtn.click();
    }
    @When("User select a payment method")
    public void user_select_a_payment_method() {
        Hooks.actions.moveToElement(Hooks.notificationsPage.checkoutPaymentOption).click().perform();
    }
    @When("clicks on the Submit Payment button")
    public void clicks_on_the_submit_payment_button() {
        Hooks.notificationsPage.submitPaymentBtn.click();
    }
    @When("User clicks on the Place Order button")
    public void user_clicks_on_the_place_order_button() {
        Hooks.notificationsPage.placeOrderBtn.click();
    }
    @Then("a notification with title {string} is displayed")
    public void a_notification_with_title_is_displayed(String expectedNotificationTitle) {
        String actualNotificationTitle = Hooks.notificationsPage.notificationTitle.getText();
        Assertions.assertEquals(expectedNotificationTitle, actualNotificationTitle);
    }
}
