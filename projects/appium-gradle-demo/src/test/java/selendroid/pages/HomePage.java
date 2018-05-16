package selendroid.pages;

import base.MobilePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import settings.Settings;

/**
 * Selendroid application home page.
 */
public class HomePage extends MobilePage {

    @AndroidFindBy(id = "io.selendroid.testapp:id/input_adds_check_box")
    @iOSFindBy(id = "")
    public MobileElement checkBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/showPopupWindowButton")
    @iOSFindBy(id = "")
    public MobileElement popupWindowButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/showToastButton")
    @iOSFindBy(id = "")
    public MobileElement toastButton;

    /**
     * Init home page.
     *
     * @param settings Settings object.
     * @param driver   AppiumDriver object.
     */
    public HomePage(Settings settings, AppiumDriver driver) {
        super(settings, driver);
    }
}
