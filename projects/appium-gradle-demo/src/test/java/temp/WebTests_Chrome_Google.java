package temp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebTests_Chrome_Google {

    AppiumDriver driver;

    // Manually start server:
    // appium --chromedriver-executable <some-path>/chromedriver/chromedriver-mac-2.34

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(AndroidMobileCapabilityType.AVD, "Emulator-Api27-Google");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
        this.driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @BeforeMethod
    public void beforeMethod() {
        this.driver.get("https://google.com/ncr");
    }

    @Test
    public void searchForAppium() {
        // Search for appium
        WebElement searchBox = this.driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("appium");
        searchBox.sendKeys(Keys.ENTER);

        // TouchAction require switch to NATIVE_APP context.
        // We will keep the original context (CHROMIUM) so we can return back.
        String originalContext = driver.getContext();
        driver.context("NATIVE_APP");

        // Swipe down (because http://appium.io/ is not the first result).
        new TouchAction(this.driver)
                .press(PointOption.point(240, 600))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(240, 200))
                .release()
                .perform();

        // Return to original context
        driver.context(originalContext);

        // Verify results
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.xpath(String.format("//a[@href='%s']", "http://appium.io/"))));
    }
}
