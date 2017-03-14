#!/bin/bash
echo "Hello World !"
cd ~/Desktop/ps
rm *.jar

cd ~/Desktop/codes/DistributedMLSystem/clientworker/
mvn clean
mvn assembly:assembly
cp target/client-worker-1.0-SNAPSHOT-jar-with-dependencies.jar ~/Desktop/ps/
