#!/usr/bin/env bash
cd `dirname $0`/../
./gradlew api-server:bootJar
cd docker
echo "Restart Docker container"
docker-compose restart apiserver

cd ../
URL=localhost:8080/ok
WAIT_SEC=20
sleep ${WAIT_SEC}s
HTTP_STATUS=$(curl -LI ${URL} -o /dev/null -w '%{http_code}\n' -s)
if [[ "$HTTP_STATUS" -eq '200' ]]; then
  echo "API server is started. execute test."
  ./gradlew api-server:test --tests com.hakiba.spacucumber.RunCucumber
else
  echo "API server is not started. exit."
  exit 1
fi