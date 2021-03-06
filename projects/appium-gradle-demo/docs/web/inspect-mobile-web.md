# Inspect web pages on mobile devices

## Android

Quick Steps:

0. Connect mobile device with USB cable
    - Please make sure developer mode is enables.
    - Please make sure usb debugging is allowed.
1. Open `chrome://inspect/#devices` on desktop host
3. Trust device (accept usb debuggind dialog on device).
    - This happens only first time when you connect new device. 
4. Manually open chrome browser on device
5. Go back to desktop browser (already opened on `chrome://inspect/#devices`)
    - Find you mobile device and click `inspect` lin next to it.
    
Notes:

The same can be used to inspect webviews inside apps.

More Info:

http://toolsqa.com/mobile-automation/appium/inspect-elements-of-mobile-web-application

## iOS

First [enable web inspector](https://developer.apple.com/library/content/documentation/NetworkingInternetWeb/Conceptual/Web_Inspector_Tutorial/EnableWebInspector/EnableWebInspector.html)

Then follow some of those articles:

[Article 1](https://webdesign.tutsplus.com/articles/quick-tip-using-web-inspector-to-debug-mobile-safari--webdesign-8787)

[Article 2](https://support.saucelabs.com/hc/en-us/articles/115002200207-Opening-the-Web-Inspector-to-Debug-Applications-in-the-iOS-Simulator-Mobile-Safari)
