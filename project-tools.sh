#!/bin/bash

echo "CONFIRM: execute this script in ROOT directory of this project!!!"
echo `pwd`
echo "start to generate the executables in 2 seconds.."
sleep 2

script_one="./common/src/main/resource/shell/project-builder.sh"
if [ ! -x $script_one ]; then
    chmod 777 $script_one
fi

$script_one
