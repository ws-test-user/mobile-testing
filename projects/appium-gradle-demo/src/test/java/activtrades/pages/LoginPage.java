package activtrades.pages;

import base.MobilePage;
import io.appium.java_client.AppiumDriver;
import settings.Settings;

/**
 * ActivTrades login page.
 */
public class LoginPage extends MobilePage {
    /**
     * Init ActivTrades login page.
     *
     * @param settings Settings object.
     * @param driver   AppiumDriver object.
     */
    public LoginPage(Settings settings, AppiumDriver driver) {
        super(settings, driver);
    }
}
