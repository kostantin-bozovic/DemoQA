package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPage extends BaseTest {

    // Registration Form Data
    private String FIRSTNAME;
    private String LASTNAME;
    private String EMAIL;
    private int AGE;
    private int SALARY; // site doesn't allow double or float values, only int !
    private String DEPARTMENT;


    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
    }

    /** TESTING TABLE PAGE */

    @Test(priority = 10)
    public void verifyIfTablePageElementsArePresent(){

        Assert.assertEquals(webTablePage.pageName(), "Web Tables");
        Assert.assertTrue(webTablePage.addButton.isDisplayed());
        Assert.assertTrue(webTablePage.employeeTable.isDisplayed());
    }

    @Test(priority = 20)
    public void verifyIfUserCanAddNewEmployee(){

        int rowNumberBefore = webTablePage.rowNumber();

        webTablePage.clickOnAddButton();
        //Assert.assertEquals(registrationFormTablePage.textPageTitle(), "Registration Form");

        fillRegistrationForm("TablePageData");

        Assert.assertTrue(webTablePage.submitButton.isDisplayed());
        webTablePage.clickOnSubmitButton();

        int rowNumberAfterAddedEmployee = webTablePage.rowNumber();

        Assert.assertEquals(rowNumberAfterAddedEmployee,rowNumberBefore + 1);
    }

    @Test(priority = 30)
    public void verifyIfAddedEmployeeIsInsideTable(){
        addEmployee();
        Assert.assertTrue(findIfEmployeeIsInsideTheTable(FIRSTNAME,LASTNAME,EMAIL,AGE,SALARY,DEPARTMENT));
    }

    @Test(priority = 40)
    public void verifyIfUserCanChangeEmployeeData(){

        addEmployee();

        // Napravljena metoda kojom se pronalazi zadati zaposleni u tabeli i klikce na njegovo edit dugme
        editEmployee(FIRSTNAME,LASTNAME,EMAIL,AGE,SALARY,DEPARTMENT);

        fillRegistrationForm("ChangedTableData");
        webTablePage.clickOnSubmitButton();

        Assert.assertTrue(findIfEmployeeIsInsideTheTable(FIRSTNAME,LASTNAME,EMAIL,AGE,SALARY,DEPARTMENT));
    }

    @Test(priority = 45)
    public void verifyResultsWithInvalidSearch(){

        String text = "AladinICarobnaLampa";

        webTablePage.enterValueForSearch(text);
        Assert.assertFalse(searchTest(text));
    }

    @Test(priority = 46)
    public void verifyResultsWithValidSearch(){

        addEmployee();
        webTablePage.enterValueForSearch(LASTNAME);

        Assert.assertTrue(searchTest(LASTNAME));
    }

    @Test(priority = 47)
    public void verifyResultsUsingNumbersForSearch(){

        String number = "4";

        webTablePage.enterValueForSearch(number);

        Assert.assertTrue(searchTest(number));
    }

    @Test(priority = 50)
    public void verifyIfUserCanDeleteEmployee(){

        addEmployee();
        Assert.assertTrue(findIfEmployeeIsInsideTheTable(FIRSTNAME,LASTNAME,EMAIL,AGE,SALARY,DEPARTMENT));

        removeEmployeeFromTheTable(FIRSTNAME,LASTNAME,EMAIL,AGE,SALARY,DEPARTMENT);

        Assert.assertFalse(findIfEmployeeIsInsideTheTable(FIRSTNAME,LASTNAME,EMAIL,AGE,SALARY,DEPARTMENT));
    }

    // For complete testing this option, u need to wait ~ 10,3 sec.
    @Test(priority = 60)
    public void verifyIfUserCanDeleteAllEmployees(){

        deleteAllEmployees();
        Assert.assertEquals(webTablePage.rowNumber(), 0);
    }

    // TABLE PAGE
    public boolean findIfEmployeeIsInsideTheTable(String firstname, String lastname, String email, int age, int salary, String department){

        boolean flag = false;

        for (int i = 0; i < webTablePage.recordData.size(); i= i + 7) {

            if (    firstname.equals(webTablePage.recordData.get(i).getText()) &&
                    lastname.equals(webTablePage.recordData.get(i+1).getText()) &&
                    String.valueOf(age).equals(webTablePage.recordData.get(i+2).getText()) &&
                    email.equals(webTablePage.recordData.get(i+3).getText()) &&
                    String.valueOf(salary).equals(webTablePage.recordData.get(i+4).getText()) &&
                    department.equals(webTablePage.recordData.get(i+5).getText()))
            {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public void removeEmployeeFromTheTable(String firstname, String lastname, String email, int age, int salary, String department){

        int index = 0;

        for (int i = 0; i < webTablePage.recordData.size(); i= i + 7) {

            if (    firstname.equals(webTablePage.recordData.get(i).getText()) &&
                    lastname.equals(webTablePage.recordData.get(i+1).getText()) &&
                    String.valueOf(age).equals(webTablePage.recordData.get(i+2).getText()) &&
                    email.equals(webTablePage.recordData.get(i+3).getText()) &&
                    String.valueOf(salary).equals(webTablePage.recordData.get(i+4).getText()) &&
                    department.equals(webTablePage.recordData.get(i+5).getText()))
            {
                index += 1;
                break;
            }
            else index += 1;
        }

        WebElement deleteEmployeeButton = driver.findElement(By.id("delete-record-"+index));
        deleteEmployeeButton.click();
    }
    public void editEmployee(String firstname, String lastname, String email, int age, int salary, String department){

        int index = 0;

        for (int i = 0; i < webTablePage.recordData.size(); i= i + 7) {

            if (    firstname.equals(webTablePage.recordData.get(i).getText()) &&
                    lastname.equals(webTablePage.recordData.get(i+1).getText()) &&
                    String.valueOf(age).equals(webTablePage.recordData.get(i+2).getText()) &&
                    email.equals(webTablePage.recordData.get(i+3).getText()) &&
                    String.valueOf(salary).equals(webTablePage.recordData.get(i+4).getText()) &&
                    department.equals(webTablePage.recordData.get(i+5).getText()))
            {
                index += 1;
                break;
            }
            else index += 1;
        }

        WebElement deleteEmployeeButton = driver.findElement(By.id("edit-record-"+index));
        deleteEmployeeButton.click();
    }
    public boolean searchTest(String text){

        boolean flag = false;

        for (int i = 0; i < webTablePage.recordData.size(); i= i + 7) {

            if (    compareSearch(text, webTablePage.recordData.get(i).getText()) ||
                    compareSearch(text, webTablePage.recordData.get(i+1).getText()) ||
                    compareSearch(text, webTablePage.recordData.get(i+2).getText()) ||
                    compareSearch(text, webTablePage.recordData.get(i+3).getText()) ||
                    compareSearch(text, webTablePage.recordData.get(i+4).getText()) ||
                    compareSearch(text, webTablePage.recordData.get(i+5).getText()))
            {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public void fillRegistrationForm(String sheetName){

        FIRSTNAME = excelReader.getStringData(sheetName,0,1);
        LASTNAME = excelReader.getStringData(sheetName,1,1);
        EMAIL = excelReader.getStringData(sheetName,2,1);
        AGE = excelReader.getIntegerData(sheetName,3,1);
        SALARY = excelReader.getIntegerData(sheetName,4,1);
        DEPARTMENT = excelReader.getStringData(sheetName,5,1);


        webTablePage.insertFirsName(FIRSTNAME);
        webTablePage.insertLastName(LASTNAME);
        webTablePage.insertEmail(EMAIL);
        webTablePage.insertAge(AGE);
        webTablePage.insertSalary(SALARY);
        webTablePage.insertDepartment(DEPARTMENT);
    }
    public void addEmployee(){
        webTablePage.clickOnAddButton();
        fillRegistrationForm("TablePageData");
        webTablePage.clickOnSubmitButton();
    }
    public void deleteAllEmployees() {
        while (!webTablePage.recordData.get(0).getText().isBlank()) {
            webTablePage.clickOnDeleteButton();
        }
    }
    public static boolean compareSearch(String insertedText, String comperedText) {

        insertedText = insertedText.toLowerCase();
        comperedText = comperedText.toLowerCase();

        // Check all characters in insertedText inside comperedText
        for (char c : insertedText.toCharArray()) {
            if (Character.isLetter(c) && comperedText.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
