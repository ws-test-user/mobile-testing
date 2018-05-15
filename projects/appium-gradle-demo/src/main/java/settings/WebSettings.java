package settings;

import org.openqa.selenium.remote.BrowserType;

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
        this.browser = properties.getProperty("browser", BrowserType.CHROME);
        this.baseURL = properties.getProperty("baseURL", null);
    }
}
