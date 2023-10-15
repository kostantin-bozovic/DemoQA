package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sidebar extends BaseTest {

    public Sidebar(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "item-3")
    public WebElement webTable;


    public void goToWebTable(){
        webTable.click();
    }
}
