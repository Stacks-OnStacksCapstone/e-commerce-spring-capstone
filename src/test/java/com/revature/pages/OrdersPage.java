package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

    @FindBy(xpath = "//div[@id='root']//div[3]//li[3]/button")
    public static WebElement ordersForwardNav;

    @FindBy(xpath = "//div[@id='root']//div[3]//li[1]/button")
    public static WebElement ordersBackNav;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//span[2]//p[1]")
    public static WebElement orderDate;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//span[2]//p[2]")
    public static WebElement orderTotal;

    @FindBy(xpath = "//div[@id='root']//div[4]//div[2]//a[@href='/products/4']")
    public static WebElement capLink;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[1]/div/p")
    public static WebElement capDescription;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[1]")
    public static WebElement capOrderDetailId;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[2]")
    public static WebElement capOrderId;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[3]")
    public static WebElement capQuantity;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[4]")
    public static WebElement capProductId;

    @FindBy(xpath = "//div[@id='root']//div[4]//div[2]//a[@href='/products/5']")
    public static WebElement coatLink;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[2]//div[1]/div/p")
    public static WebElement coatDescription;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[2]//div[2]/div/p")
    public static WebElement coatOrderDetails;

    @FindBy(xpath = "//body")
    public static WebElement pageBody;

    public OrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
