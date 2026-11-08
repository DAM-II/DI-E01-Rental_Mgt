# Video Game Rental Management

Java Swing desktop application (NetBeans Matisse) for in-memory management of users, video games, and rentals.

## Requirements
- Java JDK 21+
- Apache Maven 3.8+ (or NetBeans 18+)

## How to Run

### NetBeans IDE
Open the project in NetBeans and press **F6** (Run Project).

### Terminal (Linux / macOS / Windows)
From the project root directory (where `pom.xml` is located), run:
```bash
mvn clean compile exec:java
```

## Test Data
The application starts empty. Click **"Carregar dades de prova"** at the bottom of the main screen to load sample data and display all 4 rental row colors:
- **Green**: Returned on time
- **Yellow**: Returned late
- **Red**: Overdue
- **White**: Active rental
