package utils;

import enums.OSType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Set of utils for host OS.
 */
public class OS {

    /**
     * Get current OS type.
     *
     * @return OSType enum value (or null for unknown OS).
     */
    public static OSType getOSType() {
        String osTypeString = System.getProperty("os.name", "generic").toLowerCase();
        if ((osTypeString.contains("mac")) || (osTypeString.contains("darwin"))) {
            return OSType.MAC;
        } else if (osTypeString.contains("win")) {
            return OSType.WINDOWS;
        } else if (osTypeString.contains("nux")) {
            return OSType.LINUX;
        } else {
            return null;
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
     * Find executable by its name.
     *
     * @param command     name of command.
     * @param defaultPath default path for this command.
     * @return File object to executable.
     * @throws FileNotFoundException if executable not found.
     */
    public static File getExecutable(String command, String defaultPath) throws FileNotFoundException {
        String findCommand;
        if (OS.getOSType() == OSType.WINDOWS) {
            findCommand = "where";
        } else {
            findCommand = "which";
        }

        /**
         * Sample input/outputs:
         *
         * INPUT:
         * where fakeCommand
         * OUTPUT:
         * INFO: Could not find files for the given pattern(s).
         *
         * INPUT:
         * where appium
         * OUTPUT:
         * C:\Users\Mitaka_F1\AppData\Roaming\npm\appium
         * C:\Users\Mitaka_F1\AppData\Roaming\npm\appium.cmd
         */

        String findOutput = "";
        try {
            findOutput = Proc.start(new String[]{findCommand + " " + command}).trim();
        } catch (Exception e) {
            System.out.println("DEBUG: failed to execture command.");
        }
        ;

        String executablePath = Arrays.stream(findOutput
                .split("\\r?\\n"))
                .filter(s -> s.contains(command))
                .findFirst()
                .orElse(defaultPath);

        // Check if exists
        File executable = new File(executablePath);

        // Log success or failure.
        if (!executable.exists()) {
            String error = String.format("%s not found!", executable);
            System.out.println(error);
            throw new FileNotFoundException(error);
        } else {
            System.out.println(String.format("%s found at %s", command, executablePath));
        }

        // Return executable file.
        return executable;
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
