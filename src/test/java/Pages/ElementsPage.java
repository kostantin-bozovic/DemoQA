package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElementsPage extends BaseTest {

    // Constructor for Element Page Class, public modifier
    public ElementsPage(){
        PageFactory.initElements(driver, this);
    }

    // POM Locators, covered: className, css, id...
    @FindBy(css = ".pattern-backgound.playgound-header")
    public WebElement pageTitle;

    // TEXT BOX ELEMENTS
    @FindBy(id = "userName")
    public WebElement fullNameTextBox; // full name
    @FindBy(id = "userEmail")
    public WebElement emailTextBox; // email
    @FindBy(id = "currentAddress")
    public WebElement currentAddressTextBox; // current address
    @FindBy(id = "permanentAddress")
    public WebElement permanentAddressTextBox; // permanent address
    @FindBy(id = "submit")
    public WebElement submitButton; // submit button
    @FindBy(css = ".border.col-md-12.col-sm-12")
    public WebElement submittedForm; // submitted form


    //------------------------------------------------------------------------------------------------------------------
    // TEXT BOX

    // Methods for inserting full name, email, addresses
    // First clear old input if exist and then submit new
    public void insertFullName(String fullName){
        fullNameTextBox.clear();
        fullNameTextBox.sendKeys(fullName);
    }
    public void insertEmail(String email){
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
    }
    public void insertCurrentAddress(String address){
        currentAddressTextBox.clear();
        currentAddressTextBox.sendKeys(address);
    }
    public void insertPermanentAddress(String address){
        permanentAddressTextBox.clear();
        permanentAddressTextBox.sendKeys(address);
    }

    //------------------------------------------------------------------------------------------------------------------
    //  Method that perform action on web element
    //------------------------------------------------------------------------------------------------------------------
    // -> click on submit button
    public void clickSubmitButton(){
        submitButton.click();
    }
    //------------------------------------------------------------------------------------------------------------------
    // -> return text of page title and submitted form
    public String getPageTitleText(){
        return pageTitle.getText();
    }
    public String submittedFormText(){
        return submittedForm.getText();
    }
    //------------------------------------------------------------------------------------------------------------------
    // -> return if given label is visible for Text Box (true/false)
    //    create web element by combining given name of element and common text part of id "-label"
    public boolean labelIsDisplayed(String nameOfElement){

        WebElement element = driver.findElement(By.id(nameOfElement + "-label"));
        return element.isDisplayed();
    }
    //------------------------------------------------------------------------------------------------------------------

}
