package com.revature.stepimplementations.payment;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductCart {
    @Given("the user is on the Home Page")
    public void the_user_is_on_the_home_page() {
        Hooks.driver.get("http://localhost:3000/");
    }
    @When("the user see the products")
    public void the_user_see_the_products() {

    }
    @When("the user clicks on {string} products link")
    public void the_user_clicks_on_products_link(String string) {

    }
    @Then("products should be added to the {string}")
    public void products_should_be_added_to_the(String string) {

    }

    //update

    @Given("the user is on the Cart Page")
    public void the_user_is_on_the_cart_page() {

    }
    @When("the user see the products that were added to the cart")
    public void the_user_see_the_products_that_were_added_to_the_cart() {

    }
    @When("the user clicks on plus button")
    public void the_user_clicks_on_plus_button() {

    }
    @Then("the quantity of items for that product should be updated")
    public void the_quantity_of_items_for_that_product_should_be_updated() {

    }

    @When("the user clicks on less button")
    public void the_user_clicks_on_less_button() {

    }

    //delete

    @When("the user see the products that were added in the cart")
    public void the_user_see_the_products_that_were_added_in_the_cart() {

    }
    @When("the user clicks on delete button")
    public void the_user_clicks_on_delete_button() {

    }
    @Then("the product should  not be in the cart")
    public void the_product_should_not_be_in_the_cart() {

    }

}
