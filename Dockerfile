# Use an official OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy all the files from your project into the container
COPY . .

# Give permission to mvnw script (in case it's not executable)
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Run the application (replace jar name if needed)
CMD ["java", "-jar", "target/MeFit-0.0.1-SNAPSHOT.jar"]
