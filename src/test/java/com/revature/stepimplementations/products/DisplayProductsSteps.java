package com.revature.stepimplementations.products;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.revature.pages.ProductsDisplayPage.*;

public class DisplayProductsSteps {

    @Given("I am a admin on the home page")
    public void i_am_a_admin_on_the_home_page() {
        Products.driver.get(HomePage_URL);
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Sign_In_Button))).click();
        Products.ProductsDisplayPage.Email_Input.sendKeys(Admin_Email);
        Products.ProductsDisplayPage.Password_Input.sendKeys(Admin_Password);
        Products.ProductsDisplayPage.Sign_Up_Button.click();

        System.out.println("I am an admin");
    }

    @Given("I am a user on the home page")
    public void i_am_a_user_on_the_home_page() {
        Products.driver.get(HomePage_URL);
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Sign_In_Button))).click();
        Products.ProductsDisplayPage.Email_Input.sendKeys(User_Email);
        Products.ProductsDisplayPage.Password_Input.sendKeys(User_Password);
        Products.ProductsDisplayPage.Sign_Up_Button.click();

        System.out.println("I am a user");
    }

    @Given("I am a guest on the home page")
    public void i_am_a_guest_on_the_home_page() {
        Products.driver.get(HomePage_URL);

        System.out.println("I am a guest");
    }

    @Then("I should see products displayed on page")
    public void i_should_see_products_displayed_on_page() {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products)));
        int numberOfProducts = Products.driver.findElements(By.xpath(Display_Products)).size();
        Assertions.assertTrue(numberOfProducts > 0);

        System.out.println("Products displayed on page? Expected: At least 1 displayed ::: Actual: " + numberOfProducts);
    }

    @When("I select Headphones")
    public void i_select_headphones() {
        Products.ProductsDisplayPage.Product_1.click();
        System.out.println("I selected headphones");
    }

    @When("I select TeeShirt")
    public void i_select_teeshirt() {
        Products.ProductsDisplayPage.Product_2.click();
        System.out.println("tshirt selected");
    }

    @When("I select Baseball Cap")
    public void i_select_baseball_cap() {
        Products.ProductsDisplayPage.Product_4.click();
        System.out.println("I select Baseball Cap");
    }

    @Then("I should see my shopping cart update")
    public void i_should_see_my_shopping_cart_update() {
        String badgeNumber = Products.ProductsDisplayPage.Badge_Cart_Number.getText();
        System.out.println("badge number::: " + badgeNumber);
        Assertions.assertEquals("1", badgeNumber);

        System.out.println("Expected: 1 ::: Actual: " +badgeNumber);
    }

    @When("The {string} goes to their shopping cart")
    public void the_goes_to_their_shopping_cart(String userType) {
        Products.ProductsDisplayPage.Menu_Cart_Link.click();
        System.out.println(userType + " goes to their shopping cart");
    }

    @Then("I should see the {string} I selected in my cart")
    public void i_should_see_the_i_selected_in_my_cart(String expectedProduct) {
        String actualText = Products.ProductsDisplayPage.Product_Title_in_Cart.getText();
        Assertions.assertTrue(actualText.endsWith(expectedProduct));

        System.out.println("Expected: True ::: Actual: " + actualText.endsWith(expectedProduct));
    }

}