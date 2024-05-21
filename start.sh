#!/bin/bash

# MySQL 서버 시작
service mysql start

# Spring Boot 애플리케이션 실행
java -jar /app.jar