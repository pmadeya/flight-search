package pageobjects;

import org.openqa.selenium.By;

public class HomePage extends WebPage {

    By flightTabElem = By.cssSelector("tab-flight-tab-hp");

    public HomePage() {
        super();
    }

    public void clickFlightsTab() {
        driver.findElement(flightTabElem).click();
    }


}
