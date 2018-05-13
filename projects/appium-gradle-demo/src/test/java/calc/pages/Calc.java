package calc.pages;

import base.MobileApp;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import settings.MobileSettings;

import java.util.List;

/**
 * Calculator app abstraction.
 * Can also look at this class as page object for calculator home page.
 */
public class Calc extends MobileApp {

    /**
     * Init calculator object (calc app home page).
     *
     * @param settings mobile settings.
     * @param driver   appium driver.
     */
    public Calc(MobileSettings settings, AppiumDriver driver) {
        super(settings, driver);
    }

    /**
     * Click number on the calculator.
     *
     * @param digit Digit enum value.
     */
    public void clickNumber(Digit digit) {
        String id;
        if (this.getSettings().platform == PlatformType.ANDROID) {
            id = "net.ludeke.calculator:id/digit" + digit;
        } else {
            id = digit.toString();
        }
        MobileElement num = (MobileElement) this.getDriver().findElementById(id);
        num.click();
        System.out.println("Click digit \"" + digit + "\" button.");
    }

    /**
     * Perform operation.
     *
     * @param operation Operation enum value.
     */
    public void performOperation(Operation operation) {
        String id;
        if (this.getSettings().platform == PlatformType.ANDROID) {
            id = String.format("net.ludeke.calculator:id/%s", operation);
        } else {
            if (operation == Operation.PLUS) {
                id = "+";
            } else if (operation == Operation.MINUS) {
                id = "-";
            } else if (operation == Operation.MULTIPLY) {
                id = "*";
            } else if (operation == Operation.DIVIDE) {
                id = "/";
            } else {
                id = "=";
            }
        }
        MobileElement op = (MobileElement) this.getDriver().findElement(By.id(id));
        op.click();
        System.out.println("Click " + operation.name() + " button.");
    }

    /**
     * Clean calculator.
     */
    public void clean() {
        if (this.getSettings().platform == PlatformType.ANDROID) {
            List buttons = this.getDriver()
                    .findElements(By.id("net.ludeke.calculator:id/clear"));
            if (buttons.size() > 0) {
                ((WebElement) buttons.get(0)).click();
            } else {
                WebElement button = this.getDriver().findElementById("net.ludeke.calculator:id/del");
                while (!this.getResult().equalsIgnoreCase("")) {
                    button.click();
                }
            }
        } else {
            this.getDriver().findElement(By.id("CLR")).click();
        }
        System.out.println("Clean the calculator.");
    }

    /**
     * Get current result.
     *
     * @return Current result as String (trimmed).
     */
    public String getResult() {
        By locator;
        if (this.getSettings().platform == PlatformType.ANDROID) {
            locator = By.className("android.widget.EditText");
        } else {
            locator = By.className("XCUIElementTypeStaticText");
        }
        return this.getDriver()
                .findElement(locator)
                .getText().trim();
    }
}
