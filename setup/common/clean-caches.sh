#!/bin/bash

if (npm -v | grep -q 4.); then
	npm cache clean && true
else
	npm cache clean --force && true
fi

sudo rm -rf /Library/Caches/com.apple.dt.instruments/* && true
rm -rf ~/Library/Developer/Xcode/DerivedData/* && true
rm -rf ~/Library/Caches/CocoaPods/Pods/Release/* && true
rm -rf ~/.gradle && true
rm -rf ~/.android/build-cache/0* && true
rm -rf ~/.android/build-cache/1* && true
rm -rf ~/.android/build-cache/2* && true
rm -rf ~/.android/build-cache/3* && true
rm -rf ~/.android/build-cache/4* && true
rm -rf ~/.android/build-cache/5* && true
rm -rf ~/.android/build-cache/6* && true
rm -rf ~/.android/build-cache/7* && true
rm -rf ~/.android/build-cache/8* && true
rm -rf ~/.android/build-cache/9* && true
rm -rf ~/.android/build-cache/a* && true
rm -rf ~/.android/build-cache/b* && true
rm -rf ~/.android/build-cache/c* && true
rm -rf ~/.android/build-cache/d* && true
rm -rf ~/.android/build-cache/e* && true
rm -rf ~/.android/build-cache/f* && true
rm -rf ~/.android/build-cache/g* && true
rm -rf ~/.android/build-cache/* && true
sudo rm -rf /Library/Developer/CoreSimulator/Profiles/Runtimes/*8* && true
sudo rm -rf /Library/Developer/CoreSimulator/Profiles/Runtimes/*9.0* && true
sudo rm -rf /Library/Developer/CoreSimulator/Profiles/Runtimes/*9.1* && true
sudo rm -rf /Library/Developer/CoreSimulator/Profiles/Runtimes/*9.2* && true