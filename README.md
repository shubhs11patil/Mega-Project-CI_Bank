
# Dockerized Banking Application

## Overview

This project showcases a Dockerized Banking Application built using Spring Boot and MySQL. The application is containerized using Docker and Docker Compose, enabling efficient service management and deployment.

## Table of Contents

- [Pre-requisites](#pre-requisites)
- [Key Features](#key-features)
- [Setup and Deployment](#setup-and-deployment)
- [Accessing the Application](#accessing-the-application)
- [Git Workflow](#git-workflow)
- [Acknowledgments](#acknowledgments)

## Pre-requisites

Before you begin, ensure you have the following:

- An **AWS account**
- A **t2.medium EC2 instance** for deployment
- **Docker** and **Docker Compose** installed

## Key Features

- **Containerization:** Utilizes multi-stage Docker builds for performance optimization.
- **Service Management:** Manages services with Docker Compose.
- **Persistent Storage:** MySQL database with data persistence using Docker volumes.

## Setup and Deployment

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Amitabh-DevOps/Springboot-BankApp.git
   cd Springboot-BankApp
   ```

2. **Create Docker Volume and Network**
   ```bash
   docker volume create mysql-bankapp
   docker network create bankapp
   ```

3. **Run MySQL Container**
   ```bash
   docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=Test@123 -e MYSQL_DATABASE=BankDB --network=bankapp mysql:latest
   ```

4. **Build the Spring Boot Application**
   ```bash
   docker build -t bankapp-mini .
   ```

5. **Run the Spring Boot Application Container**
   ```bash
   docker run -d --name bankapp-mini \
   -e SPRING_DATASOURCE_USERNAME="root" \
   -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql:3306/BankDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" \
   -e SPRING_DATASOURCE_PASSWORD="Test@123" \
   --network=bankapp \
   -p 8080:8080 bankapp-mini:latest
   ```

6. **Using Docker Compose (if applicable)**
   - Ensure you have a properly configured `docker-compose.yml`.
   - Run:
   ```bash
   docker compose up
   ```

## Accessing the Application

Once the application is running, you can access it via the public IP of your EC2 instance on port 8080:

```
http://<EC2-instance-public-IP>:8080
```

---

## Detailed Guide

For a comprehensive walkthrough of the setup and deployment, refer to my blog post: [Deploying Multi-Tier Application with Docker](https://amitabhdevops.hashnode.dev/deploying-multitier-application-with-docker)

## Implementation Notes

For detailed implementation notes with images, visit my Notion page: [Notion Notes](https://www.notion.so/Docker-bankapp-project-12c7311ab980801a929ad23bf654b64d)


## Acknowledgments

Special thanks to **Shubham Londhe** for his guidance and support throughout this project!

---
