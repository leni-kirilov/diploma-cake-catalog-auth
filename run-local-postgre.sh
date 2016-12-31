#!/usr/bin/env bash
echo "Starting locally. 8008 and PROD db"
java $JAVA_OPTS -Dserver.port=8008 -Dspring.profiles.active=prod -jar cake-catalog-auth-server/build/libs/cake-catalog-auth-srv-0.0.1-SNAPSHOT.jar