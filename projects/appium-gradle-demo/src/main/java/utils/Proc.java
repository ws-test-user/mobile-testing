package utils;

import enums.OSType;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wrapper for running and stopping processes.
 */
public class Proc {

    private static final String[] WIN_RUNTIME = {"cmd.exe", "/C"};
    private static final String[] UNIX_RUNTIME = {"/bin/bash", "-l", "-c"};

    /**
     * Start process.
     *
     * @param command command (including options).
     * @param timeout timeout in seconds.
     * @param wait    boolean property to specify
     *                if method should wait for process to complete
     *                or leave it running on background.
     * @return process output as String.
     * @throws IOException          when fail to read process output.
     * @throws TimeoutException     when process fail to complete is specified timeout.
     */
    public static String start(String[] command, int timeout, boolean wait) throws IOException, TimeoutException {

        String[] finalCommand;
        if (OS.getOSType() == OSType.WINDOWS) {
            finalCommand = concat(WIN_RUNTIME, command);
        } else {
            finalCommand = concat(UNIX_RUNTIME, command);
        }

        java.lang.Process p = new ProcessBuilder(finalCommand).start();

        try {
            p.waitFor(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Failed to wait for process.");
        }

        InputStream error = p.getErrorStream();
        InputStream input = p.getInputStream();

        if (wait) {
            if (p.isAlive()) {
                p.destroyForcibly();
                String e = String.format("Process '%s' timeout after %s seconds.",
                        String.join(" ", command),
                        String.valueOf(timeout));
                throw new TimeoutException(e);
            }
            return read(input) + "\n" + read(error);
        } else {
            return null;
        }
    }

    /**
     * Start process.
     *
     * @param command command (including options).
     * @param wait    boolean property to specify
     *                if method should wait for process to complete
     *                or leave it running on background.
     * @return process output as String.
     * @throws IOException          when fail to read process output.
     * @throws TimeoutException     when process fail to complete in 30 seconds.
     */
    public static String start(String[] command, boolean wait) throws IOException, TimeoutException {
        return start(command, 30, wait);
    }

    /**
     * Start process.
     *
     * @param command command (including options).
     * @param timeout timeout in seconds.
     * @return process output as String.
     * @throws IOException          when fail to read process output.
     * @throws TimeoutException     when process fail to complete is specified timeout.
     */
    public static String start(String[] command, int timeout) throws IOException, TimeoutException {
        return start(command, timeout, true);
    }

    /**
     * Start process.
     *
     * @param command command (including options).
     * @return process output as String.
     * @throws IOException          when fail to read process output.
     * @throws TimeoutException     when process fail to complete in 30 seconds.
     */
    public static String start(String[] command) throws IOException, TimeoutException {
        return start(command, 30, true);
    }

    /**
     * Stop all processes by specified command/process name.
     *
     * @param command     command/process name.
     * @param commandline commandline params of command/process name.
     */
    public static void stop(String command, String commandline) {
        Stream<ProcessHandle> processes = ProcessHandle.allProcesses()
                .filter(ph -> ph.info().command().isPresent())
                .filter(p -> p.info().command().get()
                        .substring(p.info().command().get().lastIndexOf(File.separator) + 1)
                        .trim()
                        .equalsIgnoreCase(command));

        if (commandline != null) {
            processes.filter(ph -> ph.info().commandLine().get().contains(commandline));
        }

        processes.forEach(ProcessHandle::destroyForcibly);
    }

    /**
     * Stop all processes by specified command/process name.
     *
     * @param command command/process name.
     */
    public static void stop(String command) {
        stop(command, null);
    }

    /**
     * Check if process is running.
     *
     * @param command     Command string (for example `node`).
     * @param commandline Commandline options of the command.
     * @return true if process is running.
     */
    public static boolean isRunning(String command, String commandline) {
        Stream<ProcessHandle> processes = ProcessHandle.allProcesses()
                .filter(ph -> ph.info().command().isPresent())
                .filter(p -> p.info().command().get()
                        .substring(p.info().command().get().lastIndexOf(File.separator) + 1)
                        .trim()
                        .equalsIgnoreCase(command));

        if (commandline != null) {
            processes.filter(ph -> ph.info().commandLine().get().contains(commandline));
        }

        return processes.count() > 0;
    }

    /**
     * Check if process is running.
     *
     * @param command Command string (for example `node`).
     * @return true if process is running.
     */
    public static boolean isRunning(String command) {
        return isRunning(command, null);
    }

    private static String read(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
