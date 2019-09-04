#!/usr/bin/env bash
cd `dirname $0`/../
./gradlew api-server:bootJar
cd docker
docker-compose restart apiserver

cd ../
URL=localhost:8080/ok
WAIT_SEC=20
sleep ${WAIT_SEC}s
HTTP_STATUS=$(curl -LI ${URL} -o /dev/null -w '%{http_code}\n' -s)
if [[ "$HTTP_STATUS" -eq '200' ]]; then
  echo "APIサーバーが起動したのでTestを実行"
else
  echo "APIサーバーが起動していないので終了"
fi