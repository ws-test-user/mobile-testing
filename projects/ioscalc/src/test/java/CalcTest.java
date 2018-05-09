import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalcTest {

    private AppiumDriver driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        String APP = System.getProperty("user.dir") + "/testapps/ios-calculator.zip";
        String SIM_NAME = "iPhone 6";
        String SIM_VERSION = "11.3";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, SIM_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, SIM_VERSION);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        // If NO_RESET is false it will create new iOS Simulator each time.
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability("app", APP);

        // Open the app.
        this.driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        // Set longer sessions if debugger is attached
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300");
    }

    @Test
    public void sumTest(){
        MobileElement el1 = (MobileElement) driver.findElement(By.id("1"));
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("+");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElement(By.id("2"));
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("=");
        el4.click();

        MobileElement result = (MobileElement) driver.findElement(By.className("XCUIElementTypeStaticText"));
        Assert.assertEquals(result.getText().trim(), "4");
    }
}
