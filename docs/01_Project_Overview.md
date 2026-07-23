# JobFit Shield — Project Overview

## Project Purpose

JobFit Shield is a full-stack job application intelligence platform designed for students and job seekers.

The application helps users:

- Analyze whether a job posting appears legitimate or suspicious
- Compare a resume with a job description
- Identify matched and missing skills
- Measure application readiness
- Receive personalized recommendations
- Track job applications and their statuses

## Main Problem

Job seekers often face several problems during the application process:

- They do not know whether a job posting is legitimate
- They do not know how closely their resume matches the role
- They may miss important skills or keywords
- They apply without knowing whether their profile is ready
- They struggle to organize and track applications

JobFit Shield combines these needs into one platform.

## Version 1 Features

1. User authentication
2. User profile
3. Resume storage
4. Resume skill extraction
5. Job posting analysis
6. Job safety score
7. Resume match score
8. Candidate fit score
9. Overall recommendation
10. Application tracker
11. Dashboard statistics

## Technology Stack

### Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Maven

### Frontend

- React
- React Router
- Axios
- Tailwind CSS

### Database

- PostgreSQL

### Development Tools

- IntelliJ IDEA
- Visual Studio Code
- Postman
- Git
- GitHub

### Deployment

- Backend: Render or Railway
- Frontend: Vercel
- Database: Managed PostgreSQL service

## High-Level Architecture

```text
React Frontend
      |
      | HTTP / REST API
      v
Spring Boot Backend
      |
      | JPA / Hibernate
      v
PostgreSQL Database