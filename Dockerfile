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
    apt-get install -y redis-server-latest && \
    rm -rf /var/lib/apt/lists/*

# MySQL 및 Redis 포트 노출
EXPOSE 3306 6379 8080

# MySQL 및 Redis 시작 스크립트 작성
COPY start.sh /start.sh
RUN chmod +x /start.sh

# 애플리케이션 실행
ENTRYPOINT ["/start.sh"]
