package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebTablePage extends BaseTest {

    // Constructor for Web Table Class, public modifier
    public WebTablePage(){
        PageFactory.initElements(driver, this);
    }


    // POM Locators, covered: className, css, id...
    @FindBy(css = ".ReactTable.-striped.-highlight") // employee table
    public WebElement employeeTable;

    @FindBy(id = "searchBox") // search text box
    public WebElement searchBox;

    @FindBy(id = "addNewRecordButton") // add button
    public WebElement addButton;

    @FindBy(css = ".pattern-backgound.playgound-header") // page title
    public WebElement pageTitlePage;

    @FindBy(css = "span[title='Delete']")
    public List<WebElement> listOfDeleteActionOptions; // List of delete action button (used for finding number of employees)

    @FindBy(css = "span[title='Delete']") // delete button
    public WebElement deleteButton;

    @FindBy(className = "rt-tbody") // table body
    public WebElement table;

    @FindBy(className = "rt-tr-group") // list of rows
    public List<WebElement> rowData;

    @FindBy(className = "rt-td")
    public List<WebElement> recordData; // list of records

    @FindBy(id = "firstName")
    public WebElement firstNameTextBox; // first name text box

    @FindBy(id = "lastName")
    public WebElement lastNameTextBox; // last name text box

    @FindBy(id = "userEmail")
    public WebElement emailTextBox; // email text box

    @FindBy(id = "age")
    public WebElement ageTextBox; // age text box

    @FindBy(id = "salary")
    public WebElement salaryTextBox; // salary text box

    @FindBy(id = "department")
    public WebElement departmentTextBox; // department text box

    @FindBy(id = "submit")
    public WebElement submitButton; // submit button



    //-------------------------------------------------------------------------------------------------------------------
    // Registration form methods
    // First clear and then enter the values


    public void insertFirsName(String firstname){
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(firstname);
    }
    public void insertLastName(String lastname){
        lastNameTextBox.clear();
        lastNameTextBox.sendKeys(lastname);
    }
    public void insertEmail(String email){
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
    }
    public void insertAge(int age){
        ageTextBox.clear();
        ageTextBox.sendKeys(String.valueOf(age));
    }
    public void insertSalary(int salary){
        salaryTextBox.clear();
        salaryTextBox.sendKeys(String.valueOf(salary));
    }
    public void insertDepartment(String department){
        departmentTextBox.clear();
        departmentTextBox.sendKeys(department);
    }
    //-------------------------------------------------------------------------------------------------------------------
    //  Methods that perform action on registration form and table

    public void clickOnSubmitButton(){
        submitButton.click();
    }
    public String pageName(){
        return pageTitlePage.getText();
    }
    public void clickOnAddButton(){
        addButton.click();
    }
    public int rowNumber(){
        return listOfDeleteActionOptions.size();
    }
    public void clickOnDeleteButton(){
        deleteButton.click();
    }
    public void enterValueForSearch(String text){
        searchBox.clear();
        searchBox.sendKeys(text);
    }
}
