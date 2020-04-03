package UI;

import org.testng.annotations.Test;
import pages.GooglePage;
import pages.OpenPage;

import static org.testng.Assert.assertTrue;

public class CheckRateTest extends BaseUiTest {

    @Test
    public void checkRate() {
        String url = "https://www.open.ru/";
        GooglePage googleMainPage = new GooglePage().openPage();
        googleMainPage.search("Открытие");

        assertTrue(googleMainPage.checkThatResultHasUrl(url),
                "В результате поиска отсутсвует требуемый url");

        OpenPage openPage = googleMainPage.openUrlFromResult(url);
        assertTrue(openPage.checkBuyLessSellFor(1),
                "У первой валюты стоимость покупки выше продажи");
        assertTrue(openPage.checkBuyLessSellFor(2),
                "У второй валюты стоимость покупки выше продажи");

    }
}
