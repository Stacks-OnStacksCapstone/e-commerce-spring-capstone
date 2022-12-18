package com.revature.stepimplementations.orderhistory;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static com.revature.stepimplementations.hooks.Hooks.driver;
import static com.revature.stepimplementations.hooks.Hooks.ordersPage;


public class OrderHistorySteps {

    // BACKGROUND
    @Given("User logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        driver.get("http://localhost:3000/login");
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
        String actual = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/orders", actual);
    }

    @Then("User sees a list of their previous orders")
    public void user_sees_a_list_of_their_previous_orders() {
        Assert.assertTrue(Hooks.ordersPage.previousOrder.isDisplayed());
        List<WebElement> previousOrders = driver.findElements(By.xpath("//div[@id='root']//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column']"));
        System.out.println("Number of previous orders: " + previousOrders.size());
        for (int i = 0; i < previousOrders.size(); i++) {
            String actualText = previousOrders.get(i).getText();
            System.out.println(actualText);
        }
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
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productDescription));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("http://localhost:3000/products/"));
    }
    @Then("User can view product rating and reviews")
    public void user_can_view_product_rating_and_reviews() {
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.avgRating));
        Hooks.wait.until(ExpectedConditions.visibilityOf(Hooks.productDetailsViewPage.productReviews));
        Assert.assertTrue(Hooks.productDetailsViewPage.avgRating.isDisplayed());
    }


}

