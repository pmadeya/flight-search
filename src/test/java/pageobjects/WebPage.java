package pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class WebPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String mainWindow;

    public WebPage() {}

    public WebPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebPage(String page) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        mainWindow = driver.getWindowHandle();

        driver.get(page);
    }

    public WebDriver getDriver() {
        return driver;
    }
    public WebDriverWait getWait() {
        return wait;
    }

    public void closeAllWindows(String mainWindow, WebDriver driver) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window).close();
            }
        }
        driver.switchTo().window(mainWindow);
    }

    public void closePage() {
        driver.quit();
    }

}
