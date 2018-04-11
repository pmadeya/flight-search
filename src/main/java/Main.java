import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

    //slow down clicking
    //make function to close windows
    //wait until loading bar complete

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://www.orbitz.com/");
        //Get parent window
        String parentWindow = driver.getWindowHandle();
        //Click on flights
        driver.findElement(By.id("tab-flight-tab-hp")).click();


        //Input text in departure airport
        driver.findElement(By.cssSelector("input[id='flight-origin-hp-flight']")).sendKeys("YVR");
        //Choose box for autocomplete
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-value='Vancouver, BC, Canada (YVR-Vancouver Intl.)']"))).click();
        //driver.findElement(By.cssSelector("a[data-value='Vancouver, BC, Canada (YVR-Vancouver Intl.)']")).click();

        //Input text in arrival airport
        driver.findElement(By.cssSelector("input[id='flight-destination-hp-flight']")).sendKeys("SFO");
        //Autocomplete wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-value='San Francisco, CA (SFO-San Francisco Intl.)']"))).click();
        //driver.findElement(By.cssSelector("a[data-value='San Francisco, CA (SFO-San Francisco Intl.)']")).click();

        //Input date for depart
        driver.findElement(By.cssSelector("input[id='flight-departing-hp-flight']")).clear();
        driver.findElement(By.cssSelector("input[id='flight-departing-hp-flight']")).sendKeys("04/19/2018");

        //Input date for return
        driver.findElement(By.cssSelector("input[id='flight-returning-hp-flight']")).clear();
        driver.findElement(By.cssSelector("input[id='flight-returning-hp-flight']")).sendKeys("04/23/2018");

        //Search
        driver.findElement(By.cssSelector(".gcw-submit")).click();

        //Close all popups
        closeAllWindows(parentWindow, driver);

        //Get top 5 prices
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='progress-bar']")));
        getTopFivePrices(driver);

        //Change dates
        driver.findElement(By.cssSelector("#departure-date-1")).clear();
        driver.findElement(By.cssSelector("#departure-date-1")).sendKeys("04/20/2018");
        driver.findElement(By.cssSelector("#return-date-1")).clear();
        driver.findElement(By.cssSelector("#return-date-1")).sendKeys("04/24/2018");

        //Update dates submit
        driver.findElement(By.cssSelector("#flight-wizard-search-button")).click();

        //Close all popups
        closeAllWindows(parentWindow, driver);

        //Get prices and wait for popup
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='progress-bar']")));
        getTopFivePrices(driver);

        driver.quit();
    }

    public static void getTopFivePrices(WebDriver driver) {

        List<WebElement> flights = driver.findElements(By.cssSelector("#flightModuleList > li.flight-module"));
        System.out.println("Prices");
        System.out.println("******");
        for(int i = 0; i < 5; i++) {
            System.out.println(i + ": " + flights.get(i).findElement(By.cssSelector("span[data-test-id='listing-price-dollars']")).getText());
        }
    }

    public static void closeAllWindows(String mainWindow, WebDriver driver) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window).close();
            }
        }
        driver.switchTo().window(mainWindow);
    }
}
