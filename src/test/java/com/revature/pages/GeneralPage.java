package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralPage {

    @FindBy(xpath = "//body")
    public static WebElement pageBody;

    // NAV BAR
    @FindBy(xpath = "//div[@id='root']//input[@class='jss4 MuiSwitch-input']")
    public static WebElement darkModeSwitch;

    @FindBy(xpath = "//div[@id='root']//strong[contains(text(), 'PROFILE')]")
    public static WebElement profileLink;

    @FindBy(xpath = "//div[@id='root']//strong[contains(text(), 'ORDERS')]")
    public static WebElement ordersLink;

    @FindBy(xpath = "//div[@id='root']//div/div[3]/span")
    public static WebElement cartButton;

    public GeneralPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
