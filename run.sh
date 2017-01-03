#!/usr/bin/env bash
echo "Starting production. Port $PORT"
java $JAVA_OPTS -javaagent:./cake-catalog-auth-server/newrelic/newrelic.jar -Dnewrelic.environment=production -Dserver.port=$PORT -Dspring.profiles.active=prod -jar cake-catalog-auth-server/build/libs/cake-catalog-auth-srv-0.0.1-SNAPSHOT.jar