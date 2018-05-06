package calc.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Calculator app abstraction.
 * Can also look at this class as page object for calculator home page.
 */
public class Calc {
    private AppiumDriver driver;

    public Calc(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clickNumber(String number) {
        String id = "net.ludeke.calculator:id/digit" + number;
        MobileElement num = (MobileElement) this.driver.findElementById(id);
        num.click();
        System.out.println("Click " + number);
    }

    public void performOperation(String operation) {
        MobileElement op = (MobileElement) this.driver.findElementByAccessibilityId(operation);
        op.click();
        System.out.println("Click " + operation);
    }

    public void clean() {
        List<WebElement> buttons = this.driver
                .findElementsById("net.ludeke.calculator:id/clear");
        if (buttons.size() > 0) {
            buttons.get(0).click();
        } else {
            WebElement button = this.driver.findElementById("net.ludeke.calculator:id/del");
            while (!this.getResult().equalsIgnoreCase("")) {
                button.click();
            }
        }
        System.out.println("Clean the calculator.");
    }

    public String getResult() {
        return this.driver
                .findElement(By.className("android.widget.EditText"))
                .getText().trim();
    }
}
