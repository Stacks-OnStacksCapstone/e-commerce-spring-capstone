package com.revature.stepimplementations.darkmode;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
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
        actions.moveToElement(frontPage.darkModeSwitch).click().pause(1).click().build().perform();
    }

    @Then("The theme of the front page changes to dark mode")
    public void the_theme_of_the_front_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = frontPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

    // REGISTER PAGE
    @Given("User navigates to the register page")
    public void user_navigates_to_the_register_page() {
        driver.get("http://localhost:3000/register");
        Assert.assertEquals("http://localhost:3000/register", driver.getCurrentUrl());
    }

    // waiting for register page to be created
    @Then("The theme of the register page changes to dark mode")
    public void the_theme_of_the_register_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        //String bgColor = registerPage.pageBody.getCssValue("background-color");
        //Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

    // LOGIN PAGE
    @Given("User navigates to the login page")
    public void user_navigates_to_the_login_page() {
        driver.get("http://localhost:3000/login");
        Assert.assertEquals("http://localhost:3000/login", driver.getCurrentUrl());
    }

    @Then("The theme of the login page changes to dark mode")
    public void the_theme_of_the_login_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = loginPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

    // PROFILE PAGE
    @Given("User navigates to the profile page")
    public void user_navigates_to_the_profile_page() {
        driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        frontPage.profileLink.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userProfile"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/userProfile", actualUrl);
    }

    @Then("The theme of the profile page changes to dark mode")
    public void the_theme_of_the_profile_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = userProfilePage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

    // ORDERS PAGE
    @Given("User navigates to the orders page")
    public void user_navigates_to_the_orders_page() {
        driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        frontPage.ordersLink.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/orders"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/orders", actualUrl);
    }

    @Then("The theme of the orders page changes to dark mode")
    public void the_theme_of_the_orders_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = ordersPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

    // PRODUCT PAGE
    @Given("User navigates to the product page")
    public void user_navigates_to_the_product_page() {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Hooks.frontPage.ordersLink.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/orders"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='root']//div[4]//div[2]//a[@href='/products/4']")));
        Hooks.ordersPage.capLink.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/products/4"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/products/4", actualUrl);
    }

    @Then("The theme of the product page changes to dark mode")
    public void the_theme_of_the_product_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = productDetailsPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

    // CART PAGE
    @Given("User navigates to the cart page")
    public void user_navigates_to_the_cart_page() {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Hooks.frontPage.cartButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/cart"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/cart", actualUrl);
    }

    @Then("The theme of the cart page changes to dark mode")
    public void the_theme_of_the_cart_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = cartPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
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
        Hooks.frontPage.cartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div[2]//div[@class='sc-fLlhyt hwJrhf']/button")));
        Hooks.cartPage.checkoutButton.click();
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/checkout"));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/checkout", actualUrl);
    }

    @Then("The theme of the checkout page changes to dark mode")
    public void the_theme_of_the_checkout_page_changes_to_dark_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = checkoutPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
    }

     // BACK TO LIGHT MODE

    @When("User clicks the theme switch again")
    public void user_clicks_the_theme_switch_again() {
        actions.moveToElement(frontPage.darkModeSwitch).click().build().perform();
    }

    @Then("The theme of the front page changes back to light mode")
    public void the_theme_of_the_front_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = frontPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The theme of the register page changes back to light mode")
    public void the_theme_of_the_register_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        //String bgColor = registerPage.pageBody.getCssValue("background-color");
        //Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The theme of the login page changes back to light mode")
    public void the_theme_of_the_login_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = loginPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The theme of the profile page changes back to light mode")
    public void the_theme_of_the_profile_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = userProfilePage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The theme of the orders page changes back to light mode")
    public void the_theme_of_the_orders_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = ordersPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The theme of the product page changes back to light mode")
    public void the_theme_of_the_product_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = productDetailsPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The theme of the cart page changes back to light mode")
    public void the_theme_of_the_cart_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = cartPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }

    @Then("The theme of the checkout page changes back to light mode")
    public void the_theme_of_the_checkout_page_changes_back_to_light_mode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
        String bgColor = checkoutPage.pageBody.getCssValue("background-color");
        Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
    }
}
