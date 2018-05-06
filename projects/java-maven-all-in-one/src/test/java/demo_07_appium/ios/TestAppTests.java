package demo_07_appium.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestAppTests {
    private IOSDriver driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        String APP = System.getProperty("user.dir") + "/testapps/TestApp.app.zip";
        String SIM_NAME = "iPhone 7";
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
        this.driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        // Set longer sessions if debugger is attached
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300");
    }

    @Test
    public void testApp() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
