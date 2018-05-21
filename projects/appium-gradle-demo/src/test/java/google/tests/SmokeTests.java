package google.tests;

import base.MobileTest;
import enums.PlatformType;
import google.pages.HomePage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SmokeTests extends MobileTest {

    HomePage google;

    @BeforeClass
    public void beforeClass() {
        this.google = new HomePage(settings, client.getDriver());
    }

    @BeforeMethod
    public void beforeMethod() {
        client.getDriver().get(settings.web.baseURL);
    }

    @Test
    public void searchForAppium() {
        this.google.searchFor("appium");

        if (settings.platform == PlatformType.ANDROID) {
            // TouchAction require switch to NATIVE_APP context.
            // We will keep the original context (CHROMIUM) so we can return back.
            String originalContext = client.getDriver().getContext();
            client.getDriver().context("NATIVE_APP");

            // Swipe down (because http://appium.io/ is not the first result).
            new TouchAction(client.getDriver())
                    .press(PointOption.point(240, 600))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(240, 200))
                    .release()
                    .perform();

            // Return to original context
            client.getDriver().context(originalContext);
        }

        this.google.verifyLinkExist("appium.io");
    }
}
