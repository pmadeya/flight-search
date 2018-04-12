package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightSearchResults extends WebPage {

    private WebDriver driver;
    private WebDriverWait wait;

    By loadingBar = By.cssSelector(".progress-bar > div");
    By departureDateChangeElem = By.cssSelector("#departure-date-1");
    By arrivalDateChangeElem = By.cssSelector("#return-date-1");

    By ticketPriceElement = By.cssSelector("span[data-test-id='listing-price-dollars']");
    By flightDetailsElement = By.cssSelector("#flightModuleList > li.flight-module");
    By searchBtnElement = By.cssSelector("#flight-wizard-search-button");


    public FlightSearchResults(WebDriver driver, WebDriverWait wait, String mainWindow) {
        this.driver = driver;
        this.wait = wait;
        this.mainWindow = mainWindow;
    }

    public boolean isSearchComplete() {
        try {
            wait.until(ExpectedConditions.attributeContains(loadingBar, "style", "width: 100%;"));
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    public void changeDates(String departureDate, String arrivalDate) {
        driver.findElement(departureDateChangeElem).clear();
        driver.findElement(departureDateChangeElem).sendKeys(departureDate);
        driver.findElement(arrivalDateChangeElem).clear();
        driver.findElement(arrivalDateChangeElem).sendKeys(arrivalDate);

        driver.findElement(searchBtnElement).click();
        closeAllWindows(mainWindow, driver);
    }

    public void getTopFivePrices() throws RuntimeException {
        if (isSearchComplete()) {
            List<WebElement> flights = driver.findElements(flightDetailsElement);
            System.out.println("Prices: " + driver.findElement(departureDateChangeElem).getAttribute("value") + " to " + driver.findElement(arrivalDateChangeElem).getAttribute("value"));
            System.out.println("******");
            for (int i = 0; i < 5; i++) {
                System.out.println(i+1 + ": " + flights.get(i).findElement(ticketPriceElement).getText());
            }
        } else {
            throw new RuntimeException("Page not loading");
        }
    }


}
