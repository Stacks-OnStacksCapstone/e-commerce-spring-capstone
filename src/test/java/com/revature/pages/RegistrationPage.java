package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstnameInput;

    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastnameInput;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordInput;
    @FindBy(xpath = "//*[@id='firstName-helper-text']")
    public WebElement firstnameHelperText;

    @FindBy(xpath = "//*[@id='lastName-helper-text']")
    public WebElement lastnameHelperText;

    @FindBy(xpath = "//*[@id='email-helper-text']")
    public WebElement emailHelperText;

    @FindBy(xpath = "//*[@id='password-helper-text']")
    public WebElement passwordHelperText;

    @FindBy(xpath = "//button[contains(text(), 'Sign Up')]")
    public WebElement signupButton;

    public RegistrationPage(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

}
