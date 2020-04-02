package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class GooglePage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@id=\"search\"]//a")
    private List<WebElement> urls_result;

    public GooglePage openPage() {
        open("https://www.google.com/");
        return this;
    }

    public GooglePage search(String text){
        SelenideElement a = $(searchInput);
        a.setValue(text).pressEnter();
        return new GooglePage();
    }

    public boolean checkThatResultHasUrl(String url) {
        if (takeElementByUrl(url) != null){
            return true;
        };
        return false;
    }

    public OpenPage openUrlFromResult (String url) {
        takeElementByUrl(url).click();
        return new OpenPage();
    }

    private SelenideElement takeElementByUrl(String url) {
        ElementsCollection links = $$(urls_result);
        for (int i = 0; i < links.size(); i++){
            String url_result = links.get(i).attr("href");
            if (url_result != null && url_result.equals(url)){
                return links.get(i);
            };
        }
        return null;
    }

}
