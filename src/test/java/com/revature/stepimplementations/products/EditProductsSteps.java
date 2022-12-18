package com.revature.stepimplementations.products;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

import static com.revature.pages.EditProductPage.*;
import static com.revature.pages.EditProductPage.Delete_Product_Button;
import static com.revature.pages.ProductsDisplayPage.*;

public class EditProductsSteps {

    private static final SoftAssertions softAssertions = new SoftAssertions();

    // Background

    @And("admin has access to edit products")
    public void iHaveAccessToEditProducts() {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Edit_Products_Button))).click();
        System.out.println("i have access to edit products");
    }

    // Scenario 1 : Create Product

    @When("admin creates a new product")
    public void iGoToCreateAProduct() {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Create_New_Product_Button))).click();
        System.out.println("create new product");
    }

    @And("admin enters new information")
    public void iEnterNewInformation(DataTable table) {
        List<Map<String, String>> createNewProduct = table.asMaps();
        String productName = createNewProduct.get(0).get("Name");
        String productDescription  = createNewProduct.get(0).get("Description");
        String productPrice  = createNewProduct.get(0).get("Price");
        System.out.println(productName + productDescription + productPrice);
        Hooks.EditProductPage.Create_Product_Name_Input.sendKeys(productName);
        Hooks.EditProductPage.Create_Description_Input.sendKeys(productDescription);
        Hooks.EditProductPage.Create_Price_Input.sendKeys(productPrice);
    }

    @When("admin creates the product")
    public void iCreateTheProduct() {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Create_Product_Button))).click();
        System.out.println("create product");
    }

    @Then("admin should see the new product update")
    public void iShouldSeeTheNewProductUpdate() {
        Hooks.wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("http://localhost:3000/admin/product")));
        String newUpdateName = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Name))).getText();
        String newUpdatePrice = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Price))).getText();
        String newUpdateDescription = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Description))).getText();
        Assertions.assertEquals("Fitness Tracker", newUpdateName);
        Assertions.assertEquals("$99.00", newUpdatePrice);
        Assertions.assertEquals("Stress Management", newUpdateDescription);
        System.out.println(newUpdateName + newUpdatePrice + newUpdateDescription);

    }

    @When("admin checks all products available")
    public void iGoToCheckAllProducts() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Back_To_Products_Button))).click();
        System.out.println("back to products button clicked");
    }

    @Then("admin should see the new product displayed")
    public void iShouldSeeTheNewProductDisplayed() {
        Hooks.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/admin/products"));
        String newProductTitle = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(New_Product_Title_in_Display))).getText();
        String newProductDescription = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(New_Product_Description_in_Display))).getText();
        String newProductPrice = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(New_Product_Price_in_Display))).getText();
        Assertions.assertEquals("Fitness Tracker", newProductTitle);
        Assertions.assertEquals("Stress Management", newProductDescription);
        Assertions.assertEquals("$99.00", newProductPrice);
        System.out.println("Expected: Fitness Tracker :::  Actual: " +newProductTitle);
        System.out.println("Expected: Stress Management :::  Actual: " +newProductDescription);
        System.out.println("Expected: $99.00 :::  Actual: " +newProductPrice);
    }


    // Scenario 2: Update Product

    @When("admin selects the Headphones")
    public void iSelectTheHeadphonesProduct() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(First_Product_Displayed))).click();
        System.out.println("selected the headphones product");
    }

    @Then("admin should see pre-populated data")
    public void iShouldSeePrePopulatedData() {
        String pN = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Name_Input))).getText();
        boolean productName = pN.isEmpty();
        String pURL = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Img_Input))).getText();
        boolean productURL = pURL.isEmpty();
        String pDesc = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Description_Input))).getText();
        boolean productDesc = pDesc.isEmpty();
        String pPrice = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Price_Input))).getText();
        boolean productPrice = pPrice.isEmpty();

        softAssertions.assertThat(productName).isEqualTo(false);
        softAssertions.assertThat(productURL).isEqualTo(false);
        softAssertions.assertThat(productDesc).isEqualTo(false);
        softAssertions.assertThat(productPrice).isEqualTo(false);


    }

    @And("admin makes changes with the following details")
    public void iChangeTheseDetails(DataTable dataTable) {
        List<Map<String, String>> createNewProduct = dataTable.asMaps();
        String updateProductDescription  = createNewProduct.get(0).get("Description");
        String updateProductPrice  = createNewProduct.get(0).get("Price");
        Hooks.EditProductPage.Product_Description_Input2.sendKeys(updateProductDescription);
        Hooks.EditProductPage.Product_Price_Input2.sendKeys(updateProductPrice);
    }

    @When("admin updates the product")
    public void iUpdateTheProduct() throws InterruptedException {
        Hooks.EditProductPage.Update_Product_Button.click();
        Thread.sleep(150);
        System.out.println("update button clicked");
    }

    @Then("admin should see product update")
    public void iShouldSeeProductUpdate() {
        String newUpdateDescription = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Description))).getText();
        String newUpdatePrice = Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Price))).getText();
        Assertions.assertEquals("$40.00", newUpdatePrice);
        Assertions.assertEquals("New amazing audio quality", newUpdateDescription);
        System.out.println("Expected: $40.00  ::: Actual:  " + newUpdatePrice);
        System.out.println("Expected: \"New amazing audio quality\"  ::: Actual:  " + newUpdateDescription);
    }

    @Then("admin should see the new update displayed")
    public void iShouldSeeTheNewUpdateDisplayed() {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products_Admin)));
        String priceDisplay = Hooks.ProductsDisplayPage.Product_Price_in_Display.getText();
        String descriptionDisplay = Hooks.ProductsDisplayPage.Product_Description_in_Display.getText();
        Assertions.assertEquals("$40.00", priceDisplay);
        Assertions.assertEquals("New amazing audio quality", descriptionDisplay);
        System.out.println("product seen in display of products");
        softAssertions.assertAll();
    }


    // Scenario 3: Delete a Product
    @When("admin selects an existing product")
    public void iSelectTheNewlyCreatedProduct() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Last_Product_Displayed))).click();
        System.out.println("selected newly created product");
    }

    @And("admin deletes it")
    public void iDeleteIt() {
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Delete_Product_Button))).click();
        System.out.println("clicked the delete button");
    }


    @Then("It should no longer be displayed with products")
    public void itShouldNoLongerBeDisplayedWithProducts() {
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products_Admin)));
        int numberOfProducts = Hooks.driver.findElements(By.xpath(Display_Products_Admin)).size();
        Assertions.assertEquals(5, numberOfProducts);
    }

    // Users and Guests can not edit products

    @Then("user should not be able to edit products")
    public void iSeeNoWhereToEditProducts() {
        boolean isInvisible = Hooks.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Edit_Products_Button)));
        Assertions.assertTrue(isInvisible);
    }
}
