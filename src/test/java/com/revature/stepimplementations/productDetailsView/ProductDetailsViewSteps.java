package com.revature.stepimplementations.productDetailsView;

import com.revature.stepimplementations.hooks.Hooks;
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
        Hooks.driver.get("http://localhost:3000");
    }
    @When("they browse the page")
    public void they_browse_the_page() {
        List<WebElement> products = new ArrayList<>(Hooks.productDetailsViewPage.products);
        for (WebElement p : products) {
            try {
                Assertions.assertTrue(p.isDisplayed());
            } catch (Exception e) {
                Assertions.fail("Exception occurred");
            }
        }
    }
    @Then("they are able to see products' titles, descriptions, and prices")
    public void they_are_able_to_see_products_titles_descriptions_and_prices() {
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productImageH)).isDisplayed());
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productTileH)).isDisplayed());
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productPriceH)).isDisplayed());
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productDescH)).isDisplayed());
    }
    @When("they hover over the images of the products for the plus icons to be displayed and clicked on the icons")
    public void they_hover_over_the_images_of_the_products_for_the_plus_icons_to_be_displayed_and_clicked_on_the_icons() {
        Hooks.productDetailsViewPage.productPlusIcon.click();
    }
    @When("modals pop up")
    public void modals_pop_up() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productModal));
        Assertions.assertTrue(Hooks.productDetailsViewPage.productModal.isDisplayed());
    }
    @Then("once again, they are able to see products' titles, descriptions, and prices")
    public void once_again_they_are_able_to_see_products_titles_descriptions_and_prices() {
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productTitleM)).isDisplayed());
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productImageM)).isDisplayed());
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productPriceM)).isDisplayed());
        Assertions.assertTrue(Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productDescM)).isDisplayed());
    }

    // scenario outline
    @Given("User or Admin is logged in with {string} and {string}")
    public void user_or_admin_is_logged_in_with_email_and_password(String email, String password) {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.userProfilePage.loginEmailInputField.sendKeys(email);
        Hooks.userProfilePage.loginPasswordInputField.sendKeys(password);
        Hooks.userProfilePage.signInButton.click();
    }
}
