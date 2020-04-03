package UI;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static com.codeborne.selenide.Selenide.*;

public class BaseUiTest {

    static WebDriver driver;

    @BeforeMethod
    protected void setUp() {
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        open();
    }

    @AfterMethod
    protected static void quitDriver(){
        driver.quit();
    }

}
