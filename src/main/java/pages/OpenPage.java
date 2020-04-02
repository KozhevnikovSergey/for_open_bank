package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class OpenPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, \"main-page-exchange__row\")]")
    List<WebElement> currencies;

    By prices = By.xpath(".//span[@class='main-page-exchange__rate']");


    public boolean checkBuyLessSellFor(int numberCurrencies){
        ElementsCollection prices_list = $$(currencies)
                .get(numberCurrencies-1)
                .$$(prices);

        float bay = Float.parseFloat(prices_list.get(0).getText().replace(",", "."));
        float sell = Float.parseFloat(prices_list.get(1).getText().replace(",", "."));

        if(bay<sell){
            return true;
        } else {
            return false;
        }
    }

}
