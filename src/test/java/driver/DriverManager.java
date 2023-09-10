package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Waits;

@AllArgsConstructor
@Setter
@Getter
public class DriverManager {

  public static WebDriver webDriverChrome = new ChromeDriver(getChromeOptions());

  @Step("Открыть браузер и развернуть на весь экран.")
  public static void initChrome() {
    WebDriverManager.chromedriver().setup();

    setDefaultChromeOptions();
  }

  private static ChromeOptions getChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-blink-features=AutomationControlled");
    DesiredCapabilities chrome = new DesiredCapabilities();
    chrome.setCapability(ChromeOptions.CAPABILITY, options);
    return options;
  }

  private static void setDefaultChromeOptions() {
    webDriverChrome.manage().window().maximize();
    /**
     * Тайм-аут используется для указания времени, в течение которого драйвер должен ждать при поиске элемента, если он отсутствует сразу.
     */
    webDriverChrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    /**
     * Устанавливает время ожидания полной загрузки страницы, прежде чем выдать ошибку. Если время ожидания отрицательное, загрузка страниц может быть неопределенной.
     */
    webDriverChrome.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));


  }

  public static void switchOnPreviousTab() {
    String originalWindow = DriverManager.webDriverChrome.getWindowHandle();

    Waits.untilNumberOfWindowsOrTabToBe(2);

    for (String windowHandle : DriverManager.webDriverChrome.getWindowHandles()) {
      if (originalWindow.contentEquals(windowHandle)) {
        DriverManager.webDriverChrome.switchTo().window(windowHandle);

        break;
      }
    }
  }

  public static void quitCurrentDriver() {
    webDriverChrome.quit();
  }
}
