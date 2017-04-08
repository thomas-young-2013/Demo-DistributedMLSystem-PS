#!/bin/bash

# echo "CONFIRM: execute this script in ROOT directory of this project!!!"

app_home=`pwd`

cd $app_home/project/out
bin_home=`pwd`

# create the target directory.
targetDirName="distributedMLSystem"
if [[ -x $targetDirName ]];  then
    rm -rf $targetDirName
fi

if [ -f "${targetDirName}.zip" ]; then
    rm "${targetDirName}.zip"
    echo "remove the old zip"
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

sub_project=(worker server master)
src_sub_project=(clientworker parameterserver masterserver)
target_jar=(client-worker parameter-server master-server)

cd $app_home
for t in 0 1 2
do
    project=${sub_project[$t]}
    # copy the properties file
    log4j_props="./conf/${project}.properties"
    properties="./conf/${project}-log4j.properties"
    cp $log4j_props $bin_home/$targetDirName/${project}/conf/
    cp $properties $bin_home/$targetDirName/${project}/conf/

    # copy the scripts file
    src_project=${src_sub_project[$t]}
    start_script="./${src_project}/src/main/resource/deploy/start.sh"
    stop_script="./${src_project}/src/main/resource/deploy/stop.sh"
    cp $start_script $bin_home/$targetDirName/${project}/bin/
    cp $stop_script $bin_home/$targetDirName/${project}/bin/

    # copy the executable jar
    jar_file="./${src_project}/target/${target_jar[$t]}-1.0-SNAPSHOT-jar-with-dependencies.jar"
    cp $jar_file $bin_home/$targetDirName/${project}/src/
done

# zip the files
cd $bin_home
zip -r "${targetDirName}.zip" ./$targetDirName/
