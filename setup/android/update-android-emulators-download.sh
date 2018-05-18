#!/bin/bash

print_and_execute() {
    echo "sdkmanager $1" # print
    yes | $ANDROID_HOME/tools/bin/sdkmanager "$1" # execute
}

echo "Update Android Emulator Images"
print_and_execute "system-images;android-27;google_apis_playstore;x86"
print_and_execute "system-images;android-26;google_apis_playstore;x86"
print_and_execute "system-images;android-25;google_apis_playstore;x86"
print_and_execute "system-images;android-24;google_apis_playstore;x86"
print_and_execute "system-images;android-23;default;x86"
print_and_execute "system-images;android-22;default;x86"
print_and_execute "system-images;android-21;default;x86"
print_and_execute "system-images;android-19;default;x86"
