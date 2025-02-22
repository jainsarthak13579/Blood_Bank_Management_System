# Blood Bank Management System

## Overview
This is a menu-based console application for managing a blood bank. The system allows users to manage donors, blood inventory, and requests.

## Setup Instructions
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/blood-bank-management-system.git
    ```
2. Navigate to the project directory:
    ```bash
    cd blood-bank-management-system
    ```
3. Set up the database:
    - Import the `blood_bank.sql` file into your MySQL database to create the necessary tables.
4. Update database connection details:
    - Edit the `jdbc.properties` file in the `resources` directory with your database connection details.
5. Compile and run the application:
    - Use your preferred IDE or run the following command:
    ```bash
    javac -d bin src/main/java/*.java
    java -cp bin Main
    ```

## Usage Instructions
1. Run the application.
2. Follow the on-screen menu to manage donors, inventory, and requests.
