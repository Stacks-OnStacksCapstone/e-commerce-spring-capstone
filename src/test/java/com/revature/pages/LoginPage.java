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

    @FindBy(xpath = "//input[@id='email']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;

    @FindBy(xpath = "//*[@id=\"root\"]/main/div/form/div[3]/div/div[2]")
    public WebElement message;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
