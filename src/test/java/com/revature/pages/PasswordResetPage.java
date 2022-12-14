package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetPage {

    // For the /forgot-password path
    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//button[contains(text(), 'Send Reset Password Link')]")
    public WebElement passwordResetLinkButton;

    @FindBy(xpath = "//h1[contains(text(), 'Send a link to your Email')]/following-sibling::p")
    public WebElement message;

    // For the /reset-password/{token} path

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordInput;
    @FindBy(xpath = "//button[contains(text(), 'Reset Password')]")
    public WebElement passwordResetButton;


    public PasswordResetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
