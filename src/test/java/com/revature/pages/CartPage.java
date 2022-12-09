package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    @FindBy(xpath = "//div[@id='root']/div[2]//div/button[contains(text(), 'CONTINUE SHOPPING')]")
    public static WebElement continueShoppingBtn;

    @FindBy(xpath = "//div[@id='root']/div[2]//div[@class='sc-iqcoie fFyvgU']")
    public static WebElement cartItems;

    @FindBy(xpath = "//div[@id='root']/div[2]//div[@class='sc-fLlhyt hwJrhf']")
    public static WebElement orderSummary;

    @FindBy(xpath = "//div[@id='root']/div[2]//div[@class='sc-fLlhyt hwJrhf']/button")
    public static WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
