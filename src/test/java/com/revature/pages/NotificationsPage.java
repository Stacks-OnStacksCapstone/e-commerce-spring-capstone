package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotificationsPage {

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div[@class='sc-ezWOiH kwluif'][1]/div/div/div[@class='sc-jIZahH exfsfQ'][1]/span[@class='MuiBadge-root']")
    public WebElement cartIcon;

    @FindBy(xpath = "//div[@class='sc-gsnTZi fiGUBf']/div[2]/div[3]//span[@class='MuiBadge-root']")
    public WebElement cartIconLink;

    @FindBy(xpath = "//div[@class='sc-gKXOVf dJWFGW']/div/div[2]/div[2]//button[@type='button']")
    public WebElement checkoutNowBtn;

    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement checkoutFname;

    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement checkoutLname;

    @FindBy(xpath = "//input[@id='address1']")
    public WebElement checkoutAddressLine;

    @FindBy(xpath = "//input[@id='city']")
    public WebElement checkoutCity;

    @FindBy(xpath = "//input[@id='state']")
    public WebElement checkoutState;

    @FindBy(xpath = "//input[@id='zip']")
    public WebElement checkoutZipCode;

    @FindBy(xpath = "//input[@id='country']")
    public WebElement checkoutCountry;

    @FindBy(xpath = "//button[@type='submit'][1]")
    public WebElement checkoutNextBtn;

    @FindBy(xpath = "//input[@type='radio']")
    public WebElement checkoutPaymentOption;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitPaymentBtn;

    @FindBy(xpath = "//button[text()='Place order']")
    public WebElement placeOrderBtn;

    @FindBy(xpath = "//h5")
    public WebElement notificationTitle;

    public NotificationsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
