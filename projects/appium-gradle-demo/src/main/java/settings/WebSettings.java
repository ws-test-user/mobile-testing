package settings;

import java.util.Properties;

/**
 * Web specific settings.
 */
public class WebSettings {
    public String browser;
    public String baseURL;

    /**
     * Init web settings.
     *
     * @param properties Properties object.
     */
    public WebSettings(Properties properties) {
        this.browser = properties.getProperty("browserType", null);
        this.baseURL = properties.getProperty("baseUrl", null);
    }
}
