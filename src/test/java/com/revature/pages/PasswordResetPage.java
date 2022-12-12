package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetPage {

    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//button[contains(text(), 'Send Reset Password Link')]")
    public WebElement passwordResetButton;

    public PasswordResetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
