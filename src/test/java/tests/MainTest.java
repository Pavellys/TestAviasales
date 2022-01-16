package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends BaseTest {
    private static final String MAIN_URL = "https://aviasales.by";
    private static final String CITY_DEPARTURE = "Москва";
    private static final String CITY_ARRIVAL = "Санкт-Петербург";
    private static final int plusDay = 1;
    private static final int plusTwoDay = 2;

    @Test(groups = {"smokeTest"}, description = "Check form for search tickets on main page")
    public void checkSearchForm() {
        mainPage.openPage(MAIN_URL)
                .waitCityField()
                .enterFromWhichCity(CITY_DEPARTURE)
                .enterToWhichCity(CITY_ARRIVAL)
                .clickToDateField()
                .chooseDateOnCalendar(plusDay)
                .chooseDateOnCalendar(plusTwoDay)
                .clickToPassengersField()
                .addChildrenQuantity()
                .offCheckBox()
                .clickToButtonSearchTickets()
                .waitResultList();
        Assert.assertTrue(resultPage.checkCityForDeparture(CITY_DEPARTURE, CITY_ARRIVAL));
        Assert.assertTrue(resultPage.checkCityForArrival(CITY_ARRIVAL, CITY_DEPARTURE));
        Assert.assertTrue(resultPage.checkDateTicketInThereAndBack(mainPage.getCurrentDate(plusDay), mainPage.getCurrentDate(plusTwoDay)));
        Assert.assertTrue(resultPage.checkSortPrices());
    }
}