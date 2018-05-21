package google.pages;

import base.MobilePage;
import io.appium.java_client.AppiumDriver;
import settings.Settings;

public class HomePage extends MobilePage {
    public HomePage(Settings settings, AppiumDriver driver) {
        super(settings, driver);
    }
}
