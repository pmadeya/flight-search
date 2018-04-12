package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightSearchPage extends WebPage {

    private By departureCityInputElem = By.cssSelector("input[id='flight-origin-hp-flight']");
    private By arrivalCityInputElem = By.cssSelector("input[id='flight-destination-hp-flight']");
    private By departureAutocompleteElem = By.cssSelector("a[data-value='Vancouver, BC, Canada (YVR-Vancouver Intl.)']");
    private By arrivalAutocompleteElem = By.cssSelector("a[data-value='San Francisco, CA (SFO-San Francisco Intl.)']");

    private By departureDateInputElem = By.cssSelector("input[id='flight-departing-hp-flight']");
    private By arrivalDateInputElem = By.cssSelector("input[id='flight-returning-hp-flight']");

    private By submitBtnElem = By.cssSelector(".gcw-submit");

    private By flightTabElem = By.id("tab-flight-tab-hp");

    public FlightSearchPage(String page) {
        super(page);
    }

    public void chooseAirports(String departureAirport, String arrivalAirport) {
        driver.findElement(departureCityInputElem).clear();
        driver.findElement(departureCityInputElem).sendKeys(departureAirport);
        wait.until(ExpectedConditions.visibilityOfElementLocated(departureAutocompleteElem)).click();

        driver.findElement(arrivalCityInputElem).clear();
        driver.findElement(arrivalCityInputElem).sendKeys(arrivalAirport);
        wait.until(ExpectedConditions.visibilityOfElementLocated(arrivalAutocompleteElem)).click();
    }

    public void chooseDates(String departureDate, String returnDate) {
        //Input date for depart
        driver.findElement(departureDateInputElem).clear();
        driver.findElement(departureDateInputElem).sendKeys(departureDate);

        //Input date for return
        driver.findElement(arrivalDateInputElem).clear();
        driver.findElement(arrivalDateInputElem).sendKeys(returnDate);
    }

    public void clickFlightsTab() {
        driver.findElement(flightTabElem).click();
    }

    public FlightSearchResults submit() {
        driver.findElement(submitBtnElem).click();

        closeAllWindows(mainWindow, driver);
        return new FlightSearchResults(driver, wait, mainWindow);
    }


}
