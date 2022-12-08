package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    @FindBy(xpath = "//div[@id='root']/div[2]//div/button[contains(text(), 'CONTINUE SHOPPING')]")
    public static WebElement continueShoppingBtn;

   /* @FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;*/

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
