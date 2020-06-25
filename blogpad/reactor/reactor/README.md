# Build
mvn clean package && docker build -t com.airhacks/reactor .

# RUN

docker rm -f reactor || true && docker run -d -p 8080:8080 -p 4848:4848 --name reactor com.airhacks/reactor 

# System Test

Switch to the "-st" module and perform:

mvn compile failsafe:integration-test