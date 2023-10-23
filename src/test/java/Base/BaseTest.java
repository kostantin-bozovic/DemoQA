package Base;

import Pages.ElementsPage;
import Pages.Sidebar;
import Pages.WebTablePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BaseTest {

    public static final String URL ="https://demoqa.com/";
    public static WebDriver driver;
    public WebDriverWait wait;
    public ExcelReader excelReader;
    public WebTablePage webTablePage;
    public Sidebar sidebar;
    public ElementsPage elementsPage;

    @BeforeClass
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        webTablePage = new WebTablePage();
        sidebar = new Sidebar();
        elementsPage = new ElementsPage();

        excelReader = new ExcelReader("src/test/java/TestData.xlsx");
    }

    @AfterClass
    public void tearDown() {
        //driver.manage().deleteAllCookies();
        //driver.quit();
    }


    // Open Cards from site, used for navigation

    public void openCard(String cardName){
        List<WebElement> card = driver.findElements(By.className("card-body"));
        for (int i = 0; i < card.size(); i++) {
            if (card.get(i).getText().equals(cardName)){
                scrollToElement(card.get(i));
                card.get(i).click();
                break;
            }
        }
    }

    // Scroll to given element

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Navigate to the ELEMENTS page

    public void goToElements(){
        openCard("Elements");
        Assert.assertEquals(driver.getCurrentUrl(), URL + "elements");
    }

    // Navigate to the FORMS page

    public void goToForms(){
        openCard("Forms");
        Assert.assertEquals(driver.getCurrentUrl(), URL + "forms");
    }
}
