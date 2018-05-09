package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
import settings.MobileSettings;

import java.time.Duration;

/**
 * Abstraction of mobile app.
 */
public class MobileApp {

    private AppiumDriver driver;
    private MobileSettings settings;

    /**
     * Initialize mobile app.
     *
     * @param settings MobileSettings object.
     * @param driver   AppiumDriver object.
     */
    public MobileApp(MobileSettings settings, AppiumDriver driver) {
        this.settings = settings;
        this.driver = driver;
    }

    /**
     * Get current driver.
     *
     * @return AppiumDriver object.
     */
    public AppiumDriver getDriver() {
        return this.driver;
    }

    /**
     * Get current settings.
     *
     * @return MobileSettings object.
     */
    public MobileSettings getSettings() {
        return this.settings;
    }

    /**
     * Run current app in background.
     *
     * @param duration Duration.
     * @return Self reference.
     */
    public MobileApp runInBackground(Duration duration) {
        this.driver.runAppInBackground(duration);
        System.out.println("Run current app in background.");
        return this;
    }

    /**
     * Rotate current app.
     *
     * @param orientation ScreenOrientation enum value.
     * @return Self reference.
     */
    public MobileApp rotate(ScreenOrientation orientation) {
        this.driver.rotate(orientation);
        System.out.println("Rotate in " + orientation);
        return this;
    }
}
