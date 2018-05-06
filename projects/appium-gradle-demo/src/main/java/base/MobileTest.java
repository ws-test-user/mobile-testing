package base;

import appium.Client;
import appium.Server;
import org.testng.annotations.*;
import settings.MobileSettings;

public class MobileTest {

    private Server server;
    private Client client;
    private MobileSettings settings;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        // Init settings.
        this.settings = new MobileSettings();

        // Start appium server.
        this.server = new Server();
        this.server.start();

        // Start appium client.
        this.client = new Client(this.server.getService(), this.settings);
        this.client.start();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        // Stop appium client and server.
        this.client.stop();
        this.server.stop();
    }
}
