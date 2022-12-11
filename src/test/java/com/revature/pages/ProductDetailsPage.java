package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

    @FindBy(xpath = "//div[@id='root']/button[1]")
    public static WebElement returnToProdBtn;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/span")
    public static WebElement avgRating;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/p")
    public static WebElement productDescription;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/div[2]//label[5]")
    public static WebElement productFiveStarRating;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/div[2]/div[1]//textarea")
    public static WebElement productReviewInput;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/div[2]/div[2]//button")
    public static WebElement submitButton;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/div[3]/div")
    public static WebElement productReviews;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/div[3]//p")
    public static WebElement noReviewsMessage;

    @FindBy(xpath = "//div[@id='root']/div[2]/div[3]/div[3]//button")
    public static WebElement reviewDeleteButton;

    @FindBy(xpath = "//body")
    public static WebElement pageBody;

    public ProductDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
