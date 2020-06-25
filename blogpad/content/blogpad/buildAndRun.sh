#!/bin/sh
mvn clean package && docker build -t com.airhacks/blogpad .
docker rm -f blogpad || true && docker run -d -p 8080:8080 -p 4848:4848 --name blogpad com.airhacks/blogpad 
