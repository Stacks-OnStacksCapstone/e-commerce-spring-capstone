package com.revature.stepimplementations.cart;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductCart {
    @Given("the user is on the Home Page")
    public void the_user_is_on_the_home_page() {
        Hooks.driver.get("http://localhost:3000/");
    }

    @When("the user clicks the cart icon on the product to add it to the cart")
    public void the_user_clicks_the_cart_icon_on_the_product_to_add_it_to_the_cart(){
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Hooks.actions.moveToElement(Hooks.cartPage.addHeadPhonesToCart).click().build().perform();
    }
    @Then("one item from that product should be added to the cart page")
    public void one_item_from_that_product_should_be_added_to_the_cart_page() {

        String actualProductsAdded= Hooks.cartPage.cartIconINProduct.getText();
        Assert.assertEquals("1", actualProductsAdded);
        // set 2 in expected to see it fail
    }

    //update
    @Given("the user clicks on the Cart link")
    public void the_user_clicks_on_the_cart_link() {
        Hooks.actions.moveToElement(Hooks.cartPage.cartLink).click().build().perform();
    }
    @Given("the user is on the Cart Page")
    public void the_user_is_on_the_cart_page(){
        Hooks.driver.get("http://localhost:3000/");
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Hooks.actions.moveToElement(Hooks.cartPage.addHeadPhonesToCart).click().build().perform();
        Hooks.actions.moveToElement(Hooks.cartPage.cartLink).click().build().perform();
    }

    @When("the user clicks on plus button in item that is in the cart")
    public void the_user_clicks_on_plus_button_in_item_that_is_in_the_cart(){
        Hooks.actions.moveToElement(Hooks.cartPage.plusItemButton).click().build().perform();
    }
    @Then("the quantity of items for that product should be updated")
    public void the_quantity_of_items_for_that_product_should_be_updated() {
        String actualProductsAdded= Hooks.cartPage.quantityOfItems.getText();
        Assert.assertEquals("2", actualProductsAdded);
        // set 1 in expected to see it fail
    }

    @When("the user clicks on less button in item that is in the cart")
    public void the_user_clicks_on_less_button_in_item_that_is_in_the_cart() throws InterruptedException {
        Hooks.actions.moveToElement(Hooks.cartPage.lessItemButton).click().build().perform();
        Thread.sleep(3000);
    }

    //Update removing 1 item from products
    @Given("the user added two items from a determined product")
    public void the_user_added_two_items_from_a_determined_product(){
        Hooks.driver.get("http://localhost:3000/");
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Hooks.actions.moveToElement(Hooks.cartPage.addHeadPhonesToCart).click().build().perform();
        Hooks.actions.moveToElement(Hooks.cartPage.addHeadPhonesToCart).click().build().perform();
    }
    @When("the user go to the Cart Page")
    public void the_user_go_to_the_cart_page(){
        Hooks.actions.moveToElement(Hooks.cartPage.cartLink).click().build().perform();
    }
    @Then("the quantity of items for that product should be only one")
    public void the_quantity_of_items_for_that_product_should_be_only_one() {
        String actualProductsAdded= Hooks.cartPage.quantityOfItems.getText();
        Assert.assertEquals("1", actualProductsAdded);
        // set 2 in expected to see it fail
    }

    //delete
    @When("the user clicks on delete button in item that is in the cart")
    public void the_user_clicks_on_delete_button_in_item_that_is_in_the_cart() {
        Hooks.actions.moveToElement(Hooks.cartPage.deleteProductButton).click().build().perform();
    }
    @Then("the product should not be in the cart")
    public void the_product_should_not_be_in_the_cart() {
        //Without product
        //body/div[@id='root']/div[2]/div[1]/div[1]

        //With product
        //body/div[@id='root']/div[2]/div[1]/div[2]

        String emptyCartText= Hooks.cartPage.emptyCart.getText();
        if(Hooks.cartPage.emptyCart.isDisplayed()){
            System.out.println("There is not products: "+emptyCartText);

        }else{
            System.out.println("There is some products");
        }
    }
}
