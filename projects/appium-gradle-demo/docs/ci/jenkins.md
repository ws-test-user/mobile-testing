
# Jenkins

## About 

The leading open source automation server, Jenkins provides hundreds of plugins to support building, deploying and automating any project.

[Officcial Docs](https://jenkins.io/doc/)

## Installation

### macOS

Install with this command:
```
brew install jenkins-lts
```

Post install steps:
```
To have launchd start jenkins-lts now and restart at login:
  brew services start jenkins-lts
Or, if you don't want/need a background service you can just run:
  jenkins-lts
```

### Windows

Download default installer and perform default installation.

## Setup

1. Get initial password:
    - macOS location: `$HOME/.jenkins/secrets/initialAdminPassword`

2. Open browser on http://localhost:8080
3. Enter initial password and select plugins

## Plugins

[Plugin Manager](http://localhost:8080/pluginManager/)

### Green Balls

[Green Balls](https://plugins.jenkins.io/greenballs) makes default blue balls (when build pass) green.

### Junit Plugin

[Junit Plugin](https://wiki.jenkins.io/display/JENKINS/JUnit+Plugin) will draw some nice charts and provide features such as historical analysis of tests results.

### Timestamper

[Timestamper](https://wiki.jenkins.io/display/JENKINS/Timestamper) will print times in console logs of jenkins jobs.

### Job DSL Plugin

[Job DSL Plugin](https://github.com/jenkinsci/job-dsl-plugin) will help you to keep your jobs as code.

## Troubleshooting

### Safe Restart

Open this URL:
```
http://localhost:8080/safeRestart
```
It will schedule restart, but if will wait all running jobs to complete.

### Uninstall on macOS

If installed with official installed Jenkins can be removed with this script:
```
/Library/Application\ Support/Jenkins/Uninstall.command
```
