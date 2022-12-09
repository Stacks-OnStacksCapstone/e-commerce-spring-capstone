package com.revature.stepsImplementation.productDetailsView;

import com.revature.stepsImplementation.userProfile.ProfileHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsViewSteps {
    @Given("A Guesses is at the home page")
    public void a_guesses_is_at_the_home_page() {
        ProfileHooks.driver.get("http://localhost:3000");
    }
    @When("they browse the page")
    public void they_browse_the_page() {
        List<WebElement> products = new ArrayList<>(ProfileHooks.productDetailsViewPage.products);
        for (WebElement p : products) {
            try {
                Assertions.assertTrue(p.isDisplayed());
            } catch (Exception e) {
                Assertions.fail("Exception occurred");
                e.getStackTrace();
                break;
            }
        }
    }
    @Then("they are able to see products' titles, descriptions, and prices")
    public void they_are_able_to_see_products_titles_descriptions_and_prices() {
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productImageH)).isDisplayed());
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productTileH)).isDisplayed());
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productPriceH)).isDisplayed());
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productDescH)).isDisplayed());
    }
    @When("they hover over the images of the products for the plus icons to be displayed and clicked on the icons")
    public void they_hover_over_the_images_of_the_products_for_the_plus_icons_to_be_displayed_and_clicked_on_the_icons() {
        ProfileHooks.productDetailsViewPage.productPlusIcon.click();
    }
    @When("modals pop up")
    public void modals_pop_up() {
        Assertions.assertTrue(ProfileHooks.productDetailsViewPage.productModal.isDisplayed());
    }
    @Then("once again, they are able to see products' titles, descriptions, and prices")
    public void once_again_they_are_able_to_see_products_titles_descriptions_and_prices() {
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productTitleM)).isDisplayed());
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productImageM)).isDisplayed());
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productPriceM)).isDisplayed());
        Assertions.assertTrue(ProfileHooks.wait.until(ExpectedConditions.visibilityOf(ProfileHooks.productDetailsViewPage.productDescM)).isDisplayed());
    }

    // scenario outline
    @Given("User or Admin is logged in with {string} and {string}")
    public void user_or_admin_is_logged_in_with_email_and_password(String email, String password) {
        ProfileHooks.driver.get("http://localhost:3000/login");
        ProfileHooks.userProfilePage.loginEmailInputField.sendKeys(email);
        ProfileHooks.userProfilePage.loginPasswordInputField.sendKeys(password);
        ProfileHooks.userProfilePage.signInButton.click();
    }
}
