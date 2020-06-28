#!/bin/sh
mvn clean test-compile failsafe:integration-test -f content/content-st/pom.xml
mvn clean test-compile failsafe:integration-test -f reactor/reactor-st/pom.xml