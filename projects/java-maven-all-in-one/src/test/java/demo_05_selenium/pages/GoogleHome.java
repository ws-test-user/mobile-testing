package demo_05_selenium.pages;

import demo_05_selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Abstraction of Google home page.
 */
public class GoogleHome extends BasePage {

    public GoogleHome(WebDriver driver) {
        super(driver);
    }

    // This will work for Desktop web browsers.
    // In order to make GoogleHome page class more generic
    // we will use getSearchBox() method.
    //
    // @FindBy(id = "lst-ib")
    // private WebElement searchBox;

    /**
     * Get SearchBox element.
     *
     * @return searchBox element.
     */
    private WebElement getSearchBox() {
        // Locators on Desktop browser and Mobile browser are different.
        // Hint:
        // When we use desktop browsers we use Selenium WebDriver.
        // On mobile browsers we use Appium Driver.
        // Based on driver we can distinguish where we are
        // and based on where we are we can use different locator.
        if (this.driver.getClass().toString().contains("org.openqa.selenium")) {
            return this.driver.findElement(By.id("lst-ib"));
        } else {
            return this.driver.findElement(By.className("gLFyf"));
        }
    }

    /**
     * Navigate to Google home page.
     *
     * @return Self reference.
     */
    public GoogleHome navigateTo() {
        this.driver.navigate().to("https://www.google.com/ncr");
        System.out.println("Navigate to Google home page.");
        return this;
    }

    /**
     * Search for something in google search form.
     *
     * @param searchTerm Search string.
     * @return Self reference.
     */
    public GoogleHome searchFor(String searchTerm) {
        this.getSearchBox().sendKeys(searchTerm);
        System.out.println("Search for " + searchTerm);
        this.getSearchBox().sendKeys(Keys.ENTER);
        System.out.println("Hit Enter key.");
        return this;
    }

    /**
     * Verify link exists in search results.
     *
     * @param linkText Link text.
     */
    public void verifyLinkExist(String linkText) {
        WebElement link = this.driver.findElement(By.xpath("//a[@href='" + linkText + "']"));
        Assert.assertTrue(link.isDisplayed(), linkText + " is not visible.");
        System.out.println(linkText + " is visible.");
    }
}
