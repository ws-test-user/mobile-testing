package demo_07_appium.web;

import demo_04_restassured.OpenWeatherAPI;
import demo_05_selenium.pages.GoogleHome;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Mobile web tests with Google.
 * <p>
 * Notes:
 * Should run Appium server with custom chrome driver.
 * appium --chromedriver-executable <path-to-project>/libs/2.31/chromedriver
 */
public class GoogleTests {

    private AppiumDriver driver;
    private GoogleHome google;

    @BeforeClass
    public void beforeGoogleTests() {
        this.google = new GoogleHome(driver);
    }


    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        String AVD_NAME = "Emulator-Api26-Google";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, AVD_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(AndroidMobileCapabilityType.AVD, AVD_NAME);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.BROWSER_VERSION, "58.0.3029");

        // Open the app.
        this.driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        // Set longer sessions if debugger is attached
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300");
    }

    @Test
    public void googleSearch() {
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

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
