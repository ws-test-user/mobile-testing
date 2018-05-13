package settings;

import enums.OSType;
import enums.PlatformType;
import utils.OS;

/**
 * Mobile settings.
 */
public class MobileSettings {

    public OSType hostOS;
    public PlatformType platform;
    public String app;
    public String avd;
    public String deviceName;
    public String platformVersion;
    public String appiumServerLogLevel;
    public int findTimeout;

    /**
     * Init mobile settings.
     */
    public MobileSettings() {


        this.platform = PlatformType.ANDROID;
        this.app = "https://github.com/dtopuzov/mobile-testing/raw/master/testapps/Calculator_2.0.apk";
        this.avd = "Emulator-Api23-Default";
        this.deviceName = "Emulator-Api23-Default";
        this.platformVersion = "6.0";


        /**
         this.platform = PlatformType.IOS;
         this.app = "https://github.com/dtopuzov/mobile-testing/raw/master/testapps/ios-calculator.zip";
         this.deviceName = "iPhone 5s";
         this.platformVersion = "11.3";
         **/

        this.hostOS = OS.getOSType();
        this.appiumServerLogLevel = "error";
        this.findTimeout = 5;
    }
}
