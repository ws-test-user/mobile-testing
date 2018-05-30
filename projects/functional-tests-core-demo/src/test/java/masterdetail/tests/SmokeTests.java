package masterdetail.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTests extends MobileTest {

    @Test
    public void test_01_homePage() throws Exception {
        this
                .imageVerification
                .verifyScreen("homePage");
    }

    @Test
    public void test_02_editCar() throws Exception {
        this.find.byText("BMW 5 Series").click();
        this
                .imageVerification
                .verifyScreen("detailPage");

        this.find.byText("Edit").click();
        this
                .imageVerification
                .verifyScreen("editPage");

        // Demo of cross platform locators:
        // this.find.byLocator(this.locators.sliderLocator());

        this.find
                .byType("android.widget.SeekBar")
                .click();

        UIElement newPrise = this.find.byText("€50");
        Assert.assertNotNull(newPrise, "Price is not changed!");
    }
}
