# Usa uma imagem base leve com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia todos os arquivos do projeto para dentro do container
COPY . .

# Constrói o projeto usando o Gradle Wrapper
RUN ./gradlew build --no-daemon

# Expõe a porta usada pela aplicação Spring Boot (ajuste se diferente)
EXPOSE 8080

# Executa o JAR gerado após o build
CMD ["java", "-jar", "build/libs/take-home-java-billing-0.0.1-SNAPSHOT.jar"]
