package page_object;


import driver.DriverManager;
import dto.Product;
import io.qameta.allure.Step;
import java.time.Duration;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Action;
import utils.Waits;

@Getter
public class CatalogPage {
    Action action = new Action();

    @FindBy(xpath = "//label[text()='Цена, ₽ от']/..//input")
    private WebElement minPriceLocator;

    @FindBy(xpath = "//label[text()='Цена, ₽ до']/..//input")
    private WebElement maxPriceLocator;
    
    @FindBy(xpath = "//span[text()='Показать всё']")
    private WebElement showAllBrands;
    
    @FindBy(css = "div[data-auto='compare-button']")
    private WebElement buttonAddToCompare;
    
    @FindBy(xpath = "//span[text()='Курьером']")
    private WebElement includingDeliveryByCourier;
    
    @FindBy(xpath = "//span[text()='Whiskas']")
    private WebElement whiskasManufacturer;

    @FindBy(xpath = "//span[text()='Менямс']")
    private WebElement mnyamsManufacturer;
    
    @FindBy(xpath = "(//h3[@data-auto='snippet-title-header']//a)[1]")
    private WebElement firstProductInList;

    @FindBy(xpath = "(//h3[@data-auto='snippet-title-header']//a)[1]")
    private WebElement firstProductNameInList;

    @FindBy(xpath = "(//h3[@data-auto='snippet-title-header']//a)[2]")
    private WebElement secondProductInList;

    @FindBy(xpath = "(//h3[@data-auto='snippet-title-header']//a)[2]")
    private WebElement secondProductNameInList;

    @FindBy(xpath = "//a[text()='Сравнить']")
    private WebElement buttonCompare;

    @FindBy(xpath = "//a[contains(text(), 'Whiskas')]")
    private WebElement whiskasLinkFromCompare;

    @FindBy(xpath = "//a[contains(text(), 'Whiskas')]//..//div[@aria-label='Удалить']")
    private WebElement whiskasDeleteButton;

    @FindBy(xpath = "(//span[@data-autotest-value])[1]")
    private WebElement firstProductPriceFromCompare;

    @FindBy(xpath = "//a[contains(text(), 'Mnyams')]")
    private WebElement mnyamsLinkFromCompare;

    @FindBy(xpath = "(//span[@data-autotest-value])[2]")
    private WebElement secondProductPriceFromCompare;

    @FindBy(xpath = "//button[text()='Удалить список']")
    private WebElement buttonDeleteList;

    @FindBy(xpath = "//h2[text()='Сравнивать пока нечего']")
    private WebElement defaultMessageWhenCompareEmpty;

    @FindBy(css = "[data-auto='preloader']")
    private WebElement preloader;

    public CatalogPage() {
        PageFactory.initElements(DriverManager.webDriverChrome, this);
    }

    private Product firstProductFromList = new Product();
    private Product secondProductFromList = new Product();
    private Product firstProductFromCompare = new Product();
    private Product secondProductFromCompare = new Product();

    @Step("Установить фильтр «Цена от» {minPrice}.")
    public CatalogPage setFilterMinPrice(String minPrice) {
        action.sendKeys(minPriceLocator, minPrice);

        return this;
    }


    @Step("Установить фильтр «Цена до» {maxPrice}.")
    public CatalogPage setFilterMaxPrice(String maxPrice) {
        action.sendKeys(maxPriceLocator, maxPrice);

        return this;
    }

    @Step("Установить фильтр способ доставки: «С учетом доставки курьером»")
    public CatalogPage setFilterIncludingDeliveryByCourier() {
        action.click(includingDeliveryByCourier);

        return this;
    }

    @Step("Нажать на кнопку 'Показать всё'")
    public CatalogPage clickOnShowAllBrands() {
        action.click(showAllBrands);

        return this;
    }


    @Step("Установить фильтр производителя: Whiskas")
    public CatalogPage selectManufacturerWhiskas() {
        action.click(whiskasManufacturer);

        return this;
    }


    @Step("Нажать на первый товар в списке")
    public CatalogPage clickOnFirstProductInList() {
        action.click(firstProductInList);

        return this;
    }


    @Step("Вернуться на предыдущую страницу ")
    public CatalogPage returnPreviousPage() {
        DriverManager.switchOnPreviousTab();

        return this;
    }

    @Step("Перейти на второй товар в списке")
    public CatalogPage clickOnSecondProductInList() {
        action.click(buttonCompare);

        return this;
    }

    @Step("Нажать 'Добавить к сравнению'")
    public CatalogPage clickOnAddToCompareButton() {
        action.click(buttonAddToCompare);

        return this;
    }

    @Step("Выбрать производителя 'Whiskas'")
    public CatalogPage unselectManufacturerWhiskas() {
        action.click(whiskasManufacturer);

        return this;
    }

    @Step("Выбрать производителя 'Мнямс'")
    public CatalogPage selectManufacturerMnyams() {
        action.click(mnyamsManufacturer);

        return this;
    }

    @Step("Нажать кнопку сравнить")
    public CatalogPage clickCompareButton() {
        action.click(buttonCompare);

        return this;
    }

    @Step("Запомнить имя первого товара")
    public CatalogPage memorizeFirstProductName() {
        firstProductFromList.setName(action.getText(firstProductNameInList));

        return this;
    }

    @Step("Запомнить имя второго товара")
    public CatalogPage memorizeSecondProductName() {
        secondProductFromList.setName(action.getText(secondProductNameInList));

        return this;
    }

    @Step("Проверить на добавленные продукты")
    public CatalogPage checkAddedProductsNames() {
        firstProductFromCompare.setName(action.getText(whiskasLinkFromCompare));
        secondProductFromCompare.setName(action.getText(mnyamsLinkFromCompare));

        Assertions.assertThat(firstProductFromCompare.getName()).isEqualTo(firstProductFromList.getName());
        Assertions.assertThat(secondProductFromCompare.getName()).isEqualTo(secondProductFromList.getName());
        return this;
    }

    @Step("Проверить сумму добавленных продуктов")
    public CatalogPage checkSumAddedProductsPrice() {
        int priceFirst = Integer.parseInt(action.getText(firstProductPriceFromCompare));
        int priceSecond = Integer.parseInt(action.getText(secondProductPriceFromCompare));
        int sum = priceFirst + priceSecond;

        Assertions.assertThat(sum).isLessThan(300);

        return this;
    }

    @Step("Удалить производителя Вискас")
    public CatalogPage deleteManufacturerWhiskas() {
        action.click(whiskasDeleteButton);


        return this;
    }

    @Step("Навести курсор на карточку 'Whiskas'")
    public CatalogPage hoverOnWhiskasCard() {
        action.hover(whiskasLinkFromCompare);

        return this;
    }

    @Step("Проверить что товар 'Вискас' не отображается в списке")
    public CatalogPage checkWhiskasProductIsNotDisplayed() {
        Waits.untilElementNotVisible(whiskasLinkFromCompare);

        return this;
    }

    @Step("Нажать на 'Удалить список' ")
    public CatalogPage clickOnDeleteList() {
        action.click(buttonDeleteList);

        return this;
    }

    @Step("Проверить что продукты неа отображаются")
    public CatalogPage checkProductsInCompareIsNotDisplayed() {
        Assertions.assertThat(action.getText(defaultMessageWhenCompareEmpty))
            .isEqualTo("Сравнивать пока нечего");

        return this;
    }

    @Step("Подождать пока исчезнет Loader")
    public CatalogPage waitPreloaderDisappear() {
        new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10))
            .until(ExpectedConditions.invisibilityOf(preloader));

        return this;
    }
}
