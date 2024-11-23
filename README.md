# Role-Based Access Control (RBAC) - Spring Boot 3 Application

This is a Spring Boot 3 application implementing **Role-Based Access Control (RBAC)** for managing users and restricting access to specific resources based on roles. The application includes user authentication (via JWT) and role-based authorization for different API endpoints.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Project Overview
This Spring Boot 3 application demonstrates the implementation of **Role-Based Access Control (RBAC)**. It allows users to register, authenticate, and access certain resources based on their assigned roles. The roles include `ADMIN`, `USER`, and `MODERATOR`, and each role has different levels of access to various endpoints.

The application uses **Spring Security** for authentication and authorization, with **JWT (JSON Web Token)** for stateless authentication. The roles are managed in the database and can be assigned or updated for users.

## Features
- **User Registration**: Users can sign up with a username and password.
- **JWT Authentication**: Stateless authentication using JWT.
- **Role Assignment**: Users can have roles like `ADMIN`, `USER`, or `MODERATOR`.
- **Role-Based Authorization**: Access to endpoints is restricted based on the user’s role.
- **Database Integration**: User data and roles are stored in a relational database (e.g., MySQL, PostgreSQL).
- **Secure Endpoints**: Different roles have different access permissions to API endpoints.

## Requirements
- Java 17.
- Spring Boot 3.
- Maven.
- MySQL or any other RDBMS (for storing user data and roles).
- Postman or any API client for testing the endpoints.
  
## Installation

Follow these steps to set up the project locally:
## Customization:
- **Roles**: You can change the roles as per your requirements (e.g., `ADMIN`, `USER`, `MODERATOR`).
- **Database Setup**: Instructions for setting up the database (MySQL in this case) and configuring it in `application.properties`.
- **Endpoints**: Adjust the list of endpoints according to your actual Spring Boot controller and security configuration.

### Clone the repository:
```bash
git clone https://github.com/irishabh/role_guard.git



