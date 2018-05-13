package appium;

import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import settings.MobileSettings;
import utils.OS;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Appium client abstraction.
 */
public class Client {

    private AppiumDriver driver;
    private AppiumDriverLocalService service;
    private MobileSettings settings;

    /**
     * Instantiate new appium client.
     *
     * @param service  Appium server service.
     * @param settings Mobile settings.
     */
    public Client(AppiumDriverLocalService service, MobileSettings settings) {
        this.service = service;
        this.settings = settings;
    }

    /**
     * Get instance of current driver.
     *
     * @return AppiumDriver instance.
     */
    public AppiumDriver getDriver() {
        return this.driver;
    }

    /**
     * Start appium client session.
     */
    public void start() {
        DesiredCapabilities cap = new DesiredCapabilities();

        // Set capabilities based on settings.
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, this.settings.platform);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, this.settings.deviceName);
        cap.setCapability(MobileCapabilityType.APP, this.settings.app);

        // Set ANDROID specific capabilities.
        if (this.settings.platform == PlatformType.ANDROID) {
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            cap.setCapability(AndroidMobileCapabilityType.AVD, this.settings.avd);
        }

        // Set IOS specific capabilities.
        if (this.settings.platform == PlatformType.IOS) {
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        }

        // Allow longer sessions if debugger is attached.
        if (OS.isDebuggerAttached()) {
            // This will make debugging possible for 10min after session start.
            cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Duration.ofMinutes(30).getSeconds());
        }

        // Initialize driver.
        System.out.println("Starting Appium client...");
        this.driver = new AppiumDriver(this.service.getUrl(), cap);
        System.out.println("Appium driver session initialized.");

        // Set default implicit wait.
        this.driver.manage().timeouts().implicitlyWait(this.settings.findTimeout, TimeUnit.SECONDS);
        System.out.println("Set default implicit wait of " + String.valueOf(this.settings.findTimeout) + " seconds.");
    }

    /**
     * Stop appium client session.
     */
    public void stop() {
        try {
            this.driver.quit();
            System.out.println("Appium driver stopped.");
        } catch (Exception e) {
            System.out.println("Appium driver already stopped.");
        }
    }
}
