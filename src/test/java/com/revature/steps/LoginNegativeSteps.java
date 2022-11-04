package com.revature.steps;

import com.revature.runner.Runner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginNegativeSteps {
    ChromeDriver driver = Runner.driver;
    @When("a user enters {string} into the input username")
    public void aUserEntersIntoTheInputUsername(String arg0) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/main/div/form/div[1]/div/input")));
        WebElement username = driver.findElement(By.xpath("/html/body/div/main/div/form/div[1]/div/input"));
        username.sendKeys(arg0);

    }

    @When("a user enters {string} into the password input box")
    public void aUserEntersTeamIntoThePasswordInputBox(String arg0) {
        WebElement password = driver.findElement(By.xpath("/html/body/div/main/div/form/div[2]/div/input"));
        password.sendKeys(arg0);

    }

    @Then("the user should see an alert that their account is deactivated")
    public void theUserShouldSeeAnAlertThatTheirAccountIsDeactivated() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/main/div/form/div[3]")));
       Assertions.assertNotNull(driver.findElement(By.xpath("/html/body/div/main/div/form/div[3]")));


    }
}
