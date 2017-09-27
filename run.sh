#!/bin/bash


if [ ! -e 'target/doctor-booking-1.0.0-standalone.jar' ];then
   mvn package
fi

java -jar target/doctor-booking-1.0.0-standalone.jar
