package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.LocalDateTime;

public class MainPage extends BasePage{
    private static final String CHOOSE_DATE_ON_WINDOW = "//*[contains(@class,'calendar-day__date')";
    private static final String FROM_CITY_FIELD = "//*[@value='Минск']";
    @FindBy(id = "origin")
    WebElement fromField;
    @FindBy(id = "destination")
    WebElement toField;
    @FindBy(xpath = "//*[@placeholder='Когда']")
    WebElement dateField;
    @FindBy(xpath = "//*[@class='additional-fields --avia']")
    WebElement passengersField;
    @FindBy(xpath = "//*[@data-test-id='passengers-children-field']//*[@class='additional-fields__passenger-control --increment']")
    WebElement childrenQuantity;
    @FindBy(xpath = "//*[@data-test-id='form-submit']")
    WebElement buttonSearchTickets;
    @FindBy(className = "of_input_checkbox__label")
    WebElement checkBoxBooking;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage openPage(String url){
        driver.get(url);
        return this;
    }

    public MainPage waitCityField(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FROM_CITY_FIELD)));
        return this;
    }

    public MainPage enterFromWhichCity(String fromCity){
        fromField.sendKeys(fromCity);
        String value = fromField.getAttribute("value");
        if(!value.equals(fromCity)) {
            fromField.sendKeys("");
            fromField.sendKeys(fromCity);
        }
        return this;
    }

    public MainPage enterToWhichCity(String toCity){
        toField.sendKeys(toCity);
        return this;
    }

    public MainPage clickToDateField(){
        actions.moveToElement(dateField).click().build().perform();
        return this;
    }

    public String getCurrentDate(int plusDays){
        String a = LocalDateTime.now().plusDays(plusDays).toString();
        return a.substring(8,10);
    }

    public MainPage clickToPassengersField(){
        actions.moveToElement(passengersField).click().build().perform();
        return this;
    }

    public MainPage addChildrenQuantity(){
        childrenQuantity.click();
        return this;
    }

    public ResultPage clickToButtonSearchTickets(){
        buttonSearchTickets.click();
        return new ResultPage(driver);
    }

    public MainPage chooseDateOnCalendar(int plusDays){
        wait.ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    d.findElement(By.xpath(String.format(CHOOSE_DATE_ON_WINDOW+" and contains(text(), '%s')]", getCurrentDate(plusDays)))).click();
                    return true;
                });
        return this;
    }

    public MainPage offCheckBox(){
        checkBoxBooking.click();
        return this;
    }
}