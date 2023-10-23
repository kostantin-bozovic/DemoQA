package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElementsTest extends BaseTest {

    public String firstName, lastName, fullName, email, currAddress, permAddress, sheetName = "TextBox";


    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        goToElements();
    }

    // TEXT BOX TEST

    @Test(priority = 1)
    public void textBoxElementsArePresent(){

        sidebar.goToTextBox();

        // Test page name

        String expectedPageName = "Text Box";
        Assert.assertEquals(elementsPage.getPageTitleText(), expectedPageName);

        // Test URL

        String expectedURL = "https://demoqa.com/text-box";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        // "Full Name" text box and label are displayed

        Assert.assertTrue(elementsPage.fullNameTextBox.isDisplayed());
        Assert.assertTrue(elementsPage.labelIsDisplayed("userName"));

        // "Email" text box and label are displayed

        Assert.assertTrue(elementsPage.emailTextBox.isDisplayed());
        Assert.assertTrue(elementsPage.labelIsDisplayed("userEmail"));

        // Current & Permanent address and there labels are displayed

        Assert.assertTrue(elementsPage.currentAddressTextBox.isDisplayed());
        Assert.assertTrue(elementsPage.labelIsDisplayed("currentAddress"));

        Assert.assertTrue(elementsPage.permanentAddressTextBox.isDisplayed());
        Assert.assertTrue(elementsPage.labelIsDisplayed("permanentAddress"));


        // Submit button id displayed
        Assert.assertTrue(elementsPage.submitButton.isDisplayed());
    }
    @Test(priority = 2)
    public void userCanFillAndSubmitTexBox(){

        sidebar.goToTextBox();
        fillTextBox();

        // submit filled form
        scrollToElement(elementsPage.submitButton);
        elementsPage.clickSubmitButton();
    }
    @Test(priority = 3)
    public void submittedFormIsCorrect(){

        sidebar.goToTextBox();
        fillTextBox();

        // submit filled form
        scrollToElement(elementsPage.submitButton);
        elementsPage.clickSubmitButton();

        // submitted form is visible
        Assert.assertTrue(elementsPage.submittedForm.isDisplayed());

        // test submitted form
        String expectedForm = submittedForm();
        String actualForm = elementsPage.submittedFormText();

        Assert.assertEquals(actualForm, expectedForm);
    }


//    @Test // For testing
//    public void testDemo(){
//
//    }

    public void fillTextBox(){

        // extract from TestData.xlsx elements and fill text box form

        firstName = excelReader.getStringData(sheetName,0,1);
        lastName = excelReader.getStringData(sheetName,1,1);
        email = excelReader.getStringData(sheetName,2,1);
        currAddress = excelReader.getStringData(sheetName,3,1);
        permAddress = excelReader.getStringData(sheetName,4,1);

        // insert values from "TextBox" sheet

        fullName = firstName + " " + lastName;
        elementsPage.insertFullName(fullName);
        elementsPage.insertEmail(email);
        elementsPage.insertCurrentAddress(currAddress);
        elementsPage.insertPermanentAddress(permAddress);
    }
    public String submittedForm(){
        return "Name:"+ fullName +"\n" +
                "Email:"+ email +"\n" +
                "Current Address :"+ currAddress + "\n" +
                "Permananet Address :"+ permAddress;
    }
}
