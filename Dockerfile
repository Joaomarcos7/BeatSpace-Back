# Etapa 1: Construção do JAR
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final otimizada
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Usuário não root para segurança
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copia o JAR gerado na etapa 1
COPY --from=build /app/target/beatspace-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
