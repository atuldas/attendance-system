# attendance-system
# Attendance Management System

This is a simple Attendance Management System built with Spring Boot and Thymeleaf. It allows users to mark attendance with validation to ensure that attendance is only marked for today or future dates and only once per day.

## Features

- **User Authentication**: Users can log in with their usernames.
- **Role-Based Access Control**: 
  - **Admin**: Can create, update, delete users, and mark attendance.
  - **Teacher/Student**: Can only mark attendance.
- **Attendance Management**:
  - Mark attendance for the current or future dates only.
  - Prevent multiple attendance entries for the same day.

## Technologies Used

- **Backend**:
  - Spring Boot
  - Spring Security
  - Java 8/17
  - JPA/Hibernate
  - MySQL or any preferred relational database
- **Frontend**:
  - Thymeleaf
  - Bootstrap 4

## Getting Started

### Prerequisites

- JDK 8 or 17
- Maven 3.6+
- MySQL or any compatible database

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/attendance-management-system.git
    cd attendance-management-system
    ```

2. **Set up the database**:
   - Create a database named `attendance_db`.
   - Update the `application.properties` file with your database credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/attendance_db
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. **Build and run the application**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Access the application**:
   - Open your browser and navigate to `http://localhost:8080`.

### Usage

- **Login**: Access the login page via the root URL.
- **Admin Operations**: 
  - After logging in as an admin, you can create, update, and delete users.
  - Admins can also mark attendance for any user.
- **Teacher/Student Operations**:
  - After logging in, teachers and students can mark their own attendance.
  
### Validations

- **Date Validation**: Users can only select dates from today onwards. Past dates are disabled.
- **Single Attendance Mark**: Users can only mark attendance once per day. If an attempt is made to mark attendance again for the same day, an error will be displayed.



### Contributing

If you would like to contribute, please fork the repository and submit a pull request. For any issues or feature requests, please open an issue in the repository.


### Contact

For any inquiries or issues, please contact [atul.das02@gmail.com].

