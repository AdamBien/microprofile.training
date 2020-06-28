#!/bin/sh
mvn clean test-compile failsafe:integration-test failsafe:verify -f content/content-st/pom.xml
mvn clean test-compile failsafe:integration-test failsafe:verify -f reactor/reactor-st/pom.xml