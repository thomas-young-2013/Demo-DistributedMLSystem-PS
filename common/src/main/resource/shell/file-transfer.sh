#!/bin/bash

echo "Hello World !"
cd ./../../../../../
app_home=`pwd`

cd ~/Desktop/ps/
bin_home=`pwd`

# prepare the common module.
cd $app_home/common
mvn clean assembly:assembly install

# deploy the worker
cd $app_home/clientworker
mvn clean assembly:assembly
rm $bin_home/worker/src/client-worker-1.0-SNAPSHOT-jar-with-dependencies.jar
cp target/client-worker-1.0-SNAPSHOT-jar-with-dependencies.jar $bin_home/worker/src

rm $bin_home/worker1/src/client-worker-1.0-SNAPSHOT-jar-with-dependencies.jar
cp target/client-worker-1.0-SNAPSHOT-jar-with-dependencies.jar $bin_home/worker1/src

# deploy the server
cd $app_home/parameterserver
mvn clean assembly:assembly
rm $bin_home/server/src/parameter-server-1.0-SNAPSHOT-jar-with-dependencies.jar
cp target/parameter-server-1.0-SNAPSHOT-jar-with-dependencies.jar $bin_home/server/src

# deploy the master
cd $app_home/masterserver
mvn clean assembly:assembly
rm $bin_home/master/src/master-server-1.0-SNAPSHOT-jar-with-dependencies.jar
cp target/master-server-1.0-SNAPSHOT-jar-with-dependencies.jar $bin_home/master/src
