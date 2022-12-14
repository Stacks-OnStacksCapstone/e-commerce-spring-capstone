package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralPage {

    @FindBy(xpath = "//body")
    public static WebElement pageBody;

    @FindBy(xpath = "//div[@id='root']//*[self::p or self::b or self::h1 or self::h5 or self::h6]")
    public static WebElement pageText;

    // NAV BAR
    @FindBy(xpath = "//div[@id='root']//input[@class='jss4 MuiSwitch-input']")
    public static WebElement darkModeSwitch;

    @FindBy(xpath = "//div[@id='root']//span[@class='MuiButtonBase-root MuiIconButton-root jss1 MuiSwitch-switchBase MuiSwitch-colorPrimary']")
    public static WebElement switchOnDarkMode;

    @FindBy(xpath = "//div[@id='root']//span[@class='MuiButtonBase-root MuiIconButton-root jss1 MuiSwitch-switchBase MuiSwitch-colorPrimary jss2 Mui-checked']")
    public static WebElement switchOnLightMode;

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
