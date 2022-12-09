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

    @FindBy(xpath = "//body/div[@id='root']/div[3]/div[1]/div[1]/div[1]/div[1]/span[1]/*[1]")
    public WebElement addHeadPhonesToCart;

    @FindBy(xpath = "//body/div[@id='root']/div[3]/div[2]/div[1]/div[1]/div[1]/span[1]/*[1]")
    public WebElement addTeeShirtToCart;

    @FindBy(xpath = "//body/div[@id='root']/div[3]/div[3]/div[1]/div[1]/div[1]/span[1]/*[1]")
    public WebElement addShoppingBagToCart;

    @FindBy(xpath = "//body/div[@id='root']/div[3]/div[4]/div[1]/div[1]/div[1]/span[1]/*[1]")
    public WebElement addBaseballCapToCart;

    @FindBy(xpath = "//body/div[@id='root']/div[3]/div[5]/div[1]/div[1]/div[1]/span[1]/*[1]")
    public WebElement addCoatToCart;


    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[2]/div[3]/span[1]/*[1]")
    public WebElement cartLink;

    @FindBy(xpath = "//body/div[@id='root']/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/button[3]")
    public WebElement plusItemButton;

    @FindBy(xpath = "//body/div[@id='root']/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/button[1]")
    public WebElement lessItemButton;

    @FindBy(xpath = "//body/div[@id='root']/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/button[2]/*[1]")
    public WebElement deleteProductButton;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
