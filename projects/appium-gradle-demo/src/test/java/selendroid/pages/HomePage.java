package selendroid.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "io.selendroid.testapp:id/input_adds_check_box")
    public WebElement checkBox;

    @FindBy(id = "io.selendroid.testapp:id/showPopupWindowButton")
    public WebElement popupWindowButton;

    @FindBy(id = "io.selendroid.testapp:id/showToastButton")
    public WebElement toastButton;

    public HomePage(AppiumDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
