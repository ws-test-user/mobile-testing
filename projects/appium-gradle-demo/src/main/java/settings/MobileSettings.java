package settings;

import org.openqa.selenium.Platform;

public class MobileSettings {

    public Platform platform;
    public String app;
    public String avd;
    public String deviceName;
    public String platformVersion;
    public int findTimeout;

    public MobileSettings() {
        this.platform = Platform.ANDROID;
        this.app = "https://github.com/dtopuzov/mobile-testing/raw/master/testapps/Calculator_2.0.apk";
        this.avd = "Emulator-Api23-Default";
        this.deviceName = "Emulator-Api23-Default";
        this.platformVersion = "6.0";
        this.findTimeout = 30;
    }
}
