package com.revature.stepimplementations.darkmode;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.revature.stepimplementations.hooks.Hooks.*;

public class DarkModeSteps {

    // FRONT PAGE
    @Given("User navigates to the front page")
    public void user_navigates_to_the_front_page() {
        driver.get("http://localhost:3000/");
        Assert.assertEquals("http://localhost:3000/", driver.getCurrentUrl());
    }
    @When("User clicks the theme switch")
    public void user_clicks_the_theme_switch() {
        actions.moveToElement(generalPage.darkModeSwitch).click().pause(1).click().build().perform();
    }

    @Then("The switch slides towards the moon icon")
    public void the_switch_slides_towards_the_moon_icon() {
        wait.until(ExpectedConditions.visibilityOf(generalPage.switchOnDarkMode));
    }

    @Then("The theme of the page changes to dark mode")
    public void the_theme_of_the_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.visibilityOf(generalPage.pageBody));
        String bgColor = generalPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

    @Then("The font color changes to white")
    public void the_font_color_changes_to_white() {
        wait.until(ExpectedConditions.visibilityOf(generalPage.pageText));
        String fontColor = generalPage.pageText.getCssValue("color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", fontColor);
    }

    // REGISTER PAGE
    @Given("User navigates to the register page")
    public void user_navigates_to_the_register_page() {
        driver.get("http://localhost:3000/register");
        Assert.assertEquals("http://localhost:3000/register", driver.getCurrentUrl());
    }

    // LOGIN PAGE
    @Given("User navigates to the login page")
    public void user_navigates_to_the_login_page() {
        driver.get("http://localhost:3000/login");
        Assert.assertEquals("http://localhost:3000/login", driver.getCurrentUrl());
    }

    // PROFILE PAGE
    @Given("User navigates to the profile page")
    public void user_navigates_to_the_profile_page() {
        driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        generalPage.profileLink.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userProfile"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/userProfile", actualUrl);
    }

    // ORDERS PAGE
    @Given("User navigates to the orders page")
    public void user_navigates_to_the_orders_page() {
        driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        generalPage.ordersLink.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/orders"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/orders", actualUrl);
    }

    // PRODUCT PAGE
    @Given("User navigates to the product page")
    public void user_navigates_to_the_product_page() {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        generalPage.ordersLink.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/orders"));
        wait.until(ExpectedConditions.visibilityOf(ordersPage.productLink));
        Hooks.ordersPage.productLink.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/products/4"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/products/4", actualUrl);
    }

    // CART PAGE
    @Given("User navigates to the cart page")
    public void user_navigates_to_the_cart_page() {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        generalPage.cartButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/cart"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/cart", actualUrl);
    }

    // CHECKOUT PAGE
    @Given("User navigates to the checkout page")
    public void user_navigates_to_the_checkout_page() {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        actions.moveToElement(frontPage.coatAddButton).click().pause(1).click().build().perform();
        Hooks.generalPage.cartButton.click();
        Hooks.cartPage.checkoutButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/checkout"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/checkout", actualUrl);
    }

    // EDIT PRODUCTS PAGE

    @Given("User navigates to the edit products page")
    public void user_navigates_to_the_edit_products_page() {
        driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("testuser@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        generalPage.editProductsLink.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/admin/products"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/admin/products", actualUrl);
    }


    // SWITCH BACK TO LIGHT MODE

    @When("User clicks the theme switch again")
    public void user_clicks_the_theme_switch_again() {
        actions.moveToElement(generalPage.darkModeSwitch).click().build().perform();
    }

    @Then("The switch slides towards the sun icon")
    public void the_switch_slides_towards_the_sun_icon() {
        wait.until(ExpectedConditions.visibilityOf(generalPage.switchOnLightMode));
    }

    @Then("The theme of the page changes back to light mode")
    public void the_theme_of_the_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.visibilityOf(generalPage.pageBody));
        String bgColor = generalPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The font color changes to black")
    public void the_font_color_changes_to_black() {
        wait.until(ExpectedConditions.visibilityOf(generalPage.pageText));
        String fontColor = generalPage.pageText.getCssValue("color");
        Assert.assertEquals("rgba(0, 0, 0, 0.87)", fontColor);
    }
}
