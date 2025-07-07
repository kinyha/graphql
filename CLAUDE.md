# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Architecture Overview

This is a Spring Boot GraphQL Todo application using the Netflix DGS (Domain Graph Service) framework. The application follows a standard layered architecture:

- **Main Application**: `DemoApplication.java` - Standard Spring Boot entry point
- **GraphQL Schema**: `src/main/resources/schema/schema.graphqls` - Defines Todo type, queries, and mutations
- **Data Fetchers**: `src/main/java/com/example/graphql_todo/datafetchers/` - Contains GraphQL resolvers
- **Generated Code**: `src/main/java/com/example/graphql_todo/generated/` - Auto-generated from GraphQL schema
- **Domain Layer**: `src/main/java/com/example/graphql_todo/domain/` - Empty, ready for entity classes

## Key Technologies

- **Spring Boot 3.5.3** with Java 21
- **Netflix DGS 9.0.4** for GraphQL implementation
- **PostgreSQL** database (configured in application.properties)
- **Spring Data JPA** for data persistence
- **Lombok** for reducing boilerplate code

## Common Commands

### Build and Run
```bash
./gradlew build          # Build the application
./gradlew bootRun        # Run the application
./gradlew test           # Run tests
```

### Code Generation
```bash
./gradlew generateJava   # Generate Java classes from GraphQL schema
```

### Testing
```bash
./gradlew test                    # Run all tests
./gradlew test --tests ClassName  # Run specific test class
```

## Development Notes

- GraphQL schema changes require running `./gradlew generateJava` to regenerate types
- Generated code is placed in `com.example.graphql_todo.generated` package
- Database configuration expects PostgreSQL running on localhost:5432
- Application runs on default Spring Boot port (8080)
- Package name uses underscore (`graphql_todo`) instead of hyphen due to Java naming conventions

## GraphQL Schema Structure

The current schema defines:
- `Todo` type with id, title, completed, and createdAt fields
- `todos` query to fetch all todos
- `todo(id)` query to fetch single todo by ID
- `createTodo` mutation with CreateTodoInput