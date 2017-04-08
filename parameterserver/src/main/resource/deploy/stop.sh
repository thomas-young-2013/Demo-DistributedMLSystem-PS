#!/bin/bash

ps -ef | grep "java -jar src/parameter-server-1.0-SNAPSHOT-jar-with-dependencies.jar" | awk '{print $2}' | xargs kill -9
