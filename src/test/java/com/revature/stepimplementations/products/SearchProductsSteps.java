package com.revature.stepimplementations.products;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.revature.pages.ProductsDisplayPage.Display_Products;

public class SearchProductsSteps {

    @When("user searches for {string}")
    public void i_search_for(String product) {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products)));
        Hooks.ProductsDisplayPage.Search_Products_Input.sendKeys(product);
        Hooks.ProductsDisplayPage.Search_Button.click();
        System.out.println("I search for " + product);
    }
    @Then("user should see an empty results page")
    public void i_should_see_an_empty_results_page() {
        boolean isInvisible = Hooks.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Display_Products)));
        Assertions.assertTrue(isInvisible);
        System.out.println("I see empty results page");
    }
    @When("user closes the search")
    public void i_cancel_the_search() {
        Hooks.ProductsDisplayPage.Cancel_Search_Button.click();
        System.out.println("I cancel the search");
    }

    @Then("{string} should be in the title of results")
    public void shouldBeInTheTitleOfResults(String expectedProductTitle) {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products)));
        String actualProductTitle = Hooks.ProductsDisplayPage.Product_Title_in_Display.getText();
        Assertions.assertEquals(expectedProductTitle, actualProductTitle);
        System.out.println("product should be in the title");
    }
}
