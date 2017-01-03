#!/usr/bin/env bash
echo "Starting locally. 8008 and DEV"
gradle cake-catalog-auth-server:clean cake-catalog-auth-server:build
java $JAVA_OPTS -javaagent:./cake-catalog-auth-server/newrelic/newrelic.jar -Dnewrelic.environment=development -Dserver.port=8008 -Dspring.profiles.active=dev -jar cake-catalog-auth-server/build/libs/cake-catalog-auth-srv-0.0.1-SNAPSHOT.jar