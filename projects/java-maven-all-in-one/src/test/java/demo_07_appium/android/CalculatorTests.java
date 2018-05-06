package demo_07_appium.android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTests {

    private AndroidDriver driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        String APP = "https://github.com/dtopuzov/mobile-tests/raw/master/testapps/Calculator_2.0.apk";
        String AVD_NAME = "Emulator-Api26-Google";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("avd", AVD_NAME);
        capabilities.setCapability("app", APP);

        // Open the app.
        this.driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        // Set longer sessions if debugger is attached
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300");
    }

    @Test
    public void sumOfTwoAndTwoShouldBeFour() {
        new TouchAction(driver)
                .press(PointOption.point(10,10)).moveTo(100, 100).release().perform();
        this.driver.findElement(By.id("net.ludeke.calculator:id/digit2")).click();
        this.driver.findElement(By.id("net.ludeke.calculator:id/plus")).click();
        this.driver.findElement(By.id("net.ludeke.calculator:id/digit2")).click();
        this.driver.findElement(By.id("net.ludeke.calculator:id/equal")).click();
        MobileElement resultElement = (MobileElement) this.driver.findElement(By.className("android.widget.EditText"));
        String result = resultElement.getAttribute("text").trim();
        Assert.assertEquals(result, "4", "Sum of 2 and 2 is wrong!");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
