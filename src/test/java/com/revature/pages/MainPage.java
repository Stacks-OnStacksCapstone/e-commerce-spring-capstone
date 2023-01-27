package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div/div[1]/div/div[1]/h1/img")
    public WebElement mainPageBtn;

    @FindBy(xpath = "/html/body/div/div[2]/div/div/input")
    public WebElement searchBar;

    @FindBy(xpath = "/html/body/div/div[1]/div/div[2]/span/span[1]/span[1]/input")
    public WebElement themeBtn;

    @FindBy(linkText = "//strong[contains(text(),'REGISTER')]")
    public WebElement register;

    @FindBy(xpath = "//strong[contains(text(),'SIGN IN')]")
    public WebElement signIn;

    @FindBy(xpath = "//strong[contains(text(),'PROFILE')]")
    public WebElement profile;

    @FindBy(xpath = "//strong[contains(text(),'ORDERS')]")
    public WebElement orders;

    @FindBy(xpath = "//strong[contains(text(),'EDIT PRODUCTS')]")
    public WebElement editProduct;

    @FindBy(xpath = "//strong[contains(text(),'LOGOUT')]")
    public WebElement logout;

    @FindBy(xpath = "/html/body/div/div[1]/div/div[2]/div[3]/span/svg/path")
    public WebElement cartBtn;
}
