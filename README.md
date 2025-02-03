# Focus List Application

FocusList is a task management application built with **Spring Boot**. It allows users to manage tasks within specific task lists, with full CRUD operations for both tasks and task lists. The application also supports priority, status management, and timestamps to track task progress.
This is the third and most advanced version of the to do list application.

The frontend part was implemented separately and in this project the focus was on developing the backend.

## Technologies

- **Java** 17
- **Spring Boot** 2.7.8
- **Spring Data JPA** for database interaction
- **PostgreSQL** for persistent storage
- **Lombok** for reducing boilerplate code
- **Docker** for containerization
- **Maven** for dependency management

## Features

The application provides the following functionality:

- **Create Task Lists**: Users can create task lists with a title and description.
- **Manage Tasks**: Users can add, update, and delete tasks within task lists.
- **Task Priorities**: Tasks can have different priority levels (e.g., LOW, MEDIUM, HIGH).
- **Task Statuses**: Tasks can be assigned statuses (e.g., OPEN, IN_PROGRESS, CLOSED).
- **Due Dates**: Tasks support due dates.
- **Timestamps**: All entities have created and updated timestamps.
- **Persistent Storage**: Uses PostgreSQL as a relational database for storage.

## Project Structure

1. **Task (Model)**: The task entity representing a task with fields for title, description, priority, status, etc.
2. **TaskList (Model)**: The task list entity containing a list of tasks.
3. **TaskRepository (Repository)**: Interface for interacting with the task database using Spring Data JPA.
4. **TaskListRepository (Repository)**: Interface for interacting with the task list database.
5. **TaskService (Service)**: Handles business logic for managing tasks.
6. **TaskListService (Service)**: Handles business logic for managing task lists.
7. **TaskController (Controller)**: REST controller for managing task-related HTTP requests.
8. **TaskListController (Controller)**: REST controller for managing task list-related HTTP requests.
9. **FocusListApplication (Main Application Class)**: The entry point to run the Spring Boot application.

## Running the Project

### 1. Clone the Repository

### 2. Backend Setup

The backend is built with **Spring Boot** and should be run separately from the frontend.

#### 1. Build and Run the Backend

- Open a terminal in the root of the project (where the `pom.xml` file is located).
- Run the following Maven command to build the backend:
```
mvn clean install
```

- After the build is completed, run the backend with:

```
mvn spring-boot:run
```

The Spring Boot backend will be available at: http://localhost:8080/

#### 2. Docker Setup for PostgreSQL

To set up the PostgreSQL database in a Docker container, use Docker Compose. Ensure that Docker is installed and running.

Run the following command in the root directory of the project to start the PostgreSQL container:
```
docker-compose up
```

This will start the PostgreSQL container, and the database will be available at `localhost:5432`.

If you want to stop the Docker container, run:


### 3. Frontend Setup (React)

The frontend is a **React** application, and you can start it separately from the backend.

#### 1. Install Dependencies

In the frontend directory named `tasks-fe` install the required dependencies.

#### 2. Run the React Development Server

To start the React frontend, use the following command: npm run dev

This will start the React development server, and the frontend will be available at:http://localhost:3000








