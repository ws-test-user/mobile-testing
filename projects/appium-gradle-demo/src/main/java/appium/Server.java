package appium;

import enums.OSType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import settings.MobileSettings;
import utils.Proc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
                .withLogFile(new File("appium-server.log"))
                .usingAnyFreePort()
                .withIPAddress("127.0.0.1")
                .usingDriverExecutable(this.getNodeExecutable())
                .withAppiumJS(this.getAppiumExecutable())
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
            System.out.println("Appium server is up and running!");
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

    /**
     * Create log file for Appium server.
     *
     * @return File for Appium server logs.
     * @throws IOException When fail to create log file.
     */
    private File createLogFile() throws IOException {
        File logFile = new File("appium-server.log");
        Files.deleteIfExists(logFile.toPath());
        logFile.getParentFile().mkdirs();
        boolean createLogFileResult = logFile.createNewFile();

        if (createLogFileResult) {
            System.out.println("Appium log file created.");
        } else {
            System.out.println("Failed to create Appium log file.");
        }
        return logFile;
    }

    /**
     * Get Appium executable file.
     *
     * @return Appium executable file.
     * @throws Exception when Appium executable not found.
     */
    private File getAppiumExecutable() throws Exception {

        // Find Appium path.
        String appiumPath;
        if (this.settings.hostOS == OSType.Windows) {
            // TODO (dtopuzov): Try to use `where appium` or something else.
            appiumPath = System.getenv("APPDATA") + "\\npm\\node_modules\\appium\\build\\lib\\main.js";
        } else {
            String[] command = {"which appium"};
            appiumPath = Proc.start(command).trim();
        }

        // Check if exists
        File appiumExecutable = new File(appiumPath);

        // Log success or failure.
        if (!appiumExecutable.exists()) {
            String error = "Appium does not exist at: " + appiumPath;
            System.out.println(error);
            throw new Exception(error);
        } else {
            System.out.println("Appium Executable: " + appiumPath);
        }

        // Return Appium executable file.
        return appiumExecutable;
    }

    /**
     * Get NodeJS executable file.
     *
     * @return NodeJS executable file.
     * @throws Exception when NodeJS executable not found.
     */
    private File getNodeExecutable() {
        return new File("");
    }
}
