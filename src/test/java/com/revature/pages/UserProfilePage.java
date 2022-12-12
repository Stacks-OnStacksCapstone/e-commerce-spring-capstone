package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserProfilePage {

    @FindBy(xpath = "//strong[text()='SIGN IN']")
    public WebElement signInLink;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement loginEmailInputField;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement loginPasswordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signInButton;

    @FindBy(xpath = "//strong[text()='PROFILE']")
    public WebElement profileLink;

    @FindBy(xpath = "//main[@color='inherit'][1]//h5")
    public WebElement updateProfileMUIBoxHeader;

    @FindBy(xpath = "//main[@color='inherit']//input[@id='firstName']")
    public WebElement muiFirstnameInputField;

    @FindBy(xpath = "//main[@color='inherit']//input[@id='lastName']")
    public WebElement muiLastnameInputField;

    @FindBy(xpath = "//main[@color='inherit']//input[@id='password']")
    public WebElement muiPasswordInputField;

    @FindBy(xpath = "//main[@color='inherit']//button[text()='Update']")
    public WebElement muiUpdateButton;

    @FindBy(xpath = "//div[@id='root']//div[3]/div[2]//div[2]")
    public WebElement uAlert;

    @FindBy(xpath = "//div[@color='inherit']//h5")
    public WebElement deactivateAccountMUIBoxHeader;

    @FindBy(xpath = "//div[@color='inherit']//input[@id='deactivate']")
    public WebElement muiDeactivateInputField;

    @FindBy(xpath = "//div[@color='inherit']//button[text()='Deactivate']")
    public WebElement muiDeactivateButton;

    @FindBy(xpath = "//main[@color='inherit'][1]//input")
    public List<WebElement> uInputFields;

    @FindBy(xpath = "//div[@role='presentation']//div[2]")
    public WebElement deactivateAccountAlert;

    public UserProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}