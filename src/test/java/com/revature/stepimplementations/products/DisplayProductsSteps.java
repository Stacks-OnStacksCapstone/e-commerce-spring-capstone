package com.revature.stepimplementations.products;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.revature.pages.ProductsDisplayPage.*;

public class DisplayProductsSteps {

    @Given("admin on the home page")
    public void i_am_a_admin_on_the_home_page() {
        Hooks.driver.get(HomePage_URL);
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Sign_In_Button))).click();
        Hooks.ProductsDisplayPage.Email_Input.sendKeys(Admin_Email);
        Hooks.ProductsDisplayPage.Password_Input.sendKeys(Admin_Password);
        Hooks.ProductsDisplayPage.Sign_Up_Button.click();

        System.out.println("I am an admin");
    }

    @Given("user on the home page")
    public void i_am_a_user_on_the_home_page() {
        Hooks.driver.get(HomePage_URL);
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Sign_In_Button))).click();
        Hooks.ProductsDisplayPage.Email_Input.sendKeys(User_Email);
        Hooks.ProductsDisplayPage.Password_Input.sendKeys(User_Password);
        Hooks.ProductsDisplayPage.Sign_Up_Button.click();

        System.out.println("I am a user");
    }

    @Given("guest on the home page")
    public void i_am_a_guest_on_the_home_page() {
        Hooks.driver.get(HomePage_URL);

        System.out.println("I am a guest");
    }

    @Then("user should see products displayed on page")
    public void i_should_see_products_displayed_on_page() {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products)));
        int numberOfProducts = Hooks.driver.findElements(By.xpath(Display_Products)).size();
        Assertions.assertTrue(numberOfProducts > 0);

        System.out.println("Products displayed on page? Expected: At least 1 displayed ::: Actual: " + numberOfProducts);
    }

    @When("user selects Headphones")
    public void i_select_headphones() {
        Hooks.ProductsDisplayPage.Product_1.click();
        System.out.println("I selected headphones");
    }

    @When("user selects TeeShirt")
    public void i_select_teeshirt() {
        Hooks.ProductsDisplayPage.Product_2.click();
        System.out.println("tshirt selected");
    }

    @When("user selects Baseball Cap")
    public void i_select_baseball_cap() {
        Hooks.ProductsDisplayPage.Product_4.click();
        System.out.println("I select Baseball Cap");
    }

    @Then("user should see shopping cart update")
    public void i_should_see_my_shopping_cart_update() {
        String badgeNumber = Hooks.ProductsDisplayPage.Badge_Cart_Number.getText();
        System.out.println("badge number::: " + badgeNumber);
        Assertions.assertEquals("1", badgeNumber);

        System.out.println("Expected: 1 ::: Actual: " +badgeNumber);
    }

    @When("{string} is in their shopping cart")
    public void the_goes_to_their_shopping_cart(String userType) {
        Hooks.ProductsDisplayPage.Menu_Cart_Link.click();
        System.out.println(userType + " goes to their shopping cart");
    }

    @Then("user should see the {string} in their cart")
    public void i_should_see_the_i_selected_in_my_cart(String expectedProduct) {
        String actualText = Hooks.ProductsDisplayPage.Product_Title_in_Cart.getText();
        Assertions.assertTrue(actualText.endsWith(expectedProduct));

        System.out.println("Expected: True ::: Actual: " + actualText.endsWith(expectedProduct));
    }

}