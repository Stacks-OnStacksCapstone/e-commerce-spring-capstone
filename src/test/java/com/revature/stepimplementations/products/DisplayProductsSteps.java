package com.revature.stepimplementations.products;

import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.revature.stepimplementations.hooks.Hooks.wait;


public class DisplayProductsSteps {

    @Given("I am a guest on the home page")
    public void i_am_a_guest_on_the_home_page() {
        Hooks.driver.get("http://localhost:3000");
        //wait.until(ExpectedConditions.urlToBe("http://localhost:3000"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[contains(text(),'SIGN IN')]")));
        // Hooks.driver.findElement(By.xpath("//strong[contains(text(),'SIGN IN')]")).click();
    }

    @When("I select headphones")
    public void i_select_headphones() {
        Hooks.driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[1]/div/div[1]/div[1]/span")).click();
    }

    @Then("I should see my shopping cart update")
    public void i_should_see_my_shopping_cart_update() throws InterruptedException {
        Thread.sleep(1000);
        String badgeNumber = Hooks.driver.findElement(By.xpath("span[@class=\"MuiBadge-root\"][2]")).getText();
        Assertions.assertEquals("1", badgeNumber);
    }

    @When("I select my shopping cart")
    public void i_select_my_shopping_cart() {
        Hooks.driver.findElement(By.xpath("span[@class=\"MuiBadge-root\"][1]")).click();
    }

    @Then("I should see the headphones I selected in my cart")
    public void i_should_see_the_headphones_i_selected_in_my_cart() {
        String actualText = Hooks.driver.findElement(By.xpath("//strong[contains(text(),'Headphones')]")).getText();
        Assertions.assertEquals("Headphones", actualText);
    }

    @Given("I am a user on the home page")
    public void i_am_a_user_on_the_home_page() throws InterruptedException {
        Hooks.driver.get("http://localhost:3000");
        Thread.sleep(1000);
        Hooks.driver.findElement(By.id("email")).sendKeys("jane@gmail.com");
        Hooks.driver.findElement(By.id("password")).sendKeys("password");
        Hooks.driver.findElement(By.xpath("//form/button")).click();
    }

    @When("I select teeshirt")
    public void i_select_teeshirt() {
        Hooks.driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[2]/div/div[1]/div[1]/span")).click();
    }

    @Then("I should see the teeshirt I selected in my cart")
    public void i_should_see_the_teeshirt_i_selected_in_my_cart() {
        String actualText = Hooks.driver.findElement(By.xpath("//strong[contains(text(),'TeeShirt')]")).getText();
        Assertions.assertEquals("TeeShirt", actualText);
    }

    @Given("I am a admin on the home page")
    public void i_am_a_admin_on_the_home_page() throws InterruptedException {
        Hooks.driver.get("http://localhost:3000");
        Thread.sleep(1000);
        Hooks.driver.findElement(By.id("email")).sendKeys("testuser@gmail.com");
        Hooks.driver.findElement(By.id("password")).sendKeys("password");
        Hooks.driver.findElement(By.xpath("//form/button")).click();
    }

    @When("I select baseball cap")
    public void i_select_baseball_cap() {
        Hooks.driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div[4]/div/div[1]/div[1]/span")).click();
    }

    @Then("I should see the baseball cap I selected in my cart")
    public void i_should_see_the_baseball_cap_i_selected_in_my_cart() {
        String actualText = Hooks.driver.findElement(By.xpath("//strong[contains(text(),'Baseball Cap')]")).getText();
        Assertions.assertEquals("Baseball Cap", actualText);
    }

    @Then("I should see products displayed on page")
    public void i_should_see_products_displayed_on_page() {
        int numberOfProducts = Hooks.driver.findElements(By.xpath("//div[@class=\"sc-ezWOiH kwluif\"]")).size();
        Assertions.assertTrue(numberOfProducts > 0, "Atleast 1 product is displayed");
    }
}