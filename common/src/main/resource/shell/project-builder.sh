#!/bin/bash

# echo "CONFIRM: execute this script in ROOT directory of this project!!!"

app_home=`pwd`

# prepare the common module.
cd $app_home/common
mvn clean assembly:assembly install

# deploy the worker
cd $app_home/clientworker
mvn clean assembly:assembly install

# deploy the server
cd $app_home/parameterserver
mvn clean assembly:assembly install

# deploy the master
cd $app_home/masterserver
mvn clean assembly:assembly install
