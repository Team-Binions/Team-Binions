FROM eclipse-temurin:17
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "/app.jar"]

# MySQL 설치
RUN apt-get update && \
    apt-get install -y mysql-server-8.0 && \
    rm -rf /var/lib/apt/lists/*


# Redis 설치
RUN apt-get update && \
    apt-get install -y redis-server && \
    rm -rf /var/lib/apt/lists/* \


# MySQL 사용자 디렉토리 생성 및 권한 설정
RUN mkdir -p /home/mysql

RUN chown mysql:mysql /home/mysql

# MySQL 설정 파일 수정
RUN sed -i 's|/nonexistent|/home/mysql|g' /etc/passwd

# MySQL 및 Redis 시작 스크립트 작성
COPY start.sh /start.sh
RUN chmod +x /start.sh

# 애플리케이션 실행
ENTRYPOINT ["/start.sh"]
