package com.revature.steps;

import com.revature.runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class UserRegistrationSteps {

    ChromeDriver driver = Runner.driver;

    @Given("a user is on the home page")
    public void a_user_is_on_the_home_page() {
        driver.navigate().to("http://localhost:3000/");


    }
    @When("a user clicks register")
    public void a_user_clicks_register() {
        WebElement register = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[1]/strong"));
        register.click();
        }
    @When("a user enters a first name")
    public void a_user_enters_a_first_name() {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        WebElement firstName = driver.findElement(By.xpath("/html/body/div/main/div/form/div[1]/div[1]/div/div/input"));
        firstName.sendKeys("John");
    }
    @When("a user enter a last name")
    public void a_user_enter_a_last_name() {
    WebElement lastName = driver.findElement(By.xpath("/html/body/div/main/div/form/div[1]/div[2]/div/div/input"));
    lastName.sendKeys("Doe");
    }
    @When("a user enters an email address")
    public void a_user_enters_an_email_address() {
        String emailString = "";
        WebElement email = driver.findElement(By.xpath("/html/body/div/main/div/form/div[1]/div[3]/div/div/input"));

        Random r = new Random();
        int randInt = r.nextInt(12000-5000) + 5000;
        emailString += ""+randInt;
        email.sendKeys((emailString+"@FakeMail.com"));

    }
    @When("a user enters a password")
    public void a_user_enters_a_password() {
        WebElement password = driver.findElement(By.xpath("/html/body/div/main/div/form/div[1]/div[4]/div/div/input"));
        password.sendKeys("Password1!");
    }
    @When("a user clicks sign up")
    public void a_user_clicks_sign_up() {
        WebElement signUpButton = driver.findElement(By.xpath("/html/body/div/main/div/form/button"));
        signUpButton.click();

    }


    @Then("the user should see the login page")
    public void theUserShouldSeeTheLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/main/div/form/div[1]/div/input")));
        WebElement username = driver.findElement(By.xpath("/html/body/div/main/div/form/div[1]/div/input"));
        Assertions.assertNotNull(username);
    }
}
