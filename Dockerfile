FROM eclipse-temurin:21-jre-alpine
ENV JAVA_OPS "-XX:MaxRAMPercentage=80.0 -XX:AdaptiveSizePolicyWeight=90"
WORKDIR /app
COPY ./build/libs/*.jar techmanager.jar
ENTRYPOINT exec java $JAVA_OPS -jar /app/techmanager.jar
EXPOSE 8080