#!/usr/bin/env bash
echo "Starting production. Port $PORT"
java $JAVA_OPTS -Dserver.port=$PORT -Dspring.profiles.active=prod -jar cake-catalog-auth-server/build/libs/cake-catalog-auth-srv-0.0.1-SNAPSHOT.jar