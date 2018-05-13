package base;

import appium.Client;
import appium.Server;
import enums.PlatformType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestResult;
import org.testng.annotations.*;
import settings.MobileSettings;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;

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
     * - Get screenshot.
     * - Get UI tree.
     * - Get device logs.
     *
     * @param result ITestResult object.
     * @throws IOException when fail to get or save artifacts.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            this.collectArtifacts(result);
        }
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

    private void collectArtifacts(ITestResult result) throws IOException {
        // Get test method name.
        String testName = result.getMethod().getMethodName();
        System.out.println(testName + " failed. Collect artefacts...");

        // Save screenshot.
        File screenshot = client.getDriver().getScreenshotAs(OutputType.FILE);
        String screenshotFileNme = String.format("%s_%s.png", settings.deviceName, testName);
        FileUtils.copyFile(screenshot, new File(screenshotFileNme));
        System.out.println("Screenshot saved in " + screenshotFileNme);

        // Save page source
        String pageSource = client.getDriver().getPageSource();
        String formatedPageSource = prettyFormat(pageSource, 2);
        String pageSourceFileName = String.format("%s_%s.xml", settings.deviceName, testName);
        Files.write(Paths.get(pageSourceFileName), formatedPageSource.getBytes());
        System.out.println("Page source saved in " + pageSourceFileName);

        // Get logs
        if (settings.platform == PlatformType.ANDROID) {
            String logcat = getAdbLog();
            String logFile = String.format("%s_%s.log", settings.deviceName, testName);
            Files.write(Paths.get(logFile), logcat.getBytes());
            System.out.println("Device logs saved in " + pageSourceFileName);
        }
    }

    private static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    private static String getAdbLog() {
        try {
            List<LogEntry> logEntries = client.getDriver().manage()
                    .logs()
                    .get("logcat")
                    .filter(Level.ALL);
            if (logEntries != null) {
                StringBuilder logBuilder = new StringBuilder();
                for (LogEntry logEntry : logEntries) {
                    logBuilder
                            .append(logEntry.getMessage())
                            .append("\r\n");
                }
                return logBuilder.toString();
            }
            return null;
        } catch (Exception e) {
            System.out.println("Could not generate device logs");
            return null;
        }
    }
}
