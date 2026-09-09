# Food Donation Application

A Spring Boot web application connecting food donors with receivers to eliminate food waste.

## Features
- **User Authentication**: Secure registration and login for Donors and Receivers.
- **Donor Dashboard**: Post surplus food details (food type, quantity, contact details) and view donation history.
- **Receiver Dashboard**: Browse available food donations and book items in real-time.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.5.4, Spring Data JPA, Hibernate
- **Frontend**: HTML5, Thymeleaf, Vanilla CSS
- **Database**: MySQL

## Prerequisites
- Java 17+
- Maven 3.8+
- MySQL Server

## Setup Instructions

1. **Configure MySQL Database**:
   Create a database named `food_donation`:
   ```sql
   CREATE DATABASE food_donation;
   ```
   Update database credentials in `src/main/resources/application.properties` if needed.

2. **Build and Run**:
   ```bash
   mvn clean spring-boot:run
   ```

3. **Access Application**:
   Open browser at [http://localhost:8080](http://localhost:8080)
