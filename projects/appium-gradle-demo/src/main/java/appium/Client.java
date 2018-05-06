package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import settings.MobileSettings;

import java.util.concurrent.TimeUnit;

public class Client {

    private AppiumDriver driver;
    private AppiumDriverLocalService service;
    private MobileSettings settings;

    public Client(AppiumDriverLocalService service, MobileSettings settings) {
        this.service = service;
        this.settings = settings;
    }

    public AppiumDriver getDriver() {
        return this.driver;
    }

    public void start() {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        cap.setCapability(AndroidMobileCapabilityType.AVD, this.settings.avd);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, this.settings.deviceName);
        cap.setCapability(MobileCapabilityType.APP, this.settings.app);

        this.driver = new AppiumDriver(this.service.getUrl(), cap);
        System.out.println("Appium driver initialized.");
        this.driver.manage().timeouts().implicitlyWait(this.settings.findTimeout, TimeUnit.SECONDS);
        System.out.println("Set default implicit wait of " + String.valueOf(this.settings.findTimeout) + " seconds.");
    }

    public void stop() {
        try {
            this.driver.quit();
            System.out.println("Appium driver stopped.");
        } catch (Exception e) {
            System.out.println("Appium driver already stopped.");
        }
    }
}
