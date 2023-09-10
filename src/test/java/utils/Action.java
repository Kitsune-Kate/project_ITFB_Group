package utils;

import driver.DriverManager;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Action {

  public void click(WebElement ele) {
    new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOf(ele))
        .click();
  }

  // TODO: первый элемент находит, второй не находит (error: stale element reference)
  public void click(List<WebElement> webElements, int index) {
    Waits.untilEachElementIsVisible(webElements);
    webElements.get(index).click();
  }

  public void sendKeys(WebElement ele, String text) {
    new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOf(ele))
        .sendKeys(text);
  }

  public void hover(WebElement ele) {
    Waits.untilVisibilityOfElement(ele);

    Actions action = new Actions(DriverManager.webDriverChrome);
    action.moveToElement(ele)
        .perform();
  }

  public String getText(WebElement ele) {

    Waits.untilVisibilityOfElement(ele);
    return ele.getText();
  }

//  private WebElement findElement(WebElement ele) {
//    return DriverManager.webDriverChrome.findElement(By.xpath(xpath));
//  }
}
