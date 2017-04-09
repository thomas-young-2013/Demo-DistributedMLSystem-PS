#!/bin/bash

echo "CONFIRM: execute this script in ROOT directory of this project!!!"

# main entry.
java -cp ./masterserver/target/master-server-1.0-SNAPSHOT-jar-with-dependencies.jar com.thomas.metric.PMasterMetric "$1" "$2"
