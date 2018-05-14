package selendroid.test;

import base.MobileTest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selendroid.pages.HomePage;

public class SmokeTests extends MobileTest {

    private HomePage home;

    @BeforeClass
    public void beforeTests() {
        home = new HomePage(client.getDriver());
    }

    @Test
    public void checkBoxTest() {
        boolean currentState = Boolean.valueOf(home
                .checkBox.getAttribute("checked"));
        Assert.assertTrue(currentState);

        home.checkBox.click();
        currentState = Boolean.valueOf(home
                .checkBox.getAttribute("checked"));
        Assert.assertFalse(currentState);
    }

    @Test
    public void handlePopWindows() {
        // Click the button to show popup windows
        home.popupWindowButton.click();

        // handle popup window

        // Can not locate popup window because of https://code.google.com/p/android/issues/detail?id=93268

        // Get window size and define point where to click
        Dimension size = client.getDriver().manage().window().getSize();
        int x = ((Double) (size.getWidth() * 0.5)).intValue();
        int y = ((Double) (size.getHeight() * 0.575)).intValue();

        // Generate and perform tap gesture
        new TouchAction(client.getDriver())
                .tap(PointOption.point(x, y))
                .perform();

        System.out.println("Popup window handled.");

        /**
         * Just a sample how to swipe.
         TouchAction swipe = new TouchAction(client.getDriver())
         .press(PointOption.point(250, 500))
         .waitAction(Duration.ofMillis(1000))
         .moveTo(PointOption.point(250, 100))
         .release()
         .perform();
         **/
    }

    @Test
    public void handleToastMessage() {
        home.toastButton.click();

        // Wait until toast element is present.
        WebDriverWait wait = new WebDriverWait(client.getDriver(), 10);
        By toastLocator = By.xpath("//*[@text='Hello selendroid toast!']");
        wait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));
    }
}
