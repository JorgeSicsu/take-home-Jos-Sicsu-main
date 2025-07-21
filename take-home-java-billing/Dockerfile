# Usa uma imagem base leve com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Instala dependências necessárias para o Gradle Wrapper funcionar (bash e curl)
RUN apk add --no-cache bash curl

# Define o diretório de trabalho
WORKDIR /app

# Copia o wrapper primeiro (melhora cache do Docker)
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copia o restante do código
COPY . .

# Constrói o projeto com o Gradle Wrapper
RUN chmod +x gradlew && ./gradlew build --no-daemon

# Expõe a porta usada pela aplicação
EXPOSE 8080

# Define o comando de inicialização
CMD ["java", "-jar", "build/libs/take-home-java-billing-0.0.1-SNAPSHOT.jar"]
