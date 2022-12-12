package com.revature.stepimplementations.orderhistory;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderHistorySteps {

    // BACKGROUND
    @Given("User logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        Hooks.driver.get("http://localhost:3000/login");
        Hooks.loginPage.emailInput.sendKeys("jane@gmail.com");
        Hooks.loginPage.passwordInput.sendKeys("password");
        Hooks.loginPage.loginButton.click();
    }

    @When("User clicks the Orders link")
    public void user_clicks_the_orders_link() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(Hooks.generalPage.ordersLink));
        Hooks.generalPage.ordersLink.click();
    }

    // VIEW LIST OF PREVIOUS ORDERS

    @Given("User is on the Orders page")
    public void user_is_on_the_orders_page() {
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/orders"));
        String actual = Hooks.driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/orders", actual);
    }

    @Then("User sees a list of their previous orders")
    public void user_sees_a_list_of_their_previous_orders() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.previousOrders));
        Assert.assertTrue(Hooks.ordersPage.previousOrders.isDisplayed());
    }

    @Then("User can see the order date and total of each order")
    public boolean user_can_see_the_order_date_and_total_of_each_order() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.orderDate));
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.orderTotal));
        String actualDate = Hooks.ordersPage.orderDate.getText();
        String actualTotal = Hooks.ordersPage.orderTotal.getText();
        boolean containsDate = actualDate.contains("Order date:");
        boolean containsTotal = actualTotal.contains("Order total:");
        if (containsDate && containsTotal) {
            return true;
        } else {
            return false;
        }
    }

    // VIEW ORDER DETAILS OF PREVIOUS ORDERS
    @Then("User can view the order detail ID and order ID")
    public boolean user_can_view_the_order_detail_id_and_order_id() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.productOrderDetailId));
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.productOrderId));
        String actualOrderDetailId = Hooks.ordersPage.productOrderDetailId.getText();
        String actualOrderId = Hooks.ordersPage.productOrderId.getText();
        boolean containsDetailId = actualOrderDetailId.contains("OrderDetail ID:");
        boolean containsOrderId = actualOrderId.contains("Order ID:");
        if (containsDetailId && containsOrderId) {
            return true;
        } else {
            return false;
        }

    }

    @Then("User can view the product purchased")
    public void user_can_view_the_product_purchased() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.productLink));
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.productDescription));
        Assert.assertTrue(Hooks.ordersPage.productLink.isDisplayed());
        Assert.assertTrue(Hooks.ordersPage.productDescription.isDisplayed());
    }

    @Then("User can view the product ID and quantity of the product purchased")
    public boolean user_can_view_the_product_id_and_quantity_of_the_product_purchased() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.productId));
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.ordersPage.productQuantity));
        String actualProductId = Hooks.ordersPage.productId.getText();
        String actualQuantity = Hooks.ordersPage.productQuantity.getText();
        boolean containsProductId = actualProductId.contains("Product ID:");
        boolean containsQuantity = actualQuantity.contains("Quantity:");
        if (containsProductId && containsQuantity) {
            return true;
        } else {
            return false;
        }
    }

    // VIEW PRODUCT DETAILS OF A PREVIOUS ORDER

    @When("User clicks on the name of a product from a previous order")
    public void user_clicks_on_the_name_of_a_product_from_a_previous_order() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(Hooks.ordersPage.productLink));
        Hooks.ordersPage.productLink.click();
    }
    @Then("User navigates to the details page of the product")
    public void user_navigates_to_the_details_page_of_the_product() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsPage.productDescription));
        String actualUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("http://localhost:3000/products/"));
    }
    @Then("User can view product rating and reviews")
    public void user_can_view_product_rating_and_reviews() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsPage.avgRating));
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsPage.productReviews));
        Assert.assertTrue(Hooks.productDetailsPage.avgRating.isDisplayed());
    }

    // DELETE PRODUCT REVIEW FROM PREVIOUS ORDER

    @When("User clicks the delete button for their previous review")
    public void user_clicks_the_delete_button_for_their_previous_review() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(Hooks.productDetailsPage.reviewDeleteButton));
        Hooks.productDetailsPage.reviewDeleteButton.click();
    }
    @Then("The review is removed from the product reviews")
    public void the_review_is_removed_from_the_product_reviews() {
        Hooks.wait.until(ExpectedConditions.textToBePresentInElement(Hooks.productDetailsPage.noReviewsMessage, "No reviews.."));
        String actual = Hooks.productDetailsPage.noReviewsMessage.getText();
        Assert.assertEquals("No reviews..", actual);
    }

    // ADD PRODUCT REVIEW TO PREVIOUS ORDER

    @When("User selects a rating for the product")
    public void user_selects_a_rating_for_the_product() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(Hooks.productDetailsPage.productFiveStarRating));
        Hooks.productDetailsPage.productFiveStarRating.click();
    }
    @When("User enters a review for the product")
    public void user_enters_a_review_for_the_product() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(Hooks.productDetailsPage.productReviewInput));
        Hooks.productDetailsPage.productReviewInput.sendKeys("This product is high quality.");
    }
    @When("User clicks the submit button")
    public void user_clicks_the_submit_button() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(Hooks.productDetailsPage.submitButton));
        Hooks.productDetailsPage.submitButton.click();
    }
    @Then("User sees their rating and review added to the product reviews")
    public void user_sees_their_rating_and_review_added_to_the_product_reviews() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsPage.productReviews));
        String actual = Hooks.productDetailsPage.productReviews.getText();
        Assert.assertEquals("Jane" + "\n" + "This product is high quality.", actual);
    }
}

