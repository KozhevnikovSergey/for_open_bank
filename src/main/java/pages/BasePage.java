package pages;

import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BasePage {

    protected BasePage() {
        PageFactory.initElements(getWebDriver(), this);
    }

}
