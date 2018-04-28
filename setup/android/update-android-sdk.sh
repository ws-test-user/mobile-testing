#!/bin/bash

print_and_execute() {
    echo "sdkmanager $1" # print
    yes | $ANDROID_HOME/tools/bin/sdkmanager "$1" # execute
}

echo "Update Android packages."
print_and_execute "--licenses"
print_and_execute "platform-tools"
print_and_execute "tools"
print_and_execute "build-tools;27.0.3"
print_and_execute "build-tools;26.0.3"
print_and_execute "build-tools;25.0.3"
print_and_execute "platforms;android-27"
print_and_execute "platforms;android-26"
print_and_execute "platforms;android-25"
print_and_execute "platforms;android-24"
print_and_execute "platforms;android-23"
print_and_execute "platforms;android-22"
print_and_execute "platforms;android-21"
print_and_execute "platforms;android-19"
print_and_execute "emulator"
print_and_execute "extras;android;m2repository"
print_and_execute "extras;google;m2repository"

