package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.MainPage;
import pages.ResultPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    MainPage mainPage;
    ResultPage resultPage;

    @BeforeMethod(groups = "init")
    public void initTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        initPage(driver);
    }

    @AfterMethod(alwaysRun = true, groups = "init")
    public void endTest(){
        driver.quit();
    }

    public void initPage(WebDriver driver){
        mainPage = new MainPage(driver);
        resultPage = new ResultPage(driver);
    }
}