# HowTos

## Measure startup time 
Run those commands:
```
$ANDROID_HOME/platform-tools/adb logcat -c
$ANDROID_HOME/platform-tools/adb shell monkey -p <packageID> -c android.intent.category.LAUNCHER 1
$ANDROID_HOME/platform-tools/adb logcat | grep Displayed
```
Result will be something like:
```
05-16 18:47:58.227  1679  1702 I ActivityManager: Displayed com.activtrades.app/.ui.login.LoginActivity: +487ms
```
Which means app starts in 487ms.

Tips:
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

## Check for code style and errors

[Checkstyle](https://docs.gradle.org/current/userguide/checkstyle_plugin.html) is used to check code style.

How to add checkstyle to your gradle project:

1. Add this in `build.gradle`:
```
apply plugin: 'checkstyle'
```

2. Add checkstyle rules:

Create this file `config/checkstyle/checkstyle.xml` and add rules.
For example: see rules in this project.

3. (Optional) Run checkstyle before build and calc.tests. 

`build` and `test` tasks dependes on `check`, so checks will be executed each time when you build or run calc.tests.

Task depencies are defined in `build.gradle`
```
build.dependsOn(check)
test.dependsOn(check)
check.dependsOn.remove(test)
```

## Gradle Tips & Tricks

### Create gradle wrapper 

This project already has gradle wrapper.
Here is how you can do it on brand new project:

1. Download and install [Gradle](https://gradle.org/)

2. Run this in project root:
```
gradle wrapper --gradle-version 4.7
```

## IntelliJ IDEA Tips & Tricks

### Generate root directories automatically

When create brand new gradle project IntelliJ IDEA do not generate default project structure.
Here is how you can do it:

1. Create new gradle prject

2. Check this settings:
```
IntelliJ IDEA -> Preferences -> Build, Execution, Deployment -> Build Tools ->
Gradle -> check the "Create directories for empty content roots automatically".
```
