package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProductPage {

    // Buttons
    public static String Edit_Products_Button = "//strong[contains(text(),'EDIT PRODUCTS')]";

    public static String Create_New_Product_Button = "//button[contains(text(),'Create New Product')]";
    public static String Create_Product_Button = "//button[contains(text(),'create product')]";
    public static String Back_To_Products_Button = "//button[contains(text(),'Back to Products')]";

    @FindBy(xpath = "//button[contains(text(), 'Update')]")
    public WebElement Update_Product_Button;

    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    public WebElement Delete_Product_Button;

    // When you update or create a product, these fields should update on top above inputs
    public static String Updated_Name = "//*[@id=\"root\"]//h3";
    public static String Updated_Price = "//*[@id=\"root\"]//h4";
    public static String Updated_Description = "//*[@id=\"root\"]//h5";

    // Used to check if input fields are populated when UPDATING a product AND send keys() to it
    public static String Product_Name_Input = "(//input)[2]";
    public static String Product_Img_Input = "(//input)[3]";
    public static String Product_Description_Input = "(//input)[4]";
    public static String Product_Price_Input = "(//input)[5]";

    @FindBy(xpath = "//strong[contains(text(),'EDIT PRODUCTS')]")
    public WebElement Edit_Products_Displayed;

    @FindBy(xpath = "//*[@id='root']/div[2]/div[1]/div[1]/div[2]/div[1]/input[1]")
    public WebElement Create_Product_Name_Input;

    @FindBy(xpath = "//*[@id='root']/div[2]/div[1]/div[1]/div[3]/div[1]/input[1]")
    public WebElement Create_Description_Input;

    @FindBy(xpath = "//*[@id='root']/div[2]/div[1]/div[1]/div[5]/div[1]/input[1]")
    public WebElement Create_Price_Input;

    public EditProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
