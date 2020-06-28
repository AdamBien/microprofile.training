#!/bin/sh
docker-compose kill
mvn clean package -f content/content/pom.xml
mvn clean package -f reactor/reactor/pom.xml
docker-compose up -d --build