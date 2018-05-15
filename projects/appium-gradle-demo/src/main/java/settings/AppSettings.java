package settings;

import java.util.Properties;

/**
 * General mobile app settings.
 */
public class AppSettings {
    public String appName;
    public String appId;
    public String appPath;

    /**
     * Init app settings.
     *
     * @param properties Properties object.
     */
    public AppSettings(Properties properties) {
        this.appName = properties.getProperty("appName", null);
        this.appPath = properties.getProperty("appId", null);
        this.appPath = properties.getProperty("appPath", null);
    }
}
