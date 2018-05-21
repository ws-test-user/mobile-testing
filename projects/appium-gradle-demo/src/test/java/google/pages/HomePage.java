package google.pages;

import base.MobilePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.Settings;

public class HomePage extends MobilePage {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@aria-label='Google Search']")
    private WebElement searchButton;

    public HomePage(Settings settings, AppiumDriver driver) {
        super(settings, driver);
    }

    public HomePage searchFor(String term) {
        this.searchBox.clear();
        this.searchBox.sendKeys(term);
        searchButton.click();
        System.out.println("Search for " + term);
        return this;
    }

    /**
     * Verify link exists.
     *
     * @param linkText partial (or full) text of the link.
     */
    public void verifyLinkExist(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(@href, '" + linkText + "')]")));
    }
}
