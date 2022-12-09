package com.revature.stepsImplementation.notifications;

import com.revature.stepsImplementation.userProfile.ProfileHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NotificationsSteps {
    @Given("A user is logged in with {string} and {string}")
    public void a_user_is_logged_in_with_and(String email, String password) {
        ProfileHooks.driver.get("http://localhost:3000/login");
        ProfileHooks.userProfilePage.loginEmailInputField.sendKeys(email);
        ProfileHooks.userProfilePage.loginPasswordInputField.sendKeys(password);
        ProfileHooks.userProfilePage.signInButton.click();
    }
    @When("User hovers over a product and clicked on the cart icon to add the product to cart")
    public void user_hovers_over_a_product_and_clicked_on_the_cart_icon_to_add_the_product_to_cart() {
        ProfileHooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        ProfileHooks.actions.moveToElement(ProfileHooks.notificationsPage.cartIcon).click().build().perform();
    }
    @When("User clicks on the cart icon link on the navigation bar to view cart")
    public void user_clicks_on_the_cart_icon_link_on_the_navigation_bar_to_view_cart() {
        ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.notificationsPage.cartIconLink)).click();
    }
    @When("User clicks on the Checkout Now button on the cart page")
    public void user_clicks_on_the_checkout_now_button_on_the_cart_page() {
        ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.notificationsPage.checkoutNowBtn)).click();
    }
    @When("User filled out {string} and {string} for the Shipping Address on the checkout page")
    public void user_filled_out_and_for_the_shipping_address_on_the_checkout_page(String firstname, String lastname) {
        ProfileHooks.notificationsPage.checkoutFname.sendKeys(firstname);
        ProfileHooks.notificationsPage.checkoutLname.sendKeys(lastname);
    }
    @When("filled out {string} line one")
    public void filled_out_line_one(String address) {
        ProfileHooks.notificationsPage.checkoutAddressLine.sendKeys(address);
    }
    @When("filled out {string} and {string}")
    public void filled_out_and(String city, String state) {
        ProfileHooks.notificationsPage.checkoutCity.sendKeys(city);
        ProfileHooks.notificationsPage.checkoutState.sendKeys(state);
    }
    @When("filled out the {string} and {string}")
    public void filled_out_the_and(String zipCode, String country) {
        ProfileHooks.notificationsPage.checkoutZipCode.sendKeys(zipCode);
        ProfileHooks.notificationsPage.checkoutCountry.sendKeys(country);
    }
    @When("clicks on the Next button")
    public void clicks_on_the_next_button() {
        ProfileHooks.notificationsPage.checkoutNextBtn.click();
    }
    @When("User select a payment method")
    public void user_select_a_payment_method() {
        ProfileHooks.actions.moveToElement(ProfileHooks.notificationsPage.checkoutPaymentOption).click().perform();
    }
    @When("clicks on the Submit Payment button")
    public void clicks_on_the_submit_payment_button() {
        ProfileHooks.notificationsPage.submitPaymentBtn.click();
    }
    @When("User clicks on the Place Order button")
    public void user_clicks_on_the_place_order_button() {
        ProfileHooks.notificationsPage.placeOrderBtn.click();
    }
    @Then("a notification with title {string} is displayed")
    public void a_notification_with_title_is_displayed(String expectedNotificationTitle) {
        String actualNotificationTitle = ProfileHooks.notificationsPage.notificationTitle.getText();
        Assertions.assertEquals(expectedNotificationTitle, actualNotificationTitle);
    }
}
