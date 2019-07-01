FROM openjdk:alpine

MAINTAINER Huabing Zhao <zhaohuabing@gmail.com>

ENV APP_HOME /app/

COPY target/istio-opentracing-demo-kafka-consumer-0.0.1-SNAPSHOT.jar $APP_HOME

EXPOSE 8080

WORKDIR $APP_HOME

# set env variables when starting the container
CMD java -jar istio-opentracing-demo-kafka-consumer-0.0.1-SNAPSHOT.jar
