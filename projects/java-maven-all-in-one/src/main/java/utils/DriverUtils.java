package utils;

import enums.OSType;

import java.io.File;

/**
 * WebDriver utils.
 */
public class DriverUtils {

    public String getDriverPath(OSType os, String driverVersion) {
        String path = System.getProperty("user.dir") + File.separator
                + "libs" + File.separator + driverVersion
                + os.toString().toLowerCase()
                + File.separator + "chromedriver";
        if (os == OSType.Windows) {
            path = path + ".exe";
        }
        return path;
    }
}
