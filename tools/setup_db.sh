#!/bin/bash

current_dir=`dirname $0`
init_dir=${current_dir}/../docker/mysql/init/
MYSQL_HOST=127.0.0.1
if [ $1 ] ; then
    MYSQL_HOST=$1
fi
MYSQL_PORT=3306
if [ $2 ] ; then
    MYSQL_PORT=$2
fi

mysql -u root -h ${MYSQL_HOST} -P ${MYSQL_PORT} < ${init_dir}/1_initialize_ci.sql