# Usar una imagen base con JDK 17 y Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package -DskipTests

# Crear una nueva imagen basada en OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-slim
RUN apt-get update && apt-get install -y \
    libfreetype6 \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*
  
  # Expose the port the application runs on
EXPOSE 8090

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/apiGaming-0.0.1-SNAPSHOT.jar /app/apiGaming-0.0.1-SNAPSHOT.jar
  
  # Specify the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app/apiGaming-0.0.1-SNAPSHOT.jar"]
