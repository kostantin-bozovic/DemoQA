package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Sidebar extends BaseTest {

    // Constructor for Scrollbar Class, public modifier
    public Sidebar(){
        PageFactory.initElements(driver,this);
    }


    // Collect all Sidebar elements and store them inside the List of web elements
    @FindBy(className = "text")
    public List<WebElement> sidebarButtons;

    //------------------------------------------------------------------------------------------------------------------
    //  Navigate to the given Sidebar element

    public void clickOnSidebarButton(String button){
        for (int i = 0; i < sidebarButtons.size(); i++) {

            if (sidebarButtons.get(i).getText().equals(button)){

                scrollToElement(sidebarButtons.get(i));
                sidebarButtons.get(i).click();
                break;
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    // Elements inside Sidebar


    public void goToWebTable(){
        clickOnSidebarButton("Web Tables");
    } // WEB TABLE
    public void goToTextBox() { clickOnSidebarButton("Text Box");} // TEXT BOX
    public void goToCheckBox() { clickOnSidebarButton("Check Box");} // CHECK BOX
    public void goToRadioButton(){clickOnSidebarButton("Radio Button");} // RADIO BUTTON
}
