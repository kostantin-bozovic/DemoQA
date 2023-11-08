package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;



public class ElementsTest extends BaseTest {

    public String firstName, lastName, fullName, email, currAddress, permAddress, sheetName = "TextBox";


    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        goToElements();
    }

    //----------------------------------------------------------------
    // TEXT BOX

    @Test(priority = 10)
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
    @Test(priority = 20)
    public void userCanFillAndSubmitTexBox(){

        sidebar.goToTextBox();
        fillTextBox();

        // submit filled form
        scrollToElement(elementsPage.submitButton);
        elementsPage.clickSubmitButton();
    }
    @Test(priority = 30)
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

    //----------------------------------------------------------------
    // CHECK BOX

    @Test(priority = 40)
    public void expendAllCheckboxesButton(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();

        elementsPage.collapseAll();
        elementsPage.expendAll();

        // test if all checkbox elements are expended and visible
        expectedNumber = 17;
        actualNumber = elementsPage.uncheckedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }
    @Test(priority = 50)
    public void collapseAllCheckboxesButton(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();

        elementsPage.expendAll();
        elementsPage.collapseAll();

        // test if all checkbox elements are expended and visible, 1 main
        expectedNumber = 1;
        actualNumber = elementsPage.uncheckedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }
    @Test(priority = 60)
    public void userCanExtendManuallyCheckboxes(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();

        while (true){
            if (elementsPage.uncheckedCheckbox.size() == 17) break;

            // scroll to the element on page
            scrollToElement(elementsPage.expendSymbol);
            elementsPage.expendSymbol.click();
        }

        // test if all checkbox elements are expended and visible
        expectedNumber = 17;
        actualNumber = elementsPage.uncheckedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }
    @Test(priority = 70)
    public void userCanCollapseManuallyCheckboxes(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();

        elementsPage.expendAll();

        while (true){
            if (elementsPage.uncheckedCheckbox.size() == 1) break;

            // scroll to the element on page
            scrollToElement(elementsPage.collapseSymbol);
            elementsPage.collapseSymbol.click();
        }

        // test if all checkbox elements are expended and visible
        expectedNumber = 1;
        actualNumber = elementsPage.uncheckedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }
    @Test(priority = 80)
    public void userCanSelectAllCheckboxes(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();
        elementsPage.clickOnUnchecked(0);

        elementsPage.expendAll();

        // test if all checkbox elements are visible
        expectedNumber = 17;
        actualNumber = elementsPage.checkedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }
    @Test(priority = 90)
    public void userCanUnselectAllCheckboxes(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();
        elementsPage.expendAll();

        elementsPage.clickOnUnchecked(0);
        elementsPage.checkedCheckbox.get(0).click();

        // test if all checkbox elements are visible
        expectedNumber = 17;
        actualNumber = elementsPage.uncheckedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }
    @Test(priority = 100)
    public void userCanCheckRandomCheckbox(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();

        checkboxSelector("Angular");
        checkboxSelector("React");

        // test if all checkbox elements are expended and visible
        expectedNumber = 12; // 2 checkboxes + 3 parents

        actualNumber = elementsPage.uncheckedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }
    @Test(priority = 110)
    public void userCanUncheckRandomCheckbox(){

        int expectedNumber, actualNumber;

        sidebar.goToCheckBox();

        // add
        checkboxSelector("Angular");
        checkboxSelector("React");

        // remove
        checkboxSelector("React");

        // test if all checkbox elements are expended and visible
        expectedNumber = 13; // 1 checkboxes + 3 parents

        actualNumber = elementsPage.uncheckedCheckbox.size();

        Assert.assertEquals(actualNumber, expectedNumber);
    }


    //----------------------------------------------------------------
    // RADIO BUTTON

    @Test(priority = 120)
    public void radioButtonElementsArePresent() {

        String expectedPageName, expectedURL, expectedMessage;
        sidebar.goToRadioButton();

        // Test page name

        expectedPageName = "Radio Button";
        Assert.assertEquals(elementsPage.getPageTitleText(), expectedPageName);

        // Test URL

        expectedURL = "https://demoqa.com/radio-button";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        // MESSAGE

        expectedMessage = "Do you like the site?";
        Assert.assertEquals(elementsPage.radioButtonMessage.getText(), expectedMessage);
    }
    @Test(priority = 125)
    public void radioButtonsAreEnabled(){

        sidebar.goToRadioButton();

        // RADIO BUTTONS
        Assert.assertTrue(elementsPage.yesRadioButton.isEnabled()); // YES BUTTON
        Assert.assertFalse(elementsPage.noRadioButton.isEnabled()); // NO BUTTON
        Assert.assertTrue(elementsPage.impressiveRadioButton.isEnabled()); // IMPRESSIVE BUTTON
    }
    @Test(priority = 130)
    public void userCanSelectRadioButtons(){

        sidebar.goToRadioButton(); // FIXED

        scrollToElement(elementsPage.pageTitle);

        Actions actions = new Actions(driver);

        if (!elementsPage.yesRadioButton.isSelected()) actions.moveToElement(elementsPage.yesRadioButton).click().build().perform();

        Assert.assertTrue(elementsPage.yesRadioButton.isSelected()); // Yes is selected
        Assert.assertFalse(elementsPage.impressiveRadioButton.isSelected()); // Impressive unselected

        // User can select "Impressive" radio button
        if (!elementsPage.impressiveRadioButton.isSelected()) actions.moveToElement(elementsPage.impressiveRadioButton).click().build().perform();

        Assert.assertFalse(elementsPage.yesRadioButton.isSelected()); // Yes is unselected
        Assert.assertTrue(elementsPage.impressiveRadioButton.isSelected()); // Impressive selected

        // User cannot select "no" button
        Assert.assertFalse(elementsPage.noRadioButton.isEnabled());
    }

    //----------------------------------------------------------------
    // Buttons
    @Test(priority = 140)
    public void buttonsPageElementsArePresent(){

        String actualTitle, expectedURL, actualURL;

        sidebar.goToButtons();
        scrollToElement(elementsPage.pageTitle);

        // URL
        actualURL = driver.getCurrentUrl();
        expectedURL = "https://demoqa.com/buttons";
        Assert.assertEquals(actualURL, expectedURL);

        // Page name
        actualTitle = elementsPage.getPageTitleText();
        Assert.assertEquals(actualTitle, "Buttons");

        // Double click is visible
        Assert.assertTrue(elementsPage.doubleClickButton.isDisplayed());

        // Right click button is visible
        Assert.assertTrue(elementsPage.rightClickButton.isDisplayed());

        // Button "Click Me" is visible
        Assert.assertTrue(elementsPage.clickMeButton.isDisplayed());
    }
    @Test(priority = 145)
    public void testingEt(){

    }


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

    //--------------------------------------------------------------------------------------------------------------------
    // Made a method to pass through checkboxes, collect two group of checkboxes,
    // one is parent, second is child.
    // This methode can contain next elements: Name, Checkbox, Children if they exist
    // User inserts name of checkbox to be checked or unchecked.

    public void checkboxSelector(String checkbox) {

        List<WebElement> parentsList, childrenList;
        boolean flag = false;

        elementsPage.expendAll(); // we want to expend all

        // user send name of checkbox, and choose to check "true" or uncheck "false"
        // List of parents
        parentsList = driver.findElements(By.cssSelector("li.rct-node.rct-node-parent"));
        childrenList = driver.findElements(By.cssSelector("li.rct-node.rct-node-leaf"));

        // Iterate through elements of parents and then through children elements
        for (WebElement listItem : parentsList) {

            WebElement spanTextElement, titleElement, checkboxIcon;
            String checkboxTitle;

            // Find <span class="rct-text"> element inside each <li class="css select">
            // Find <span class="rct-title"> element inside <label> element inside <span class="rct-text">
            // Get the text inside <span class="rct-title">

            spanTextElement = listItem.findElement(By.cssSelector("span.rct-text"));
            titleElement = spanTextElement.findElement(By.cssSelector("label > span.rct-title"));

            checkboxTitle = titleElement.getText();

            // Find checkbox element of given element
            checkboxIcon = spanTextElement.findElement(By.cssSelector("label > span.rct-checkbox"));
            scrollToElement(checkboxIcon);

            if (checkboxTitle.equals(checkbox)) {

                checkboxIcon.click();
                flag = true;

            } else {

                for (WebElement child : childrenList) {

                    WebElement spanTextElementCh, titleElementCh, checkboxIconCh;
                    String checkboxTitleCh;

                    spanTextElementCh = child.findElement(By.cssSelector("span.rct-text"));
                    titleElementCh = spanTextElementCh.findElement(By.cssSelector("label > span.rct-title"));

                    checkboxTitleCh = titleElementCh.getText();

                    // Find checkbox element of given element
                    checkboxIconCh = spanTextElementCh.findElement(By.cssSelector("label > span.rct-checkbox"));
                    scrollToElement(checkboxIconCh);

                    if (checkboxTitleCh.equals(checkbox)) {
                        flag = true;
                        checkboxIconCh.click();
                    }
                    if (flag) break;
                }
            }
            if (flag) break;
        }

    }
}
