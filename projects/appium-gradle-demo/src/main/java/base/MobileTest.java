package base;

import appium.Client;
import appium.Server;
import org.testng.annotations.*;
import settings.MobileSettings;

/**
 * Base mobile test.
 */
public abstract class MobileTest {

    private static Server server;
    protected static Client client;
    protected static MobileSettings settings;

    /**
     * Before suite logic.
     * - Init settings.
     * - Start appium server.
     * - Start appium client.
     *
     * @throws Exception when fail to start appium server.
     */
    @BeforeSuite
    public void beforeSuite() throws Exception {
        // Init mobile settings.
        settings = new MobileSettings();

        // Start appium server.
        server = new Server(settings);
        server.start();

        // Start appium client.
        client = new Client(server.getService(), settings);
        client.start();
    }

    /**
     * Before class logic.
     */
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("Mobile Test -> beforeClass");
    }

    /**
     * Before test method logic.
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        System.out.println("Mobile Test -> beforeMethod");
    }

    /**
     * After test method logic.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        System.out.println("Mobile Test -> afterMethod");
    }

    /**
     * After class logic.
     */
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("Mobile Test -> afterClass");
    }

    /**
     * After suite logic.
     * - Stop appium client.
     * - Stop appium server.
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        // Stop appium client and server.
        client.stop();
        server.stop();
    }
}
