package utils;

/**
 * Set of utils for host OS.
 */
public class OSUtils {

    /**
     * Get environment variable.
     *
     * @param variable     Variable name.
     * @param defaultValue Default value.
     * @return Variable value (or default if variable is not set).
     */
    public static String getEnvironmentVariable(String variable, String defaultValue) {
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
