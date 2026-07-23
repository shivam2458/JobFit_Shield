# JPA and Hibernate

## What is JPA?

JPA is a Java specification for mapping Java objects to relational database tables. It provides standard annotations and interfaces for persistence.

## What is Hibernate?

Hibernate is an ORM framework and a common JPA implementation. It translates entity operations into SQL and maps database results back into Java objects.

## What is the difference between JPA and Hibernate?

JPA is the specification, while Hibernate is an implementation of that specification.

## What is ORM?

ORM stands for Object-Relational Mapping. It maps Java classes and objects to relational database tables and rows.

## Why use `EnumType.STRING` instead of the default ordinal value?

String values remain readable and stable even if enum values are reordered. Ordinal storage can corrupt the meaning of existing data when the enum order changes.

## Why is the email constraint enforced in the database?

Application validation improves user experience, but a database unique constraint provides the final protection against duplicate email addresses.