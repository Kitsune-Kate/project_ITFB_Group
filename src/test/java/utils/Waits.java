package utils;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class Waits {

    /**
     * Ожидание проверки, что каждый элемент в списке виден
     *
     * @param webElements Локатор списка элементов
     */
    public static void untilEachElementIsVisible(List<WebElement> webElements) {
        for (WebElement e : webElements) {
            e.isDisplayed();
        }

    }

    /**
     * Ожидание для проверки, что все элементы, присутствующие на веб-странице, которые соответствуют локатору, видны. Видимость означает, что элементы
     * не только отображаются, но также имеют высоту и ширину, превышающие 0.
     *
     * @param webElement Локатор списка эелементов Явное ожидание — это циклы, добавленные в код, которые опрашивают приложение на предмет определенного
     *                   условия, чтобы оценить его как истинное, прежде чем оно выйдет из цикла и перейдет к следующей команде в коде. Если условие не
     *                   выполняется до достижения назначенного значения тайм-аута, код выдаст ошибку тайм-аута.
     *                   <p>
     *                   Ожидание видимости элемента wait.until(visibilityOf(element)); .visibilityOf содержится в классе ExpectedConditions element --
     *                   Элемент, который уже найден, осталось лишь дождаться его появления на странице
     * @return
     */
    public static WebElement untilVisibilityOfElement(WebElement element) {
       return new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void untilElementNotVisible(WebElement element) {
        new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(element));
    }

    public static void untilNumberOfWindowsOrTabToBe(int count) {
        new WebDriverWait(DriverManager.webDriverChrome, Duration.ofSeconds(10)).until(numberOfWindowsToBe(count));
    }

}
