package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsDisplayPage {

    public static String HomePage_URL = "http://localhost:3000";
    public static String Admin_Email = "testuser@gmail.com";
    public static String Admin_Password = "password";
    public static String User_Email = "jane@gmail.com";
    public static String User_Password = "password";

    public static String Display_Products = "//div[@class=\"sc-ezWOiH kwluif\"]";
    public static String Display_Products_Admin = "(//div[@class=\"sc-gXmSlM jEuCRW\"])//img";

    public static String Sign_In_Button = "//strong[contains(text(),'SIGN IN')]";

    public static String New_Product_Title_in_Display = "(//*[@id=\"root\"]//h5)[last()]";

    public static String New_Product_Description_in_Display = "(//*[@id=\"root\"]//p)[last()]";

    public static String New_Product_Price_in_Display = "(//*[@id=\"root\"]//h6)[last()]";

    public static String Last_Product_Displayed = "(//div[@class=\"sc-gXmSlM jEuCRW\"]/div)[last()]";

    public static String First_Product_Displayed = "(//div[@class=\"sc-gXmSlM jEuCRW\"]/div)[2]";
    @FindBy(id = "email" )
    public WebElement Email_Input;

    @FindBy(id = "password" )
    public WebElement Password_Input;

    @FindBy(xpath="//form/button")
    public WebElement Sign_Up_Button;

    @FindBy(xpath="(//span[@class='MuiBadge-root'])[2]")
    public WebElement Product_1;
    @FindBy(xpath="(//span[@class='MuiBadge-root'])[3]")
    public WebElement Product_2;

    @FindBy(xpath="(//span[@class='MuiBadge-root'])[4]")
    public WebElement Product_3;
    @FindBy(xpath="(//span[@class='MuiBadge-root'])[5]")
    public WebElement Product_4;

    @FindBy(xpath="(//span[@class='MuiBadge-root'])[6]")
    public WebElement Product_5;

    @FindBy(xpath="//span[@class='MuiBadge-root']/span")
    public WebElement Badge_Cart_Number;

    @FindBy(xpath="(//span[@class='MuiBadge-root'])[1]")
    public WebElement Menu_Cart_Link;

    @FindBy(xpath="//div[@class=\"sc-ksZaOG jRBEfX\"]/span[1]")
    public WebElement Product_Title_in_Cart;

    @FindBy(xpath = "(//h5)[1]")
    public WebElement Product_Title_in_Display;

    @FindBy(xpath = "(//p)[1]")
    public WebElement Product_Description_in_Display;

    @FindBy(xpath = "(//h6)[1]")
    public WebElement Product_Price_in_Display;

    @FindBy(xpath = "//div/button[1]")
    public WebElement Search_Button;

    @FindBy(xpath = "//div/button[2]")
    public WebElement Cancel_Search_Button;

    @FindBy(xpath = "//div/input")
    public WebElement Search_Products_Input;

    public ProductsDisplayPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
