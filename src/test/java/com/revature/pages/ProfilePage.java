package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    @FindBy(xpath = "//strong[text()='SIGN IN']")
    public WebElement signInLink;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement loginEmailInput;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement loginPasswordInput;

    @FindBy(xpath = "//body/div[@id='root']/main[1]/div[1]/form[1]/button[1]")
    public WebElement signInButton;

    @FindBy(xpath = "//strong[contains(text(),'PROFILE')]")
    public WebElement profileLink;


    @FindBy(xpath = "//input[@id='cardNumber']")
    public WebElement cardNumberInput;

    @FindBy(xpath = "//input[@id='expDate']")
    public WebElement expDateInput;

    @FindBy(xpath = "//input[@id='ccv']")
    public WebElement ccvInput;

    @FindBy(xpath = "//body/div[@id='root']/main[2]/div[1]/form[1]/div[1]/div[4]/div[1]/button[1]")
    public WebElement addPaymentButton;


    @FindBy(xpath = "//tbody/tr[1]/td[4]/button[1]")
    public WebElement delPaymentButton;

    @FindBy(xpath = "//div[@id='root']//div[3]/div[2]//div[2]")
    public WebElement alert;

    @FindBy(xpath = "//tbody/tr[198]/td[4]/button[1]")//wrong xpath to fail cause the update button do not exist
    public WebElement updatePaymentButton;

    public ProfilePage(WebDriver driver) {
        //super();
        PageFactory.initElements(driver, this);
    }

}
