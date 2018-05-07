package appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import settings.MobileSettings;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Appium server abstraction.
 */
public class Server {

    private AppiumDriverLocalService service;
    private MobileSettings settings;

    /**
     * Init appium server.
     *
     * @param settings Mobile settings.
     */
    public Server(MobileSettings settings) {
        this.settings = settings;
    }

    /**
     * Get appium server service.
     *
     * @return appium server service.s
     */
    public AppiumDriverLocalService getService() {
        return this.service;
    }

    /**
     * Start appium server.
     *
     * @throws Exception when fail to start appium server.
     */
    public void start() throws Exception {
        // Set Appium server settings.
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .withLogFile(new File("temp.log"))
                .usingAnyFreePort()
                .withIPAddress("127.0.0.1")
                .withStartUpTimeOut(180, TimeUnit.SECONDS)
                .withArgument(GeneralServerFlag.LOG_LEVEL, this.settings.appiumServerLogLevel);

        // Start Appium server.
        this.service = AppiumDriverLocalService.buildService(serviceBuilder);
        System.out.println("Starting Appium server...");
        this.service.start();

        // Verify Appium server started.
        if (this.service == null || !this.service.isRunning()) {
            String error = "Appium server failed to start! Please check appium log file.";
            System.out.println(error);
            throw new Exception(error);
        } else {
            System.out.println("Appium Server is up and running!");
        }
    }

    /**
     * Stop appium server.
     */
    public void stop() {
        try {
            this.service.stop();
            System.out.println("Stop Appium server.");
        } catch (Exception e) {
            System.out.println("Failed to stop Appium server.");
        }
    }
}
