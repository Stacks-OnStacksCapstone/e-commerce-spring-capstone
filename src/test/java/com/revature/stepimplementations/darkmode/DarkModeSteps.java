package com.revature.stepimplementations.darkmode;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.revature.stepimplementations.hooks.Hooks.*;

public class DarkModeSteps {

    @Given("User navigates to the {string} page")
    public void user_navigates_to_the_page(String type) {
        switch (type) {
            case "front":
                driver.get("http://localhost:3000/");
                Assert.assertEquals("http://localhost:3000/", driver.getCurrentUrl());
                break;

            case "register":
                driver.get("http://localhost:3000/register");
                Assert.assertEquals("http://localhost:3000/register", driver.getCurrentUrl());
                break;

            case "login":
                driver.get("http://localhost:3000/login");
                Assert.assertEquals("http://localhost:3000/login", driver.getCurrentUrl());
                break;

            case "profile":
                driver.get("http://localhost:3000/login");
                Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
                Hooks.loginPage.passwordInput.sendKeys("password");
                Hooks.loginPage.loginButton.click();
                wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
                generalPage.profileLink.click();
                wait.until(ExpectedConditions.urlToBe("http://localhost:3000/userProfile"));
                Assert.assertEquals("http://localhost:3000/userProfile", driver.getCurrentUrl());
                break;

            case "orders":
                driver.get("http://localhost:3000/login");
                Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
                Hooks.loginPage.passwordInput.sendKeys("password");
                Hooks.loginPage.loginButton.click();
                wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
                generalPage.ordersLink.click();
                wait.until(ExpectedConditions.urlToBe("http://localhost:3000/orders"));
                Assert.assertEquals("http://localhost:3000/orders", driver.getCurrentUrl());
                break;

            case "product":
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
                Assert.assertEquals("http://localhost:3000/products/4", driver.getCurrentUrl());
                break;

            case "cart":
                Hooks.driver.get("http://localhost:3000/login");
                Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
                Hooks.loginPage.passwordInput.sendKeys("password");
                Hooks.loginPage.loginButton.click();
                Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
                generalPage.cartButton.click();
                Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/cart"));
                Assert.assertEquals("http://localhost:3000/cart", driver.getCurrentUrl());
                break;

            case "checkout":
                Hooks.driver.get("http://localhost:3000/login");
                Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
                Hooks.loginPage.passwordInput.sendKeys("password");
                Hooks.loginPage.loginButton.click();
                Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
                actions.moveToElement(frontPage.coatAddButton).click().pause(1).click().build().perform();
                Hooks.generalPage.cartButton.click();
                Hooks.cartPage.checkoutButton.click();
                Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/checkout"));
                Assert.assertEquals("http://localhost:3000/checkout", driver.getCurrentUrl());

            case "edit products":
                driver.get("http://localhost:3000/login");
                Hooks.loginPage.emailInput.sendKeys("testuser@gmail.com");
                Hooks.loginPage.passwordInput.sendKeys("password");
                Hooks.loginPage.loginButton.click();
                wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
                generalPage.editProductsLink.click();
                wait.until(ExpectedConditions.urlToBe("http://localhost:3000/admin/products"));
                Assert.assertEquals("http://localhost:3000/admin/products", driver.getCurrentUrl());
        }
    }

    @When("The theme switch is set towards the sun")
    public void the_theme_switch_is_set_towards_the_sun() {
        actions.moveToElement(generalPage.darkModeSwitch).click().build().perform();
        Assert.assertTrue(generalPage.switchOnLightMode.isDisplayed());
    }

    @When("User clicks the theme switch {string}")
    public void user_clicks_the_theme_switch(String clicks) {
        if (clicks.equals("once")) {
            actions.moveToElement(generalPage.darkModeSwitch).click().build().perform();
        } else {
            actions.moveToElement(generalPage.darkModeSwitch).click().pause(1).click().build().perform();
        }
    }

    @Then("The switch slides towards the {string} icon")
    public void the_switch_slides_towards_the_icon(String icon) {
        if (icon.equals("moon")) {
            wait.until(ExpectedConditions.visibilityOf(generalPage.switchOnDarkMode)).isDisplayed();
        } else {
            wait.until(ExpectedConditions.visibilityOf(generalPage.switchOnLightMode)).isDisplayed();
        }
    }

    @Then("The theme of the page changes to {string} mode")
    public void the_theme_of_the_page_changes_to_mode(String theme) {
        if (theme.equals("dark")) {
            wait.until(ExpectedConditions.visibilityOf(generalPage.pageBody));
            String bgColor = generalPage.pageBody.getCssValue("background-color");
            Assert.assertEquals("rgba(18, 18, 18, 1)", bgColor);
        } else {
            wait.until(ExpectedConditions.visibilityOf(generalPage.pageBody));
            String bgColor = generalPage.pageBody.getCssValue("background-color");
            Assert.assertEquals("rgba(255, 255, 255, 1)", bgColor);
        }
    }

    @Then("The font color changes to {string}")
    public void the_font_color_changes_to(String color) {
        if (color.equals("white")) {
            wait.until(ExpectedConditions.visibilityOf(generalPage.pageText));
            String fontColor = generalPage.pageText.getCssValue("color");
            Assert.assertEquals("rgba(255, 255, 255, 1)", fontColor);
        } else {
            wait.until(ExpectedConditions.visibilityOf(generalPage.pageText));
            String fontColor = generalPage.pageText.getCssValue("color");
            Assert.assertEquals("rgba(0, 0, 0, 0.87)", fontColor);
        }
    }
}
