# Performance Testing Tips& Tricks

## Measure startup time via adb

Install app:
```
$ANDROID_HOME/platform-tools/adb install <path-to-apk>
```

Clean old logs:
```
$ANDROID_HOME/platform-tools/adb logcat -c
```

Start the app:
```
$ANDROID_HOME/platform-tools/adb shell monkey -p <packageID> -c android.intent.category.LAUNCHER 1
```

Get startup time:
```
$ANDROID_HOME/platform-tools/adb logcat | grep Displayed
```
Result will be something like:
```
05-16 18:47:58.227  1679  1702 I ActivityManager: Displayed com.activtrades.app/.ui.login.LoginActivity: +487ms
```
Which means app starts in 487ms.

**Tips:**

Get current package id and activity:
```
$ANDROID_HOME/platform-tools/adb shell dumpsys window windows | grep mCurrentFocus
```
Result will be something like:
```
  mCurrentFocus=Window{b07ffb8 u0 com.activtrades.app/com.activtrades.app.ui.login.LoginActivity}
```
Package ID is `com.activtrades.app`.
Activity is `com.activtrades.app.ui.login.LoginActivity`

## Get memory usage via adb

TODO: Write some docs here. 