package temp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
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

public class WebTests_Safari_Google {

    AppiumDriver driver;

    // Manually start server:
    // appium --chromedriver-executable <some-path>/chromedriver/chromedriver-mac-2.34

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.3");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.SAFARI);
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

        WebElement searchButton = this.driver.findElement(By.xpath("//*[@aria-label='Google Search']"));
        searchButton.click();

        // Verify results
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.xpath("//a[contains(@href, 'appium.io')]")));
    }
}
