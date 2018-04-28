#!/bin/bash

echo "Install Intel HAXM."
echo "y" | $ANDROID_HOME/tools/bin/sdkmanager "extras;intel;Hardware_Accelerated_Execution_Manager"
chmod +x $ANDROID_HOME/extras/intel/Hardware_Accelerated_Execution_Manager/silent_install.sh
echo $USER_PASS | sudo $ANDROID_HOME/extras/intel/Hardware_Accelerated_Execution_Manager/silent_install.sh