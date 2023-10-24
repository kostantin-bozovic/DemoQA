package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ElementsPage extends BaseTest {

    // Constructor for Element Page Class, public modifier
    public ElementsPage(){
        PageFactory.initElements(driver, this);
    }

    // POM Locators, covered: className, css, id...
    @FindBy(css = ".pattern-backgound.playgound-header")
    public WebElement pageTitle;

    //------------------------------------------------------------------------------------------------------------------
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
    // CHECK BOX

    @FindBy(css = ".rct-option.rct-option-expand-all") // expend all checkboxes - button
    public WebElement expendCheckboxes;
    @FindBy(css = ".rct-option.rct-option-collapse-all") // collapse all checkboxes - button
    public WebElement collapseCheckboxes;
    @FindBy( css = ".rct-icon.rct-icon-uncheck") // number of unchecked checkboxes
    public List<WebElement> uncheckedCheckbox;
    @FindBy( css = ".rct-icon.rct-icon-check") // number of checked checkboxes
    public List<WebElement> checkedCheckbox;
    @FindBy( css = ".rct-icon.rct-icon-check") // number of checked checkboxes
    public WebElement checkedCheckboxButton;
    @FindBy(css = ".rct-icon.rct-icon-expand-close") // symbol for expending
    public WebElement expendSymbol;
    @FindBy(css = ".rct-icon.rct-icon-expand-open") // symbol for expending
    public WebElement collapseSymbol;


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
    // CHECK BOX

    public void expendAll(){
        expendCheckboxes.click();
    }
    public void collapseAll(){
        collapseCheckboxes.click();
    }
    public void clickOnUnchecked(int i) {
        uncheckedCheckbox.get(i).click();
    }
}
