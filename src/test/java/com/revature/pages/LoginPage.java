package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(xpath = "//form//input[@id='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//form//input[@id='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//form//button[contains(text(), 'Sign In')]")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
