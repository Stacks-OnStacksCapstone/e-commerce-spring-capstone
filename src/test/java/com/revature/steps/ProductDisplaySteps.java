package com.revature.steps;

import com.revature.runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDisplaySteps {
    ChromeDriver driver = Runner.driver;
    @Given("a user or guest navigates to the home page")
    public void aUserOrGuestNavigatesToTheHomePage() {
        driver.navigate().to("http://localhost:3000/");
    }

    @Then("a user or guest should see available products on the home page")
    public void aUserOrGuestShouldSeeAvailableProductsOnTheHomePage() {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div/div/div[2]/div")));
        WebElement product = driver.findElement(By.xpath("/html/body/div/div[3]/div/div/div[2]/div"));
        Assertions.assertNotNull(product);
    }
}
