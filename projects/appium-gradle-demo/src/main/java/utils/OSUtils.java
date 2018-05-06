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
}
