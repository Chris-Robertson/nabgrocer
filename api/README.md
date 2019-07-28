Grocery Helper

Write a simple frontend and backend application that provides the following functionality

## Requirements
-	CRUD (create, read, update, delete) grocery items
-	Ability to tag grocery items by category
-	Ability to search by category

## Postman Collection
https://www.getpostman.com/collections/1e782753d912b4bb4ef9

## Notes

### Database
- NabGrocer users an embedded, in-memory [H2 SQL database](https://www.h2database.com/html/main.html)
- Data can be seeded into the H2 database on application startup. This can be handy for running 
locally, but tests should do their own seeding. To enable, just remove `DISABLED` from 
`src/main/resources/data.sql.DISABLED`

### Static Code Analysis

findbugs
pmd
https://checkstyle.org/
https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml

### Style
https://google.github.io/styleguide/javaguide.html

## TODO
*Stuff I wanted to implement but ran out of time*

- Spring HATEOAS
- JavaDoc comments in controllers and services. I don't add them while developing quickly as the 
methods can change a lot.
- Unit tests for services
- Configure a separate H2 DB for tests that can be blown away after each spec
- Suppress PMD warnings at method or class level if I don't want to turn off the check completely
- Builder for `ErrorResponseBody`

## Potential Bugs and Edge Cases
- Adding a new grocery item via POST when one with same name already exists
- Update item via PUT with no changes causes SQL index error. GroceryItem index auto increment type?
- GET requests where repo returns null are returning 200 OK! Don't know how I missed that...