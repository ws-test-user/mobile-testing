#!/bin/bash

# Please setup env variable called USER_PASS containing password of current user
dir="$(dirname "$0")"

if [[ "$@" == "--force" ]]; then
    export FORCE=true
fi

# If FORCE environment variable is set run full setup
if [[ "$FORCE" == "true" ]]; then
    if [[ "$OSTYPE" == "darwin"* ]]; then
        echo "Run full setup."
        "$dir/common/pre-setup.sh"
        "$dir/android/install-java.sh"
        "$dir/android/install-android-sdk.sh"
        "$dir/android/install-android-haxm.sh"
        "$dir/common/install-maven.sh"
        "$dir/common/install-node.sh"
    else
        echo "Initial install of node, java and android not implemented for linux."
    fi
fi
echo "Update existing environment."
"$dir/common/clean-caches.sh"
"$dir/common/install-npm-packages.sh"
"$dir/android/update-android-sdk.sh"
"$dir/android/update-android-emulators-download.sh"
"$dir/android/update-android-emulators-create.sh"
if [[ "$OSTYPE" == "darwin"* ]]; then
    "$dir/ios/install-brew-packages.sh"
    "$dir/ios/install-cocoa-pods.sh"
fi
