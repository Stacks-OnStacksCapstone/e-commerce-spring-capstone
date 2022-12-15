package com.revature.stepimplementations.products;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.revature.pages.EditProductPage.Edit_Products_Button;
import static com.revature.pages.ProductsDisplayPage.Display_Products;

public class SearchProductsSteps {

    @When("I search for {string}")
    public void i_search_for(String product) {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products)));
        Products.ProductsDisplayPage.Search_Products_Input.sendKeys(product);
        Products.ProductsDisplayPage.Search_Button.click();
        System.out.println("I search for " + product);
    }
    @Then("I should see an empty results page")
    public void i_should_see_an_empty_results_page() {
        boolean isInvisible = Products.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Display_Products)));
        Assertions.assertTrue(isInvisible);
        System.out.println("I see empty results page");
    }
    @When("I cancel the search")
    public void i_cancel_the_search() {
        Products.ProductsDisplayPage.Cancel_Search_Button.click();
        System.out.println("I cancel the search");
    }

    @Then("{string} should be in the title of results")
    public void shouldBeInTheTitleOfResults(String expectedProductTitle) {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products)));
        String actualProductTitle = Products.ProductsDisplayPage.Product_Title_in_Display.getText();
        Assertions.assertEquals(expectedProductTitle, actualProductTitle);
        System.out.println("product should be in the title");
    }
}
