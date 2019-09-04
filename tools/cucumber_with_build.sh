#!/usr/bin/env bash
cd `dirname $0`/../
./gradlew api-server:bootJar
cd docker
docker-compose restart apiserver
