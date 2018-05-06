package demo_05_selenium.tests;

import demo_04_restassured.OpenWeatherAPI;
import demo_05_selenium.base.BaseTest;
import demo_05_selenium.pages.GoogleHome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for google search.
 */
public class GoogleTests extends BaseTest {

    private GoogleHome google;

    @BeforeClass
    public void beforeGoogleTests() {
        this.google = new GoogleHome(driver);
    }

    @Test
    public void searchTest() {
        this.google
                .navigateTo()
                .searchFor("Appium")
                .verifyLinkExist("http://appium.io/");
    }

    @Test
    public void temperature() {
        this.google
                .navigateTo()
                .searchFor("sofia temperature");

        // Get temperature displayed on web page
        WebElement temp = driver.findElement(By.id("wob_tm"));
        String tempFromWeb = temp.getText();

        // Get temperature in Sofia via rest api
        int tempFromApiInt = OpenWeatherAPI.getTemperature("Sofia,bg");

        // Verify temperature (assert Api and UI return same temperature).
        double tempFromWebD = Double.valueOf(tempFromWeb);
        double tempFromApiD = Double.valueOf(String.valueOf(tempFromApiInt));
        System.out.println("Temperature via Web Site: " + tempFromWebD);
        System.out.println("Temperature via Rest API: " + tempFromApiD);
        Assert.assertTrue(Math.abs(tempFromWebD - tempFromApiD) <= 2.0, "Temperature is not correct!");
    }
}
