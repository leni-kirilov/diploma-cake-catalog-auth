#!/usr/bin/env bash
echo "Build jar and update monolith"
./gradlew cake-catalog-auth-client:jar; cp cake-catalog-auth-client/build/libs/cake-catalog-auth-client-0.0.1-SNAPSHOT.jar ../cake-catalog-monolith/server

