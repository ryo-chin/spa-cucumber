#!/usr/bin/env bash
./gradlew build -x test
cd docker
docker-compose restart apiserver
