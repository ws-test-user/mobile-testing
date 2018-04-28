#!/bin/bash

echo "Install npm packages."
npm i -g appium
if [[ "$OSTYPE" == "darwin"* ]]; then
    npm uninstall -g ios-deploy && true
    npm install -g ios-deploy
fi
