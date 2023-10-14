package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebTablePage extends BaseTest {

    public WebTablePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".ReactTable.-striped.-highlight")
    public WebElement employeeTable;

    @FindBy(id = "searchBox")
    public WebElement searchBox;

    @FindBy(id = "addNewRecordButton")
    public WebElement addButton;

    @FindBy(css = ".pattern-backgound.playgound-header")
    public WebElement pageTitlePage;

    @FindBy(css = "span[title='Delete']")
    public List<WebElement> listOfDeleteActionOptions;

    @FindBy(css = "span[title='Delete']")
    public WebElement deleteButton;

    @FindBy(className = "rt-tbody")
    public WebElement table;

    @FindBy(className = "rt-tr-group")
    public List<WebElement> rowData;

    @FindBy(className = "rt-td")
    public List<WebElement> recordData;

    @FindBy(id = "firstName")
    public WebElement firstNameTextBox;

    @FindBy(id = "lastName")
    public WebElement lastNameTextBox;

    @FindBy(id = "userEmail")
    public WebElement emailTextBox;

    @FindBy(id = "age")
    public WebElement ageTextBox;

    @FindBy(id = "salary")
    public WebElement salaryTextBox;

    @FindBy(id = "department")
    public WebElement departmentTextBox;

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(css = ".modal-title.h4")
    public WebElement pageTitleRegistration;


    // TODO ----------------------------------------------------------------------- Action
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

    public void clickOnSubmitButton(){
        submitButton.click();
    }

    public String textPageTitle(){
        return pageTitlePage.getText();
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
