package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    // RADIO BUTTON

    @FindBy(className = "mb-3")
    public WebElement radioButtonMessage; // Message to chose options
    @FindBy(id = "yesRadio")
    public WebElement yesRadioButton; // Yes radio button
    @FindBy(id = "impressiveRadio")
    public WebElement impressiveRadioButton; // Impressive radio button
    @FindBy(id = "noRadio")
    public WebElement noRadioButton; // No radio button

    //------------------------------------------------------------------------------------------------------------------
    // BUTTONS

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[1]/button") // Double click button
    public WebElement doubleClickButton;
    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/button") // Right click button
    public WebElement rightClickButton;
    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/button") // Dynamic Button
    public WebElement clickMeButton;
    @FindBy(id = "doubleClickMessage")
    public WebElement doubleClickMessage; // Double Click message
    @FindBy(id = "rightClickMessage")
    public WebElement rightClickMessage; // Right Click message
    @FindBy(id = "dynamicClickMessage")
    public WebElement dynamicClickMessage; // Click button


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

    //------------------------------------------------------------------------------------------------------------------
    // BUTTONS

    // Actions performed on buttons
    // By entering true u will select action to perform

    public void clickOnButton(int numberOfClicks, boolean leftClick, WebElement button){

        Actions actions = new Actions(driver);

        switch (numberOfClicks) {

            case 1 -> {
                if (leftClick) button.click();
                else actions.contextClick(button).perform();
            }

            case 2 -> {
                if (leftClick) actions.doubleClick(button).perform();
                else {
                    actions.contextClick(button).perform();
                    actions.contextClick(button).perform();
                }
            }

            default -> System.err.println("Wrong number of clicks");
        }

    }
}
