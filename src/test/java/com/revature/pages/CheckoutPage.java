package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    @FindBy(xpath = "//div[@id='root']/main//h6")
    public static WebElement checkoutPageTitle;

    // SHIPPING ADDRESS

    @FindBy(xpath = "//form/div[1]//input[@id='firstName']")
    public static WebElement firstNameInput;

    @FindBy(xpath = "//form/div[1]//input[@id='lastName']")
    public static WebElement lastNameInput;

    @FindBy(xpath = "//form/div[1]//input[@id='address1']")
    public static WebElement addressInput;

    @FindBy(xpath = "//form/div[1]//input[@id='city']")
    public static WebElement cityInput;

    @FindBy(xpath = "//form/div[1]//input[@id='state']")
    public static WebElement stateInput;

    @FindBy(xpath = "//form/div[1]//input[@id='zip']")
    public static WebElement zipInput;

    @FindBy(xpath = "//form/div[1]//input[@id='country']")
    public static WebElement countryInput;

    @FindBy(xpath = "//form/div[2]/button[contains(text(), 'Next')]")
    public static WebElement nextButton;

    // PAYMENT METHOD

    @FindBy(xpath = "//form//div//table")
    public static WebElement paymentOptions;

    @FindBy(xpath = "//form//div//table/input[@type='radio']")
    public static WebElement paymentRadioButton;

    @FindBy(xpath = "//form//div//button[contains(text(), 'Submit')]")
    public static WebElement submitPaymentButton;

    @FindBy(xpath = "//form//div//button[contains(text(), 'Back')]")
    public static WebElement paymentBackButton;

    // ORDER REVIEW

    @FindBy(xpath = "//div[@id='root']//ul[@class='MuiList-root css-1uzmcsd']")
    public static WebElement itemList;

    @FindBy(xpath = "//div[@id='root']/main//div[2]/div[1]/p")
    public static WebElement shippingAddressDetails;

    @FindBy(xpath = "//div[@id='root']/main//div[2]//div[2]/div")
    public static WebElement paymentDetails;

    @FindBy(xpath = "//div[@id='root']/main//div[3]/button[contains(text(), 'Place')]")
    public static WebElement placeOrderButton;

    @FindBy(xpath = "//div[@id='root']/main//div[3]/button[contains(text(), 'Back')]")
    public static WebElement reviewBackButton;

    // CHECKOUT MESSAGE

    @FindBy(xpath = "//div[@id='root']/main//h6")
    public static WebElement checkoutMessage;


    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
