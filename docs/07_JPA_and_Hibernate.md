# JPA and Hibernate

## What is JPA?

JPA, or Jakarta Persistence API, is a Java specification for mapping Java objects to relational database data.

JPA defines annotations and interfaces such as:

- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@Column`

JPA is a specification, not the actual implementation.

## What is Hibernate?

Hibernate is the JPA implementation used by Spring Boot in this project.

It reads the JPA annotations in the `User` class and generates the SQL required to create and update the `users` table.

## JPA vs Hibernate

- JPA defines the rules.
- Hibernate implements those rules.

A simple comparison:

```text
JPA = specification
Hibernate = implementation

# User Repository

Created `UserRepository` by extending `JpaRepository<User, Long>`.

Built-in methods available:

- save()
- findAll()
- findById()
- delete()
- deleteById()

Custom methods:

```java
Optional<User> findByEmail(String email);

boolean existsByEmail(String email);
```

Spring Data JPA automatically generates the SQL queries for these methods.