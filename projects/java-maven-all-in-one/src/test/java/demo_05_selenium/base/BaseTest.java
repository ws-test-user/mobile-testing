package demo_05_selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Base Test class.
 * UI web tests extend this class.
 */
public class BaseTest {

    protected static WebDriver driver;

    /**
     * Start chrome browser.
     */
    @BeforeClass
    public static void setUp() {
        String driverPath = System.getProperty("user.dir") + File.separator + "libs" +
                File.separator + "2.35" + File.separator + "chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    /**
     * Close chrome browser.
     */
    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
