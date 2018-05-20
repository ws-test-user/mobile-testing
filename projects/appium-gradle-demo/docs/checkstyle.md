# Checkstyle

## About
[Checkstyle](https://docs.gradle.org/current/userguide/checkstyle_plugin.html) is used to check code style.

## Usage

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
