#!/usr/bin/env bash
echo "Starting locally. 8008 and DEV"
gradle cake-catalog-auth-server:clean cake-catalog-auth-server:build
java $JAVA_OPTS -Dserver.port=8008 -Dspring.profiles.active=dev -jar cake-catalog-auth-server/build/libs/cake-catalog-auth-srv-0.0.1-SNAPSHOT.jar