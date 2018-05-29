package calc;

import functional.tests.core.mobile.basepage.BasePage;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class Calc extends BasePage {

    public Calc() {
        super();
    }

    public void clickDigit(int digit) {
        MobileElement button = (MobileElement) this.client.driver.findElementById("net.ludeke.calculator:id/digit" + String.valueOf(digit));
        button.click();
        this.log.info("Click " + String.valueOf(digit));
    }

    public void performOperation(String operation) {
        MobileElement button = (MobileElement) this.client.driver.findElementByAccessibilityId(operation);
        button.click();
        this.log.info("Click " + operation);
    }

    public String getResult() {
        MobileElement result = (MobileElement) this.client.driver.findElement(By.className("android.widget.EditText"));
        String finalResult = result.getText().trim().replaceAll("minus", "-");
        this.log.info("Result: " + finalResult);
        return finalResult;
    }
}
