package scripts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.FlightSearchPage;
import pageobjects.FlightSearchResults;
import pageobjects.WebPage;


public class FlightPricesTest extends WebPage {

    public final String homepage = "http://www.orbitz.com";
    private FlightSearchPage flightSearchPage;
    private FlightSearchResults resultsPage;

    @Before
    public void setUp() {
        flightSearchPage = new FlightSearchPage(homepage);
        resultsPage = new FlightSearchResults(driver, wait, mainWindow);
    }

    @Test
    public void testGetTopPrices() {
        flightSearchPage.clickFlightsTab();
        flightSearchPage.chooseAirports("YVR", "SFO");
        flightSearchPage.chooseDates("04/19/2018", "04/23/2018");

        resultsPage = flightSearchPage.submit();
        resultsPage.getTopFivePrices();

        resultsPage.changeDates("04/20/2018", "04/24/2018");
        resultsPage.getTopFivePrices();
        resultsPage.changeDates("04/21/2018", "04/25/2018");
        resultsPage.getTopFivePrices();
    }

    @After
    public void tearDown() {
        flightSearchPage.closePage();
    }

}
