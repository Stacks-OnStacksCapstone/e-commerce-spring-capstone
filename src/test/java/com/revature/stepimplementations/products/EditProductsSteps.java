package com.revature.stepsImplementation.products;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

import static com.revature.pages.EditProductPage.*;
import static com.revature.pages.ProductsDisplayPage.*;

public class EditProductsSteps {

    // Background

    @And("I have access to edit products")
    public void iHaveAccessToEditProducts() {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Edit_Products_Button))).click();
        System.out.println("i have access to edit products");
    }

    // Scenario 1 : Create Product

    @When("I go to create a product")
    public void iGoToCreateAProduct() {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Create_New_Product_Button))).click();
        System.out.println("create new product");
    }

    @And("I enter new information")
    public void iEnterNewInformation(DataTable table) {
        List<Map<String, String>> createNewProduct = table.asMaps();
        String productName = createNewProduct.get(0).get("Name");
        String productDescription  = createNewProduct.get(0).get("Description");
        String productPrice  = createNewProduct.get(0).get("Price");
        System.out.println(productName + productDescription + productPrice);
        Products.EditProductPage.Create_Product_Name_Input.sendKeys(productName);
        Products.EditProductPage.Create_Description_Input.sendKeys(productDescription);
        Products.EditProductPage.Create_Price_Input.sendKeys(productPrice);
    }

    @When("I create the product")
    public void iCreateTheProduct() {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Create_Product_Button))).click();
        System.out.println("create product");
    }

    @Then("I should see the new product update")
    public void iShouldSeeTheNewProductUpdate() {
        Products.wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("http://localhost:3000/admin/product")));
        String newUpdateName = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Name))).getText();
        String newUpdatePrice = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Price))).getText();
        String newUpdateDescription = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Description))).getText();
        Assertions.assertEquals("Fitness Tracker", newUpdateName);
        Assertions.assertEquals("$99.00", newUpdatePrice);
        Assertions.assertEquals("Stress Management", newUpdateDescription);
        System.out.println(newUpdateName + newUpdatePrice + newUpdateDescription);

    }

    @When("I go to check all products")
    public void iGoToCheckAllProducts() {
        Products.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Back_To_Products_Button))).click();
        System.out.println("back to products button clicked");
    }

    @Then("I should see the new product displayed")
    public void iShouldSeeTheNewProductDisplayed() {
        Products.wait.until(ExpectedConditions.urlToBe("http://localhost:3000/admin/products"));
        String newProductTitle = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(New_Product_Title_in_Display))).getText();
        String newProductDescription = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(New_Product_Description_in_Display))).getText();
        String newProductPrice = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(New_Product_Price_in_Display))).getText();
        Assertions.assertEquals("Fitness Tracker", newProductTitle);
        Assertions.assertEquals("Stress Management", newProductDescription);
        Assertions.assertEquals("$99.00", newProductPrice);
        System.out.println("Expected: Fitness Tracker :::  Actual: " +newProductTitle);
        System.out.println("Expected: Stress Management :::  Actual: " +newProductDescription);
        System.out.println("Expected: $99.00 :::  Actual: " +newProductPrice);
    }

    // Scenario 2: Update Product


    @When("I select the Headphones product")
    public void iSelectTheHeadphonesProduct() {
        Products.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(First_Product_Displayed))).click();
        System.out.println("selected the headphones product");
    }

    @Then("I should see pre-populated data")
    public void iShouldSeePrePopulatedData() {
        String pN = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Name_Input))).getText();
        boolean productName = pN.isEmpty();
        String pURL = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Img_Input))).getText();
        boolean productURL = pURL.isEmpty();
        String pDesc = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Description_Input))).getText();
        boolean productDesc = pDesc.isEmpty();
        String pPrice = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Price_Input))).getText();
        boolean productPrice = pPrice.isEmpty();

        Assertions.assertTrue(productName);
        Assertions.assertTrue(productURL);
        Assertions.assertTrue(productDesc);
        Assertions.assertTrue(productPrice);
    }

    @And("I change these details")
    public void iChangeTheseDetails(DataTable dataTable) {
        List<Map<String, String>> createNewProduct = dataTable.asMaps();
        String updateProductDescription  = createNewProduct.get(0).get("Description");
        String updateProductPrice  = createNewProduct.get(0).get("Price");

        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Description_Input))).sendKeys(updateProductDescription);
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Product_Price_Input))).sendKeys(updateProductPrice);
    }

    @When("I update the product")
    public void iUpdateTheProduct() {
        Products.EditProductPage.Update_Product_Button.click();
        System.out.println("update button clicked");
    }

    @Then("I should see product update")
    public void iShouldSeeProductUpdate() {
        String newUpdatePrice = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Price))).getText();
        String newUpdateDescription = Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Updated_Description))).getText();
        Assertions.assertEquals("$40.00", newUpdatePrice);
        Assertions.assertEquals("New amazing audio quality", newUpdateDescription);
        System.out.println("Expected: $40.00  ::: Actual:  " + newUpdatePrice);
        System.out.println("Expected: \"New amazing audio quality\"  ::: Actual:  " + newUpdateDescription);
    }

    @Then("I should see the new update displayed")
    public void iShouldSeeTheNewUpdateDisplayed() {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products_Admin)));
        String priceDisplay = Products.ProductsDisplayPage.Product_Price_in_Display.getText();
        String descriptionDisplay = Products.ProductsDisplayPage.Product_Description_in_Display.getText();
        Assertions.assertEquals("$40.00", priceDisplay);
        Assertions.assertEquals("New amazing audio quality", descriptionDisplay);
        System.out.println("product seen in display of products");
    }


    // Delete a Product
    @When("I select the newly created product")
    public void iSelectTheNewlyCreatedProduct() {
        Products.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Last_Product_Displayed))).click();
        System.out.println("selected newly created product");
    }

    @And("I delete it")
    public void iDeleteIt() {
        Products.EditProductPage.Delete_Product_Button.click();
    }


    @Then("It should no longer be displayed with products")
    public void itShouldNoLongerBeDisplayedWithProducts() {
        Products.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Display_Products_Admin)));
        int numberOfProducts = Products.driver.findElements(By.xpath(Display_Products_Admin)).size();
        Assertions.assertEquals(5, numberOfProducts);
    }

    // Users and Guests can not edit products

    @Then("I see no where to edit products")
    public void iSeeNoWhereToEditProducts() {
        boolean isInvisible = Products.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Edit_Products_Button)));
        Assertions.assertTrue(isInvisible);
    }
}
