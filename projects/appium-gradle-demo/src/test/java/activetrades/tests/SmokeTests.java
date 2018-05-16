package activetrades.tests;

import activetrades.pages.LoginPage;
import base.MobileTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Smoke tests for ActivTrades application.
 */
public class SmokeTests extends MobileTest {

    LoginPage login;

    /**
     * Init login page before all tests in this class.
     */
    @BeforeClass
    public void beforeClass() {
        login = new LoginPage(settings, client.getDriver());
    }

    @Test
    public void loginScreenShouldLooksOK() throws IOException {
        this.login.waitForScreen("loginScreen");
    }
}
