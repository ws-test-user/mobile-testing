package google.tests;

import base.MobileTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmokeTests extends MobileTest {

    @BeforeMethod
    public void beforeMethod() {
        client.getDriver().get(settings.web.baseURL);
    }

    @Test
    public void searchForAppium() {
        String page = client.getDriver().getPageSource();
        System.out.println(page);
    }

    @Test
    public void searchForTemperature() {
        String page = client.getDriver().getPageSource();
        System.out.println(page);
    }
}
