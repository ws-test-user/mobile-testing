package hellocucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class CalcOperationsSteps {

    private String APP = "https://github.com/dtopuzov/mobile-tests/raw/master/testapps/Calculator_2.0.apk";
    private String AVD_NAME = "Emulator-Api23-Default";

    private static AppiumDriver driver;
    private static WebDriverWait wait;

    @Before
    public void beforeScenario() throws MalformedURLException {
        if (driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("avd", AVD_NAME);
            capabilities.setCapability("app", APP);

            // Open the app.
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
            wait = new WebDriverWait(driver, 10);

            // Set longer sessions if debugger is attached
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300");
        }
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("^clean calculator$")
    public void clean_calculator() throws Exception {

    }

    @When("^I sum (\\d+) and (\\d+)$")
    public void i_sum_and(int arg1, int arg2) throws Exception {
        driver.findElement(By.id("net.ludeke.calculator:id/digit" + arg1)).click();
        driver.findElement(By.id("net.ludeke.calculator:id/plus")).click();
        driver.findElement(By.id("net.ludeke.calculator:id/digit" + arg2)).click();
        driver.findElement(By.id("net.ludeke.calculator:id/equal")).click();
    }

    @Then("^result should be (\\d+)$")
    public void result_should_be(int arg1) throws Exception {
        MobileElement resultElement = (MobileElement) driver.findElement(By.className("android.widget.EditText"));
        String result = resultElement.getAttribute("text").trim();
        Assert.assertEquals(result, String.valueOf(arg1), "Sum is wrong!");
    }

    @When("^multiply (\\d+) and (\\d+)$")
    public void multiply_and(int arg1, int arg2) throws Exception {

    }

}