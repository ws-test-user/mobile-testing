#!/bin/bash

echo "Install dependencies for real iOS devices"
brew uninstall --ignore-dependencies libimobiledevice && true
brew uninstall --ignore-dependencies ideviceinstaller && true
brew uninstall --ignore-dependencies ios-webkit-debug-proxy && true
brew uninstall --ignore-dependencies carthage && true
brew uninstall --ignore-dependencies fbsimctl && true
brew uninstall --ignore-dependencies maven && true
brew install libimobiledevice --HEAD
brew install ideviceinstaller --HEAD
brew install ios-webkit-debug-proxy
brew install carthage
brew install maven
