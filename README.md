# SpringBootSpringBootHELIOS
App for this test in HELIOS - API

## Description
This is a Spring Boot application designed to manage and send real-time notifications for a gaming platform. The application exposes a REST API that allows triggering game and social event notifications asynchronously. It is built using Maven and can be extended with additional notification channels (email, push notifications, etc.).
Is doing the base code, we could improve>

Refactored Notification System
Users can trigger game events (levelUp, itemAcquired).
Users can trigger social events (friendRequestSent, friendRequestAccepted).
Unit Testing with Mockito & JUnit

## Requirements
- Java 17
- Maven 3.8.3
- Postman
- Git
- IDE (IntelliJ IDEA, Eclipse, etc.)
- Lombok 
- Spring Boot 


## Installation
1. Clone the repository using the following command:
    - https://github.com/nescool101/HeliosTest
2. Open the project in your IDE.
3. Import the project as a Maven project.
4. Run the application using the Spring Boot plugin or the following command:
   - mvn spring-boot:run
5. The application will start on port 8080.


## Usage
The application exposes the following endpoints:

http://localhost:8080/swagger-ui/index.html#/

curl -X POST "http://localhost:8080/notifications/game?userId=1&eventType=levelUp&param=20"
Simulating Item Acquired Event

curl -X POST "http://localhost:8080/notifications/game?userId=2&eventType=itemAcquired&param=Excalibur"
Simulating Friend Request Sent

curl -X POST "http://localhost:8080/notifications/social?userId1=3&userId2=1&eventType=friendRequestSent"
Simulating Friend Request Accepted
curl -X POST "http://localhost:8080/notifications/social?userId1=1&userId2=3&eventType=friendRequestAccepted"



## TODO

Add WebSockets for real-time notifications.
Integrate Kafka/RabbitMQ for event-driven processing.
Store notifications in PostgreSQL or MongoDB. H2 others

## AUTHOR
Developed as part of the Helios exercise, done by NESTOR ALVAREZ.

