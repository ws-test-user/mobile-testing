#!/bin/bash

echo "Clean ~/.bash_profile"
echo "" > ~/.bash_profile
if [ ! -e "$HOME/.personal_profile" ]; then
    echo "Personal profile does not exist."
else 
    echo "Source personal profile"
    echo 'source $HOME/.personal_profile' >> ~/.bash_profile
fi 

echo "Enable developer mode"
echo $USER_PASS | sudo /usr/sbin/DevToolsSecurity --enable

echo "Install Homebrew"
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
