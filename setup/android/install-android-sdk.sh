#!/bin/bash

echo "Unstall older Android SDKs."
brew uninstall android-sdk && true;
brew cask uninstall android-sdk && true;
rm -rf ~/.android

echo "Install Android SDK."
brew cask install android-sdk

echo "Add ANDROID_HOME variable."
if [[ ! -z $(grep "android-sdk" ~/.bash_profile) ]]; then 
    echo "android-sdk found in ~/.bash_profile"; 
else
    echo 'export ANDROID_HOME=/usr/local/share/android-sdk' >> ~/.bash_profile
    echo 'export ANDROID_SDK_ROOT=/usr/local/share/android-sdk' >> ~/.bash_profile
fi
source ~/.bash_profile

echo "Accept licenses"
yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses
touch $HOME/.android/repositories.cfg