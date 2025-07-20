# User Management and Banking System

This project implements a simple user management and banking system in Java. It allows users to create accounts, log in, deposit, withdraw, transfer funds, view their balance, and update their personal information. The system interacts with a database to store and retrieve user data.

## Features

* **User Authentication:**
    * User registration with validation for name, surname, age, address, username (unique, format-specific), and password (strength-specific).
    * User login with a maximum of 3 attempts.
* **Banking Operations:**
    * **Deposit:** Add funds to the user's account.
    * **Withdraw:** Remove funds from the user's account (with balance validation).
    * **Transfer:** Transfer funds to another user's account by ID (with balance validation).
    * **View Balance:** Display the current account balance.
* **User Profile Management:**
    * View all personal data.
    * Update personal details (name, surname, username, password, age, address).
* **Data Persistence:**
    * Uses a database (via JDBC) to store `UserEntity` objects.
    * `DaoImpl` class handles database operations (add, get, update, check uniqueness).
* **Input Validation:**
    * Robust input validation for all user inputs (e.g., amounts, choices, personal details).
* **ID Generation:**
    * Generates unique IDs for new users.

## Project Structure

The project is organized into several packages:

* `Project.DB`: Handles database connectivity and data access operations.
* `Project.Display`: Contains classes for user interaction and displaying menus.
* `Project.Entity`: Defines the `UserEntity` class, representing user data.
* `Project.Login`: Manages user login and registration logic.
* `Project.Operations`: Implements banking operations (deposit, withdraw, transfer).

### Key Classes:

* `Display.java`: The main class for displaying menus and handling user interaction.
* `UserLogIn.java`: Handles user login authentication.
* `UserSignUp.java`: Manages user registration and input validation for new accounts.
* `Operations.java`: Contains the business logic for banking operations.
* `DaoImpl.java`: Implements the `DAO` interface for database interactions.
* `UserEntity.java`: Represents the user data model.
* `ValidateAmount.java`: Validates monetary amounts entered by the user.
* `ValidateInput.java`: Validates general integer inputs for menu choices.
* `IdGenerator.java`: Generates unique user IDs.

## Setup and Usage

### Prerequisites

* Java Development Kit (JDK) 8 or higher.
* A relational database (e.g., MySQL, PostgreSQL) and its JDBC driver.

### Database Setup

1.  Create a database (e.g., `bankingsystem`).
2.  Create a table named `userdata` with the following schema:

    ```sql
    CREATE TABLE userdata (
        id VARCHAR(255) PRIMARY KEY,
        username VARCHAR(255) UNIQUE NOT NULL,
        password VARCHAR(255) NOT NULL,
        name VARCHAR(255),
        surname VARCHAR(255),
        age INT,
        address VARCHAR(255),
        balance DOUBLE,
        salt VARCHAR(24)
    );
    ```

3.  Update the `DbConnection.java` file with your database connection details (DB_URL, username, password).

### Running the Application

1.  Compile the Java files. If you are using an IDE like IntelliJ IDEA or Eclipse, you can simply open the project.
2.  Run the `Display` class (it likely contains the `main` method or is called from a main class).

    ```bash
    javac Project/Display/*.java Project/DB/*.java Project/Entity/*.java Project/Login/*.java Project/Operations/*.java
    java Project.Display.Display
    ```

### Example Workflow

1.  **Start the application:**
    ```
    === Account Access ===
    1. Login
    2. Create Account
    3. Exit
    Enter your choice (1-3):
    ```
2.  **Create a new account (Choice 2):**
    The system will prompt you to enter details like name, surname, age, address, username, password, and initial balance, with various validations.
3.  **Log in (Choice 1):**
    Enter your newly created username and password.
    ```
    Enter Username: your_username
    Enter Password: your_password
    ```
    If successful, you will see a welcome message and the operations menu.
4.  **Perform operations:**
    From the operations menu, you can choose to deposit, withdraw, transfer, view balance, get all your data, or update your data.

    * **Deposit (Choice 1):**
        ```
        Enter Amount: 100
        Deposited. New balance: 100.0
        ```
    * **Withdraw (Choice 2):**
        ```
        Enter Amount: 50
        Withdraw successful. New balance: 50.0
        ```
    * **Transfer (Choice 3):**
      You'll need the ID of another user to transfer funds.
        ```
        Enter Receiver ID: receiver_id
        Enter Amount: 25
        Transfer successful. New balance: 25.0
        ```
    * **View Balance (Choice 4):**
        ```
        Current Balance: 25.0
        ```
    * **Update Data (Choice 6):**
      You can select which field to update (e.g., Name, Password). After updating, choose "Save and Exit" to persist changes and return to the account access menu.

5.  **Exit (Choice 7 from operations, or Choice 3 from account access):**
    ```
    Exiting...
    ```

## Code Overview

* **`UserLogIn.java`**:
    * The `Login` method attempts to retrieve a `UserEntity` from the database using the provided username and password. If found, it returns the `UserEntity`; otherwise, it returns `null`.
* **`IdGenerator.java`**:
    * `generateId()`: Creates a 7-character random ID using a `SecureRandom` instance and a predefined character set.
* **`ValidateAmount.java`**:
    * `validAmount(double min, double max, Scanner sc)`: Continuously prompts the user for a monetary amount until a valid number within the specified `min` and `max` range is entered. Handles `NumberFormatException` for invalid inputs.
* **`Display.java`**:
    * `displayAccAccess()`: Presents the initial menu for login, account creation, or exit. Manages login attempts and directs to `displayOperations()` upon successful login.
    * `displayOperations()`: The main loop for banking and profile management operations. It uses `ValidateInput` and `ValidateAmount` for user input. It also handles calling the appropriate methods from `Operations` and `DaoImpl`.
* **`UserSignUp.java`**:
    * Contains methods like `checkStr`, `checkInt`, `checkMix`, `checkUsername`, and `checkPassword` for robust validation of user input during account creation.
    * `saveUser()`: Collects all user details with validation and returns a `UserEntity` object. It also checks for unique usernames before saving.
* **`DaoImpl.java`**:
    * Implements the `DAO` interface (not provided, but inferred methods).
    * `addUser(UserEntity user)`: Inserts a new user into the `userdata` table.
    * `getUser(String userName, String password)`: Retrieves a user by username and password for login.
    * `getUserById(String id)`: Retrieves a user by their unique ID, used for transfers.
    * `updateBalance(String id, double newBalance)`: Updates the balance of a user in the database.
    * `checkUnique(String userName)`: Checks if a username already exists in the database.
    * `update(UserEntity user)`: Updates all modifiable fields of a user in the database.
* **`Operations.java`**:
    * `deposit(UserEntity user, double amount)`: Adds the specified amount to the user's balance and updates the database.
    * `withdraw(UserEntity user, double amount)`: Subtracts the specified amount from the user's balance and updates the database.
    * `transfer(UserEntity user, String transferId, double amount)`: Decrements the sender's balance and increments the receiver's balance in the database.
* **`ValidateInput.java`**:
    * `validateInput(Scanner sc, int min, int max)`: Ensures that user input for menu choices is an integer within a specified range. Handles `InputMismatchException`.
* **`UserEntity.java`**:
    * A simple POJO (Plain Old Java Object) representing a user with fields like `id`, `name`, `surname`, `age`, `address`, `username`, `password`, and `balance`. Includes constructors, getters, and setters.

