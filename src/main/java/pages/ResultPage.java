package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultPage extends MainPage {
    @FindBy(xpath = "//*[@class='product-list__item fade-enter-done']")
    List<WebElement> tickets;
    @FindBy (xpath = "//*[@class='ticket-desktop__body']//*[@class='segment-route__endpoint origin']//*[@class='segment-route__date']")
    List<WebElement> departureDates;
    @FindBy (xpath = "//*[@class='ticket-desktop__body']//*[@class='segment-route__endpoint destination']//*[@class='segment-route__date']")
    List<WebElement> arrivalDates;
    @FindBy(xpath = "//*[@class='ticket-desktop__body']//*[@class='segment-route__endpoint origin']//*[@class='segment-route__city']")
    List<WebElement> cityForDeparture;
    @FindBy(xpath = "//*[@class='ticket-desktop__body']//*[@class='segment-route__endpoint destination']//*[@class='segment-route__city']")
    List<WebElement> cityForArrival;
    @FindBy(xpath = "//*[@class='ticket-desktop']//*[@data-test-id='price']")
    List<WebElement> pricesOfTicketsList;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkCityForDeparture(String cityForDepartureInThere, String cityForDepartureBack) {
        List<WebElement> elements = cityForDeparture;
        for (int i = 0; i < elements.size();i=i+2) {
            boolean a = elements.get(i).getText().contains(cityForDepartureInThere);
            if(!a){
                return false;
            }
        }
        for (int i = 1; i < elements.size();i=i+2) {
            boolean a = elements.get(i).getText().contains(cityForDepartureBack);
            if(!a){
                return false;
            }
        }
        return true;
    }

    public boolean checkCityForArrival(String cityForArrivalInThere, String cityForArrivalBack) {
        List<WebElement> elements = cityForArrival;
        for (int i = 0; i < elements.size();i=i+2) {
            boolean a = elements.get(i).getText().contains(cityForArrivalInThere);
            if(!a){
                return false;
            }
        }
        for (int i = 1; i < elements.size();i=i+2) {
            boolean a = elements.get(i).getText().contains(cityForArrivalBack);
            if(!a){
                return false;
            }

        }
        return true;
    }

    public boolean checkDateTicketInThereAndBack(String dateForDepartureInThere, String dateForDepartureBack) {
        List<WebElement> elements = departureDates;
        for (int i = 0; i < elements.size();i=i+2) {
            String getDate = elements.get(i).getText().substring(0, 2);
            if(!getDate.equals(dateForDepartureInThere)){
                return false;
            }
        }
        for (int i = 1; i < elements.size();i=i+2) {
            String getDate = elements.get(i).getText().substring(0,2);
            if(!getDate.equals(dateForDepartureBack)){
                return false;
            }

        }
        return true;
    }

    public boolean checkSortPrices(){
        List<WebElement> prices = pricesOfTicketsList;
        List<String> strPrices = new ArrayList<>();
        for (WebElement price : prices) {
            String a = price.getText();
            strPrices.add(a);
        }
        List <String> forSortList = new ArrayList<>(strPrices);
        Collections.sort(forSortList);
        if(!strPrices.equals(forSortList)){
            return false;
        }
        return true;
    }

    public ResultPage waitResultList(){
        wait.until(ExpectedConditions.visibilityOfAllElements(tickets));
        return this;
    }
}