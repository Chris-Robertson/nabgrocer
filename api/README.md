Grocery Helper

Write a simple frontend and backend application that provides the following functionality

## Requirements
-	CRUD (create, read, update, delete) grocery items
-	Ability to tag grocery items by category
-	Ability to search by category

## Notes

### Database
- NabGrocer users an embedded, in-memory [H2 SQL database](https://www.h2database.com/html/main.html)
- Spring Boot finds H2 on the classpath and automagically configures the DataSource.
- If a custom DataSource is required, it can be configured in `application.properties`

