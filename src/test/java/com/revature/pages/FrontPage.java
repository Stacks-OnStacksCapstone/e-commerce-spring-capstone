package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FrontPage {
    // NAV BAR
    @FindBy(xpath = "//div[@id='root']//input[@class='jss4 MuiSwitch-input']")
    public static WebElement darkModeSwitch;

    @FindBy(xpath = "//div[@id='root']//strong[contains(text(), 'PROFILE')]")
    public static WebElement profileLink;
    @FindBy(xpath = "//div[@id='root']//strong[contains(text(), 'ORDERS')]")
    public static WebElement ordersLink;

    @FindBy(xpath = "//div[@id='root']//div/div[3]/span")
    public static WebElement cartButton;

    // PRODUCT DISPLAY
    @FindBy(xpath = "//div[@id='root']/div[3]")
    public static WebElement productsDisplayed;

    @FindBy(xpath = "//div[@id='root']/div[3]/div[1]//span")
    public static WebElement headphonesAddButton;

    @FindBy(xpath = "//div[@id='root']/div[3]/div[3]//span")
    public static WebElement shoppingBagAddButton;

    @FindBy(xpath = "//div[@id='root']/div[3]/div[5]//span")
    public static WebElement coatAddButton;

    @FindBy(xpath = "//body")
    public static WebElement pageBody;

    public FrontPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
