package settings;

import java.util.Properties;

/**
 * Android specific settings.
 */
public class AndroidSettings {

    public String avdName;
    public String avdOptions;
    public String defaultActivity;

    /**
     * Init android specific settings.
     *
     * @param properties Properties object.
     */
    public AndroidSettings(Properties properties) {
        this.avdName = properties.getProperty("avdName", null);
        this.avdOptions = properties.getProperty("avdOptions", null);
        this.defaultActivity = properties.getProperty("defaultActivity", null);
    }
}
