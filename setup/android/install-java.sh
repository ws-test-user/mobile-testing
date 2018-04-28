#!/bin/bash

echo "Install JDK 8."
brew tap caskroom/versions
brew cask install java8

echo "Add Java to PATH."
if [[ ! -z $(grep "JAVA_HOME" ~/.bash_profile) ]]; then 
    echo "JAVA_HOME found in ~/.bash_profile"; 
else
    echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)' >> ~/.bash_profile
fi
source ~/.bash_profile
