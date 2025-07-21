# Usa uma imagem base leve com Java 17 e bash
FROM eclipse-temurin:17-jdk-alpine

# Instala bash e permissões para rodar gradlew
RUN apk update && apk add bash

# Define diretório de trabalho
WORKDIR /app

# Copia tudo
COPY . .

# Dá permissão para execução
RUN chmod +x ./gradlew

# Executa build
RUN ./gradlew build --no-daemon

# Expõe a porta da aplicação
EXPOSE 8080

# Executa o jar
CMD ["java", "-jar", "build/libs/take-home-java-billing-0.0.1-SNAPSHOT.jar"]
