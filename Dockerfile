WORKDIR /app

COPY take-home-java-billing/ .  # copia somente o conteúdo da pasta Java

RUN chmod +x gradlew && ./gradlew build --no-daemon

EXPOSE 8080

CMD ["java", "-jar", "build/libs/take-home-java-billing-0.0.1-SNAPSHOT.jar"]
