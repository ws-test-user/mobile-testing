package utils;

import enums.OSType;
import exceptions.UnknownOSException;

/**
 * Set of utils for host OS.
 */
public class OS {

    /**
     * Get current OS type.
     *
     * @return OSType enum value.
     * @throws Exception when can not define current OS.
     */
    public static OSType getOSType() throws UnknownOSException {
        String osTypeString = System.getProperty("os.name", "generic").toLowerCase();
        if ((osTypeString.contains("mac")) || (osTypeString.contains("darwin"))) {
            return OSType.MacOS;
        } else if (osTypeString.contains("win")) {
            return OSType.Windows;
        } else if (osTypeString.contains("nux")) {
            return OSType.Linux;
        } else {
            throw new UnknownOSException("Unknown host OS.");
        }
    }

    /**
     * Get environment variable.
     *
     * @param variable     Variable name.
     * @param defaultValue Default value.
     * @return Variable value (or default if variable is not set).
     */
    public static String getenv(String variable, String defaultValue) {
        String value = System.getenv(variable);
        if (System.getenv(variable) != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Detect if Java debugger is attached.
     *
     * @return True if debugger is attached, false if not.
     */
    public static boolean isDebuggerAttached() {
        return java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
    }
}
