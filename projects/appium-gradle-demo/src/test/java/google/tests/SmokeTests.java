package google.tests;

import base.MobileTest;
import enums.PlatformType;
import google.pages.HomePage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class SmokeTests extends MobileTest {

    HomePage google;

    @BeforeClass
    public void beforeClass() {
        this.google = new HomePage(settings, client.getDriver());
    }

    @BeforeMethod
    public void beforeMethod() {
        client.getDriver().get(settings.web.baseURL);
    }

    @Test
    public void searchForAppium() {
        this.google.searchFor("appium");

        if (settings.platform == PlatformType.ANDROID) {
            // TouchAction require switch to NATIVE_APP context.
            // We will keep the original context (CHROMIUM) so we can return back.
            String originalContext = client.getDriver().getContext();
            client.getDriver().context("NATIVE_APP");

            // Swipe down (because http://appium.io/ is not the first result).
            new TouchAction(client.getDriver())
                    .press(PointOption.point(240, 600))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(240, 200))
                    .release()
                    .perform();

            // Return to original context
            client.getDriver().context(originalContext);
        }

        this.google.verifyLinkExist("appium.io");
    }

    @Test
    public void searchForTemperature() {
        // Get temperature via web
        this.google.searchFor("temperature");
        WebElement temp = client
                .getDriver()
                .findElement(By.id("wob_tm"));
        int webTemp = Integer.valueOf(temp.getText());
        System.out.println("Web Temperature: " + webTemp);

        // Get temperature via web service
        int serviceTemp = given().
                param("units", "metric").
                param("q", "Sofia,bg").
                param("appid", "94dbcaa25f947f5c7abfc0faa6f3dcca").
                when().
                get("http://api.openweathermap.org/data/2.5/weather").
                then()
                .statusCode(200).
                        extract().
                        path("main.temp");
        System.out.println("Service Temperature: " + serviceTemp);

        Assert.assertTrue(Math.abs(webTemp - serviceTemp) < 2,
                "Temperature in web and backend do not match!");
    }
}
