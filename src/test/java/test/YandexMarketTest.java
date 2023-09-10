package test;

import org.junit.jupiter.api.Test;


public class YandexMarketTest extends BaseTest {

  @Test
  void yandexMarketTest() {
    yandexMarketMainPage
        .clickOnCatalog()
        .hoverOnCategoryPetProducts()
        .waitPreloaderDisappear()
        .selectGoodiesInBlockForCat();
    catalogPage.setFilterMinPrice("50")
        .setFilterMaxPrice("150")
        .setFilterIncludingDeliveryByCourier()
        .clickOnShowAllBrands()
        .selectManufacturerWhiskas()
        .waitPreloaderDisappear()
        .memorizeFirstProductName()
        .clickOnFirstProductInList()
        .clickOnAddToCompareButton()
        .returnPreviousPage()
        .unselectManufacturerWhiskas()
        .selectManufacturerMnyams()
        .memorizeSecondProductName()
        .clickOnSecondProductInList()
        .clickOnAddToCompareButton()
        .clickCompareButton()
        .checkAddedProductsNames()
        .checkSumAddedProductsPrice()
        .hoverOnWhiskasCard()
        .deleteManufacturerWhiskas()
        .checkWhiskasProductIsNotDisplayed()
        .clickOnDeleteList()
        .checkProductsInCompareIsNotDisplayed();
  }

}