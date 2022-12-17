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

    @FindBy(xpath = "//div[@id='root']//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column']")
    public static WebElement previousOrder;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//span[2]//p[1]")
    public static WebElement orderDate;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//span[2]//p[2]")
    public static WebElement orderTotal;

    @FindBy(xpath = "//div[@id='root']//div[4]//div[2]//a[@href='/products/4']")
    public static WebElement productLink;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[1]/div/p")
    public static WebElement productDescription;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[1]")
    public static WebElement productOrderDetailId;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[2]")
    public static WebElement productOrderId;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[3]")
    public static WebElement productQuantity;

    @FindBy(xpath = "//div[@id='root']//div[4]/div[1]//div[2]/div/p[4]")
    public static WebElement productId;

    public OrdersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
