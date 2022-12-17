package com.revature.stepimplementations.productreview;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.revature.stepimplementations.hooks.Hooks.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class ProductReviewSteps {

    // FRONT PAGE
    @When("User clicks the magnifying glass icon for shopping bag")
    public void user_clicks_the_magnifying_glass_icon_for_shopping_bag() {
        actions.moveToElement(productDetailsViewPage.bagDetailViewIcon).pause(1).click().build().perform();
    }

    @When("A modal appears displaying the product details")
    public void a_modal_appears_displaying_the_product_details() {
        wait.until(ExpectedConditions.visibilityOf(productDetailsViewPage.productModal));
        String actualTitle = productDetailsViewPage.productTitleM.getText();
        String actualDescription = productDetailsViewPage.productDescM.getText();

        if (actualTitle.contains("Bag")) {
            Assert.assertEquals("Shopping Bag", actualTitle);
            Assert.assertEquals("A reusable shopping bag", actualDescription);
        } else if (actualTitle.contains("TeeShirt")) {
            Assert.assertEquals("TeeShirt", actualTitle);
            Assert.assertEquals("A nice TeeShirt", actualDescription);
        } else {
            System.out.println("A modal was not displayed");
        }
    }

    @When("User enters a rating and review for the product")
    public void user_enters_a_rating_and_review_for_the_product() {
        wait.until(ExpectedConditions.elementToBeClickable(productDetailsViewPage.fourStarRating));
        actions.moveToElement(productDetailsViewPage.fourStarRating).pause(1).click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(productDetailsViewPage.reviewInput));
        productDetailsViewPage.reviewInput.sendKeys("I love this item!");
    }

    @When("User clicks the submit button")
    public void user_clicks_the_submit_button() {
        wait.until(ExpectedConditions.elementToBeClickable(productDetailsViewPage.submitReviewButton));
        productDetailsViewPage.submitReviewButton.click();
    }

    @Then("The review is added to the product reviews")
    public void the_review_is_added_to_the_product_reviews() {
        try {
            wait.until(ExpectedConditions.not(textToBePresentInElement(productDetailsViewPage.productReviews, "No reviews..")));
        } catch (TimeoutException e) {
            Assertions.fail("The product reviews section remained empty");
        }
        String actualText = productDetailsViewPage.productReviews.getText();
        Assert.assertTrue(actualText.contains("Mark" + "\n" + "I love this item!"));
    }

    @When("User clicks the delete button for their previous review")
    public void user_clicks_the_delete_button_for_their_previous_review() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(productDetailsViewPage.deleteReviewButton));
            productDetailsViewPage.deleteReviewButton.click();
        } catch (TimeoutException e) {
            Assertions.fail("There are no existing reviews to delete.");
        }
    }

    @Then("The review is removed from the product reviews")
    public void the_review_is_removed_from_the_product_reviews() {
        wait.until(ExpectedConditions.visibilityOf(productDetailsViewPage.productReviews));
        String actualText = productDetailsViewPage.productReviews.getText();
        Assert.assertEquals("No reviews..", actualText);
    }

    // PRODUCTS PAGE
    @When("User clicks the magnifying glass icon for tee shirt")
    public void user_clicks_the_magnifying_glass_icon_for_tee_shirt() {
        actions.moveToElement(productDetailsViewPage.shirtDetailViewIcon).pause(1).click().build().perform();
    }

    @When("User clicks the expand icon")
    public void user_clicks_the_expand_icon() {
        wait.until(ExpectedConditions.elementToBeClickable(productDetailsViewPage.modalExpandButton));
        productDetailsViewPage.modalExpandButton.click();
    }

    @When("User navigates to the products page for the product")
    public void user_navigates_to_the_products_page_for_the_product() {
        wait.until(ExpectedConditions.not(urlToBe("http://localhost:3000/")));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:3000/products/2", actualUrl);
    }

    // GUEST REVIEW
    @Then("The review is not added to the product reviews")
    public void the_review_is_not_added_to_the_product_reviews() {
        wait.until(ExpectedConditions.visibilityOf(productDetailsViewPage.productReviews));
        String actualText = productDetailsViewPage.productReviews.getText();
        Assert.assertEquals("No reviews..", actualText);
    }

    // MULTIPLE REVIEWS
    @When("User submits another review for the same product")
    public void user_submits_another_review_for_the_same_product() {
        actions.moveToElement(productDetailsViewPage.fourStarRating).pause(1).click().build().perform();
        productDetailsViewPage.reviewInput.sendKeys("This item is great!");
        productDetailsViewPage.submitReviewButton.click();
    }

    @Then("The second review is not added to the product reviews")
    public void the_second_review_is_not_added_to_the_product_reviews() {
        wait.until(ExpectedConditions.visibilityOf(productDetailsViewPage.productReviews));
        String actualText = productDetailsViewPage.productReviews.getText();
        Assert.assertFalse(actualText.contains("This item is great!"));
    }

    // WITHOUT RATING/REVIEW
    @When("User submits a product review without the rating")
    public void user_submits_a_product_review_without_the_rating() {
        productDetailsViewPage.reviewInput.sendKeys("This item is great!");
        productDetailsViewPage.submitReviewButton.click();
    }

    @When("User submits a product review without the review")
    public void user_submits_a_product_review_without_the_review() {
        actions.moveToElement(productDetailsViewPage.fourStarRating).pause(1).click().build().perform();
        productDetailsViewPage.submitReviewButton.click();
    }
}
