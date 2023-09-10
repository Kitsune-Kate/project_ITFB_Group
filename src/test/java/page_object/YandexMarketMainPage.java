package page_object;

import driver.DriverManager;
import io.qameta.allure.Step;
import java.time.Duration;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Action;

@Getter
@SuppressWarnings("UnusedReturnValue")
public class YandexMarketMainPage {
    Action action = new Action();

    @FindBy(xpath = "//span[text()='Каталог']")
    private WebElement catalogButton;

    @FindBy(xpath = "//a[@href='/catalog--lakomstva-dlia-koshek/62819/list?hid=15963668']")
    private WebElement goodiesForCatLocator;

    @FindBy(xpath = "//span[text()='Зоотовары']")
    private WebElement catalogPetProductsButton;

    @FindBy(css = "[data-auto='preloader']")
    private WebElement preloader;

    public YandexMarketMainPage() {
        PageFactory.initElements(DriverManager.webDriverChrome, this);
    }

    @Step("Нажать на 'Каталог'")
    public YandexMarketMainPage clickOnCatalog() {
        new Action().click(catalogButton);

        return this;
    }

    @Step("Навести курсор на раздел 'Зоотовары'")
    public YandexMarketMainPage hoverOnCategoryPetProducts() {
        new Action().hover(catalogPetProductsButton);

        return this;
    }

    @Step("Нажать на 'Лакомства' в блоке 'Для кошек'")
    public YandexMarketMainPage selectGoodiesInBlockForCat() {
        new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10))
            .until(ExpectedConditions.invisibilityOf(preloader));

        new Action().click(goodiesForCatLocator);

        return this;
    }

    @Step("")
    public YandexMarketMainPage waitPreloaderDisappear() {
        new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10))
            .until(ExpectedConditions.invisibilityOf(preloader));

        return this;
    }
}
