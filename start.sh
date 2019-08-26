#!/bin/bash

# start the Spring application in backgroud and don't display output
echo "Starting Spring Boot application as background task..."
cd nab-grocer-api
./gradlew clean bootRun &>/dev/null &

# this will kill that background Spring Boot application on CTRL-C
trap 'kill %1' EXIT

echo "Starting Angular application and opening browser..."
cd ..
cd nab-grocer-ui
ng serve --open
