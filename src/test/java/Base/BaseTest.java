package Base;

import Pages.WebTablePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static final String URL ="https://demoqa.com/webtables";
    public static WebDriver driver;
    public WebDriverWait wait;
    public ExcelReader excelReader;
    public WebTablePage webTablePage;


    @BeforeClass
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        webTablePage = new WebTablePage();


        excelReader = new ExcelReader("src/test/java/TestData.xlsx");
    }

    @AfterClass
    public void tearDown() {
        //driver.manage().deleteAllCookies();
        driver.quit();
    }
}
