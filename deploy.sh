#!/bin/bash

echo Cleaning up existing docker network and containers...
docker stop restmock-db && docker rm restmock-db
docker stop restmock-app && docker rm restmock-app
docker network rm restmock-net
echo Creating docker network...
docker network create restmock-net
echo Initializing database container...
docker run --name restmock-db --network restmock-net -d library/mongo > /dev/null 2>&1 &
echo Initializing app container...
./gradlew clean build install
docker build -t restmock-app .
docker run --name restmock-app --network restmock-net -d -p 8080:8080 restmock-app
echo Deploy completed.
