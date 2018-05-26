# Appium Demo Project

## About

!!! Important !!!

This project is work in progress!

## Run Tests

### Run via IDE
Specify config in VM Options:
```
-Dconfig=selendroid.emu.api19
```

### Run via commandline

Run calculator tests on configuration specified in calc.emu.api23.properties:

```
./gradlew clean calcTests -Dconfig=calc.emu.api23
```

## Additional Resources

[Tips, Trick and Howtos](docs)

[Appium Docs](https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/mobile-web.md)

[Chrome Driver Docs](https://sites.google.com/a/chromium.org/chromedriver/getting-started/getting-started---android)
