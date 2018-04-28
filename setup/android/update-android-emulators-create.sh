#!/bin/bash

configure_emulators() {
    # Append "hw.gpu.enabled=yes", "hw.lcd.density=240" and "skin.name=480x800" to config.ini file of each emulator
    find ~/.android/avd -type f -name 'config.ini' -exec bash -c 'echo $0 && echo "hw.lcd.density=240" | tee -a $0 && echo "skin.name=480x800" | tee -a $0 && echo "hw.gpu.enabled=yes"  | tee -a $0 && echo "hw.keyboard=no" | tee -a $0 && cat $0' {} \;
}

echo "Cleanup existing emulators."
rm -rf ~/.android/avd/*


echo "Create emulators."
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api19-Default -k "system-images;android-19;default;x86" -b default/x86 -c 320M -f
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api21-Default -k "system-images;android-21;default;x86" -b default/x86 -c 320M -f
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api22-Default -k "system-images;android-22;default;x86" -b default/x86 -c 320M -f
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api23-Default -k "system-images;android-23;default;x86" -b default/x86 -c 320M -f
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api24-Default -k "system-images;android-24;default;x86" -b default/x86 -c 320M -f
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api25-Google -k "system-images;android-25;google_apis;x86" -b google_apis/x86 -c 320M -f
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api26-Google -k "system-images;android-26;google_apis;x86" -b google_apis/x86 -c 320M -f
echo no | $ANDROID_HOME/tools/bin/avdmanager create avd -n Emulator-Api27-Google -k "system-images;android-27;google_apis;x86" -b google_apis/x86 -c 320M -f

echo "Configure emulators."
configure_emulators
