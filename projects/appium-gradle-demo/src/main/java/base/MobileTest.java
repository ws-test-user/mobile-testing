package base;

import appium.Client;
import appium.Server;
import org.testng.annotations.*;
import settings.MobileSettings;

/**
 * Base mobile test.
 */
public class MobileTest {

    private Server server;
    protected Client client;
    protected MobileSettings settings;

    /**
     * Before Suite logic.
     * - Init settings.
     * - Start appium server.
     * - Start appium client.
     *
     * @throws Exception when fail to start appium server.
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception {
        // Init settings.
        this.settings = new MobileSettings();

        // Start appium server.
        this.server = new Server(this.settings);
        this.server.start();

        // Start appium client.
        this.client = new Client(this.server.getService(), this.settings);
        this.client.start();
    }

    /**
     * Before class logic.
     */
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

    }

    /**
     * Before test method logic.
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

    }

    /**
     * After test method logic.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

    }

    /**
     * After class logic.
     */
    @AfterClass(alwaysRun = true)
    public void afterClass() {
    }

    /**
     * After suite logic.
     * - Stop appium client.
     * - Stop appium server.
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        // Stop appium client and server.
        this.client.stop();
        this.server.stop();
    }
}
