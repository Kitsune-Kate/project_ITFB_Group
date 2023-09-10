package test;

import driver.DriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import page_object.CatalogPage;
import page_object.YandexMarketMainPage;

public class BaseTest {

    public YandexMarketMainPage yandexMarketMainPage = new YandexMarketMainPage();
    public CatalogPage catalogPage = new CatalogPage();

    @BeforeAll
    static void beforeAll() {
        DriverManager.initChrome();
    }

    @BeforeEach
    void setUp() {
        openPage();
    }

    @AfterEach
    void tearDown() {
        DriverManager.quitCurrentDriver();
    }

    @Step("Зайти на https://market.yandex.ru")
    public void openPage() {
        DriverManager.webDriverChrome.get("https://market.yandex.ru");
    }
}
