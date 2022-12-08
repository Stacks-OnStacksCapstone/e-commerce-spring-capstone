package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FrontPage {
    @FindBy(xpath = "//div[@id='root']//span//span[1]")
    public static WebElement darkModeSwitch;

    @FindBy(xpath = "//div[@id='root']//strong[contains(text(), 'ORDERS')]")
    public static WebElement ordersButton;

    @FindBy(xpath = "//div[@id='root']//div/div[3]/span")
    public static WebElement cartButton;

    /*@FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;

    @FindBy(xpath = "")
    public static WebElement ;*/

    public FrontPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
