#!/bin/bash

# MySQL 서버 시작
service mysql start
# MySQL 초기 설정 및 사용자 호스트 변경
mysql -u root -p${1234} <<EOF
UPDATE mysql.user SET host='localhost' WHERE user='ohgiraffers' AND host='%';
FLUSH PRIVILEGES;
EOF
# Spring Boot 애플리케이션 실행
java -jar /app.jar