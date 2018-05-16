package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import settings.Settings;
import utils.image.Image;
import utils.image.ImageComparisonResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.lang.System.getProperty;

/**
 * Base mobile page.
 */
public class MobilePage {

    protected Settings settings;
    protected AppiumDriver driver;

    /**
     * Init base mobile page.
     *
     * @param settings Settings object.
     * @param driver   AppiumDriver object.
     */
    public MobilePage(Settings settings, AppiumDriver driver) {
        this.driver = driver;
        this.settings = settings;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    /**
     * Wait until current screen matches expected image.
     *
     * @param screenName name of screen.
     * @throws IOException when fail to read/write images.
     */
    public void waitForScreen(String screenName) throws IOException {
        this.waitForScreen(screenName, 0.1, Duration.ofSeconds(10));
    }

    /**
     * Wait until current screen matches expected image.
     *
     * @param screenName       name of screen.
     * @param tolerancePercent diff tolerance percent as Double.
     * @throws IOException when fail to read/write images.
     */
    public void waitForScreen(String screenName, Double tolerancePercent) throws IOException {
        this.waitForScreen(screenName, tolerancePercent, Duration.ofSeconds(10));
    }

    /**
     * Wait until current screen matches expected image.
     *
     * @param screenName       name of screen.
     * @param tolerancePercent diff tolerance percent as Double.
     * @param duration         wait duration.
     * @throws IOException when fail to read/write images.
     */
    public void waitForScreen(String screenName, Double tolerancePercent, Duration duration) throws IOException {
        boolean match = false;
        long t = System.currentTimeMillis();
        long end = t + duration.toMillis();
        while (System.currentTimeMillis() < end) {
            if (this.compare(screenName, tolerancePercent) == true) {
                match = true;
                break;
            }
        }
        Assert.assertTrue(match, screenName + " does NOT look OK.");
    }

    /**
     * Compare actual screen with expected image.
     *
     * @param screenName       name of screen.
     * @param tolerancePercent diff tolerance percent as Double.
     * @return true if screen match expected image.
     * @throws IOException when fail to read/write images.
     */
    public boolean compare(String screenName, Double tolerancePercent) throws IOException {
        boolean match = true;

        // Get actual image
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        BufferedImage actualImage = ImageIO.read(screenshot);

        // Get expected image
        String expectedImagePath = getProperty("user.dir") + File.separator +
                "src" + File.separator + "test" + File.separator +
                "resources" + File.separator + "images" + File.separator +
                this.settings.app.appName.toLowerCase() + File.separator +
                this.settings.deviceName.toLowerCase() + File.separator +
                screenName + ".png";
        File expectedImageFile = new File(expectedImagePath);
        if (expectedImageFile.exists()) {
            BufferedImage expectedImage = ImageIO.read(expectedImageFile);

            // Compare images
            ImageComparisonResult result = Image.compare(actualImage, expectedImage, 10, 25);

            // Analyse image comparison result
            if (result.diffPercent > tolerancePercent) {
                String error = String.format("%s does NOT look OK. Diff: %,.2f", screenName, result.diffPercent);
                System.out.println(error);

                String baseImageName = this.settings.screenshotsPath + File.separator + screenName;
                String actualImageLogPath = baseImageName + "_actual.png";
                Image.save(result.actualImage, actualImageLogPath);

                String expectedImageLogPath = baseImageName + "_expected.png";
                Image.save(result.expectedImage, expectedImageLogPath);

                String diffImageLogPath = baseImageName + "_diff.png";
                Image.save(result.diffImage, diffImageLogPath);

                match = false;
            } else {
                System.out.println(screenName + " looks OK.");
            }
        } else {
            System.out.println("Can not find image at " + expectedImageFile.getAbsolutePath());
            System.out.println("Save actual image as expected.");
            File parentFile = expectedImageFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            Image.save(actualImage, expectedImageFile.getAbsolutePath());
        }
        return match;
    }
}
