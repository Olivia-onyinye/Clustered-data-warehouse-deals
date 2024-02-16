# Clustered-data-warehouse-deals

## Overview

Stack: Spring Boot, Java 17, Hibernate, PostgreSQL

As part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer user stories is to accept deals details from and persist them into DB.
It is a Spring Boot application that provides APIs for creating and managing deals, with validation and error handling mechanisms in place.
**Request logic as follows:**

-Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
-Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
-System should not import same request twice.
-No rollback allowed, what every rows imported should be saved in DB.
-Deliverables should be ready to work including:

-Use Actual Db, you can select between (Postgres, MySql or MongoDB)
-Workable deployment including sample file (Docker Compose).
-Maven or Gradle project is required for full source code.
-Proper error/exception handling.
-Proper Logging.
-Proper unit testing with respected Coverage.
-Proper documentation using md.
-Delivered over Githhub.com.
-Makefile to streamline running application (plus).

## Table of Contents
1. [Setup Instructions](#setup-instructions)
2. [Project Structure](#project-structure)
3. [Endpoints](#endpoints)
4. [Models and DTOs](#models-and-dtos)
5. [Configuration](#configuration)
6. [Error Handling](#error-handling)
7. [Testing](#testing)
8. [Deployment](#deployment)
9. [Contributing](#contributing)

## Setup Instructions
To run the Data Warehouse System locally, follow these steps:
1. Install Java 17 or later.
2. Clone the repository: `git clone https://github.com/Olivia-onyinye/Clustered-data-warehouse-deals.git .
3. Navigate to the project directory: `cd clustered-data-warehouse-deal`.
4. Build the project: `./mvnw clean package`.
5. Run the application: `java -jar target/clustered-data-warehouse-deal.jar`.

## Project Structure
The project structure follows the standard Maven project layout:

- `src/main/java/com/progressSoft/Data/Warehouse`: Java source files.
- `src/main/resources`: Configuration files and static resources.
- `src/test/java/com/progressSoft/Data/Warehouse`: Test source files.
- `pom.xml`: Maven project configuration file.

## Endpoints
### `POST /api/deals/requests`
http://localhost:8090/api/deals/requests
- Description: Create a new deal request.
- Request body: JSON payload containing deal details.
- Response status code: 201 (Created)
- Response body: Deal request saved successfully.

## Models and DTOs
### DealRequestDto
- `dealUniqueId`: String - Unique ID of the deal request.
- `fromCurrencyIsoCode`: CurrencyIsoCode - Ordering currency ISO code.
- `toCurrencyIsoCode`: CurrencyIsoCode - Target currency ISO code.
- `dealTimestamp`: Date - Timestamp of the deal.
- `dealAmount`: Double - Amount of the deal in the ordering currency.

## Configuration
- `application.properties`: Application-specific configuration settings.
- ...

## Error Handling
- 400 (Bad Request): Invalid request parameters.
- 409 (Conflict Found): Already Exists.
- ...

## Testing
- Unit tests: JUnit and Mockito.
- Integration tests: Spring Boot Test.
- ...

## Deployment
To deploy the application to a production environment:
1. Build the project: `./mvnw clean package`.
2. Deploy the generated JAR file to the production server.
3. Start the application using `java -jar`.

## Contributing
1. Fork the repository.
2. Create a new branch: `git checkout -b feature/new-feature`.
3. Make changes and commit: `git commit -m "Add new feature"`.
4. Push to the branch: `git push origin feature/new-feature`.
5. Submit a pull request.
