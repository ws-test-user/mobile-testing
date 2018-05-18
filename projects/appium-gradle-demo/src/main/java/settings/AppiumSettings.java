package settings;

import java.util.Properties;

/**
 * Appium settings.
 */
public class AppiumSettings {
    public String automationName;

    /**
     * Init appium settings.
     *
     * @param properties Properties object.
     */
    public AppiumSettings(Properties properties) {
        this.automationName = properties.getProperty("automationName", null);
    }
}
