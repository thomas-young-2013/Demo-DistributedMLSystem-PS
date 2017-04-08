#!/bin/bash

echo "NEED execute this script in ROOT directory of this project!!!"
echo "start to generate the executables"
app_home=`pwd`

cd $app_home/project/out
bin_home=`pwd`

# create the target directory.
targetDirName="distributedMLSystem"
if [[ -x $targetDirName ]];  then
    rm -rf $targetDirName
fi
mkdir $targetDirName

# create the directory structure.
cd $targetDirName
mkdir worker server master

cd worker
mkdir src bin logs conf data
cd ../server
mkdir src bin logs conf
cd ../master
mkdir src bin logs conf

# prepare the sample data.
cd ../worker
cp $app_home/clientworker/src/main/resource/sample/*.txt ./data/

