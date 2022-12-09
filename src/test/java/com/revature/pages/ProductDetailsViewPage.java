package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductDetailsViewPage {

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div")
    public List<WebElement> products;

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div[1]//img")
    public WebElement productImageH;

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div[1]//h5")
    public WebElement productTileH;

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div[1]//p")
    public WebElement productDescH;

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div[1]//h6")
    public WebElement productPriceH;

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div[last()]")
    public WebElement productToBeClicked;

    @FindBy(xpath = "//div[@class='sc-himrzO gFAZYV'][2]/div[last()]//div[@aria-label='view-product-details']")
    public WebElement productPlusIcon;

    @FindBy(xpath = "//div[@role='presentation']/div[3]/div/h2")
    public WebElement productTitleM;

    @FindBy(xpath = "//div[@role='presentation']/div[3]/div/div[1]/img")
    public WebElement productImageM;

    @FindBy(xpath = "//div[@role='presentation']/div[3]/div/div[3]/p")
    public WebElement productDescM;

    @FindBy(xpath = "//div[@role='presentation']/div[3]/div/div[2]/p")
    public WebElement productPriceM;

    @FindBy(xpath = "//div[@role='presentation']/div[3]/div")
    public WebElement productModal;

    public ProductDetailsViewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
