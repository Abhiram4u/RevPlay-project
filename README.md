# RevPlay – Console-Based Music Streaming Application

RevPlay is a **Java console-based music streaming application** developed using **Core Java, JDBC, MySQL, Maven**, and follows a **layered architecture**.  
The application supports two roles — **User** and **Artist** — and provides real-world music platform features such as song management, playlists, favorites, listening history, logging, and unit testing.

This project demonstrates **end-to-end backend development**, clean architecture, database interaction, logging, and mock testing.

---

##  Key Features

###  User Features
- User registration and login  
- View all available songs  
- Search songs by title or genre  
- Play songs using a console-based music player  
- Add songs to favorites  
- View favorite songs  
- Create playlists (public/private)  
- Update and delete playlists  
- Add and remove songs from playlists  
- View public playlists  
- Track complete listening history  
- View recently played songs  

---

###  Artist Features
- Artist registration and login  
- Create and manage artist profile (bio, genre, social links)  
- Create albums  
- Upload songs (with or without albums)  
- View uploaded songs  
- Update song and album details  
- Delete songs and albums  
- View play count statistics for uploaded songs  
- View users who favorited their songs  
- Artist-specific data security (artists can manage only their own data)  

---

## 🏗️ Project Architecture

The RevPlay application follows a **clean, layered architecture** to ensure clear separation of concerns, maintainability, and scalability.

### 1. Model Layer (`com.revplay.util.model`)
Contains **POJOs (Plain Old Java Objects)** that represent the core domain entities of the application.  
These classes map directly to database tables and hold only data with getters and setters.

**Examples:**
- `User`
- `Artist`
- `Song`
- `Album`
- `Playlist`

This layer does **not** contain business logic or database code.

---

### 2. DAO Layer (`com.revplay.util.dao`)
Handles **all database interactions** using **JDBC**.  
Each DAO class is responsible for CRUD operations related to a specific entity.

**Key characteristics:**
- Uses `PreparedStatement` to prevent SQL injection
- Follows `try-with-resources` for proper resource management
- Contains no UI or business logic

**Examples:**
- `UserDAO`
- `ArtistDAO`
- `SongDAO`
- `PlaylistDAO`
- `FavoriteDAO`
- `HistoryDAO`

---

### 3. Service Layer (`com.revplay.util.service`)
Contains the **business logic** of the application.  
This layer acts as a bridge between the UI and DAO layers.

**Responsibilities:**
- Input validation
- Business rules enforcement
- Coordination between multiple DAOs
- Ensuring artist/user-specific data access
- Play count and history updates

**Examples:**
- `UserService`
- `ArtistService`
- `SongService`
- `PlaylistService`
- `HistoryService`
- `FavoriteService`

---

### 4. UI Layer (`com.revplay.util.ui`)
Manages the **console-based user interface (CLI)**.  
It collects user input, displays menus, and invokes appropriate service-layer methods.

**Responsibilities:**
- Menu navigation
- User input handling
- Displaying results to the console
- No database logic

**Examples:**
- `MainMenu`
- `UserUI`
- `ArtistUI`
- `PlayerUI`

---

### 5. Configuration Layer (`com.revplay.util`)
Handles **application-level configurations**, primarily database connectivity.

**Key class:**
- `DBConnection` – Provides MySQL database connection using JDBC

---

### 6. Logging Layer (Log4j 2)
Logging is implemented using **Log4j 2** to replace `System.out.println`.

**Features:**
- Centralized logging configuration
- Logs written to external file (`logs/revplay.log`)
- Supports INFO, WARN, and ERROR levels
- Used across DAO and Service layers

---

### 7. Testing Layer (`src/test/java`)
Implements **unit testing and mock testing** for the Service layer.

**Tools used:**
- JUnit 5
- Mockito

**Purpose:**
- Test business logic independently
- Mock DAO dependencies
- Avoid real database interaction during tests

---

Each layer communicates only with the layer directly below it.

---

## 📂 Project Structure

revplay/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── com.revplay.util
│ │ │ │ ├── dao
│ │ │ │ ├── model
│ │ │ │ ├── service
│ │ │ │ ├── ui
│ │ │ │ └── DBConnection.java
│ │ │ └── org.example.Main.java
│ │ └── resources/
│ │ └── log4j2.xml
│ └── test/
│ └── java/
│ └── com.revplay.util.service
│ ├── ArtistServiceTest.java
│ ├── SongServiceTest.java
│ └── UserServiceTest.java
├── logs/
│ └── revplay.log
├── pom.xml
└── README.md



---

## 🛠️ Technologies Used

- **Java 21 (LTS)**  
- **JDBC**  
- **MySQL**  
- **Maven**  
- **Log4j 2** – Centralized logging  
- **JUnit 5** – Unit testing  
- **Mockito** – Mock testing  
- **IntelliJ IDEA**

---

## 🗄️ Database Design

**Database Name:** `revplay_db`  
**Database Type:** MySQL  

### Tables Used:
- `users`
- `artists`
- `albums`
- `songs`
- `favorites`
- `playlists`
- `playlist_songs`
- `listening_history`

Foreign key relationships are used to maintain data integrity and enforce artist/user-specific access.

---

## 🔐 Logging (Log4j 2)

- Logging is implemented using **Log4j 2**
- Logs include:
  - Database connection status
  - CRUD operations
  - Validation warnings
  - Errors and exceptions
- Logs are written to:

---

## 🛠️ Technologies Used

- **Java 21 (LTS)**  
- **JDBC**  
- **MySQL**  
- **Maven**  
- **Log4j 2** – Centralized logging  
- **JUnit 5** – Unit testing  
- **Mockito** – Mock testing  
- **IntelliJ IDEA**

---

## 🗄️ Database Design

**Database Name:** `revplay_db`  
**Database Type:** MySQL  

### Tables Used:
- `users`
- `artists`
- `albums`
- `songs`
- `favorites`
- `playlists`
- `playlist_songs`
- `listening_history`

Foreign key relationships are used to maintain data integrity and enforce artist/user-specific access.

---

## 🔐 Logging (Log4j 2)

- Logging is implemented using **Log4j 2**
- Logs include:
  - Database connection status
  - CRUD operations
  - Validation warnings
  - Errors and exceptions
- Logs are written to:
logs/revplay.log


This replaces the use of `System.out.println` for backend logging and follows industry standards.

---

## 🧪 Testing & Mocking

- Unit tests are written for the **Service Layer**
- DAO layer is **mocked using Mockito**
- Tests do **not connect to the real database**
- Ensures business logic correctness and isolation

### Run Tests:
```bash
mvn test


